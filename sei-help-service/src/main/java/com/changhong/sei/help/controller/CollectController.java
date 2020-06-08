package com.changhong.sei.help.controller;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.context.SessionUser;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.help.api.CollectApi;
import com.changhong.sei.help.dto.CollectDto;
import com.changhong.sei.help.dto.NotificationAction;
import com.changhong.sei.help.entity.Collect;
import com.changhong.sei.help.entity.Topic;
import com.changhong.sei.help.service.CollectService;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.help.service.NotificationService;
import com.changhong.sei.help.service.TopicService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;

import java.util.Date;
import java.util.Objects;

/**
 * 话题收藏(Collect)控制类
 *
 * @author sei
 * @since 2020-06-04 10:10:36
 */
@RestController
@Api(value = "CollectApi", tags = "话题收藏服务")
@RequestMapping(path = "collect", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CollectController extends BaseEntityController<Collect, CollectDto> implements CollectApi {
    /**
     * 话题收藏服务对象
     */
    @Autowired
    private CollectService collectService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private NotificationService notificationService;

    @Override
    public BaseEntityService<Collect> getService() {
        return collectService;
    }

    @Override
    public ResultData<CollectDto> save(CollectDto dto) {
        String topicId = dto.getTopic().getId();
        if (StringUtils.isBlank(topicId)) {
            return ResultData.fail("要收藏的话题ID不能为空");
        }
        SessionUser user = ContextUtil.getSessionUser();
        String userId = user.getUserId();
        Topic topic = topicService.findOne(topicId);
        if (Objects.isNull(topic)) {
            return ResultData.fail("00005");
        }
        Collect collect = collectService.findByUserIdAndTopicId(userId, topicId);
        if (Objects.nonNull(collect)) {
            return ResultData.fail("您已经收藏了这个话题");
        }
        dto.setUserId(userId);
        dto.setUserInfo(user.getUserName() + "[" + user.getAccount() + "]");
        dto.setInTime(new Date());
        collectService.save(convertToEntity(dto));
        // 更新话题的收藏数
        topic.setCollectCount(topic.getCollectCount() + 1);
        topicService.save(topic);
        // 创建通知
        notificationService.create(topicId, userId, topic.getCreatorId(), NotificationAction.COLLECT);
        return ResultData.success(dto);
    }

    /**
     * 根据话题id取消收藏
     * @param topicId 话题id
     * @return 结果
     */
    @Override
    @Transactional
    public ResultData<String> deleteByTopicId(String topicId) {
        if (StringUtils.isBlank(topicId)) {
            return ResultData.fail("要取消收藏的话题ID不能为空");
        }
        SessionUser user = ContextUtil.getSessionUser();
        String userId = user.getUserId();
        Collect collect = collectService.findByUserIdAndTopicId(userId, topicId);
        if (Objects.isNull(collect)) {
            return ResultData.fail("您还没有收藏了这个话题");
        }
        Topic topic = topicService.findOne(topicId);
        if (Objects.isNull(topic)) {
            return ResultData.fail("要取消收藏的话题不存在");
        }
        // 更新话题的收藏数
        topic.setCollectCount(topic.getCollectCount() - 1);
        topicService.save(topic);
        // 取消收藏
        collectService.deleteById(collect.getId());
        return ResultData.success("00001");
    }

}