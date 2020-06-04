package com.changhong.sei.help.controller;

import com.changhong.sei.help.api.NotificationApi;
import com.changhong.sei.help.dto.NotificationDto;
import com.changhong.sei.help.entity.Notification;
import com.changhong.sei.help.service.NotificationService;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;

/**
 * 通知(Notification)控制类
 *
 * @author sei
 * @since 2020-06-04 10:12:17
 */
@RestController
@Api(value = "NotificationApi", tags = "通知服务")
@RequestMapping(path = "notification", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class NotificationController extends BaseEntityController<Notification, NotificationDto> implements NotificationApi {
    /**
     * 通知服务对象
     */
    @Autowired
    private NotificationService service;

    @Override
    public BaseEntityService<Notification> getService() {
        return service;
    }

}