package com.changhong.sei.help.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.help.dto.NotificationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

/**
 * 通知(Notification)API
 *
 * @author sei
 * @since 2020-06-04 10:12:17
 */
@Valid
@FeignClient(name = "sei-help", path = "notification")
public interface NotificationApi extends BaseEntityApi<NotificationDto>, FindByPageApi<NotificationDto> {

    /**
     * 获取未读数量
     * @return 未读数量
     */
    @GetMapping("/notRead")
    ResultData<Long> notRead();

}