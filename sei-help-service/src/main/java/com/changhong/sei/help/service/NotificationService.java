package com.changhong.sei.help.service;

import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.bo.OperateResult;
import com.changhong.sei.help.dto.NotificationAction;
import com.changhong.sei.help.dto.NotificationDto;
import com.changhong.sei.help.entity.Notification;
import com.changhong.sei.help.dao.NotificationDao;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.help.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * 通知(Notification)业务逻辑实现类
 *
 * @author sei
 * @since 2020-06-04 10:12:17
 */
@Service("notificationService")
public class NotificationService extends BaseEntityService<Notification> {
    @Autowired
    private NotificationDao dao;
    @Autowired
    private TopicService topicService;
    @Autowired
    private TaskExecutor taskExecutor;

    @Override
    protected BaseEntityDao<Notification> getDao() {
        return dao;
    }

    /**
     * 获取未读数量
     * @param targetUserId 用户id
     * @return 未读数量
     */
    public long countNotRead(String targetUserId) {
        Notification notification = new Notification();
        notification.setRead(false);
        notification.setTargetUserId(targetUserId);
        return dao.count(Example.of(notification));
    }

    /**
     * 创建通知
     * @param topicId 话题id
     * @param userId 创建人id
     * @param targetUserId 接收者id
     * @param action 通知类型
     */
    public OperateResult create(String topicId, String userId, String targetUserId, NotificationAction action) {
        Topic topic = topicService.findOne(topicId);
        if (Objects.isNull(topic)){
            return OperateResult.operationFailure("00003");
        }
        // userId 与 targetUserId 相同的话，说明是自己给自己评论、回复、收藏，则不创建通知
        if (!userId.equals(targetUserId)) {
            Notification notification = new Notification();
            notification.setTopic(topic);
            notification.setUserId(userId);
            notification.setTargetUserId(targetUserId);
            notification.setAction(action);
            notification.setInTime(new Date());
            notification.setRead(false);
            save(notification);
        }
        return OperateResult.operationSuccess();
    }

    @Override
    public PageResult<Notification> findByPage(Search searchConfig) {
        PageResult<Notification> page = super.findByPage(searchConfig);
        //更新消息为已读
        updateRead(page.getRows());
        return page;
    }

    /**
     * 更新消息为已读
     * @param notifications
     */
    private void updateRead(List<Notification> notifications) {
        taskExecutor.execute(() -> {
            try {
                Thread.sleep(500);
                notifications.stream()
                        .filter(notification1 -> !notification1.getRead())
                        .forEach(notification1 -> {
                            notification1.setRead(true);
                            this.save(notification1);
                        });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}