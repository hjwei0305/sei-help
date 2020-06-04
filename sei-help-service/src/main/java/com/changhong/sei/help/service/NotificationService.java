package com.changhong.sei.help.service;

import com.changhong.sei.help.entity.Notification;
import com.changhong.sei.help.dao.NotificationDao;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    @Override
    protected BaseEntityDao<Notification> getDao() {
        return dao;
    }

}