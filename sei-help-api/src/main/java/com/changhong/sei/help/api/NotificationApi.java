package com.changhong.sei.help.api;

import com.changhong.sei.help.dto.NotificationDto;
import com.changhong.sei.core.api.BaseEntityApi;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 通知(Notification)API
 *
 * @author sei
 * @since 2020-06-04 10:12:17
 */
@Valid
@FeignClient(name = "sei-help", path = "notification")
public interface NotificationApi extends BaseEntityApi<NotificationDto> {

}