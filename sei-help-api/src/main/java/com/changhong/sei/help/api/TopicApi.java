package com.changhong.sei.help.api;

import com.changhong.sei.help.dto.TopicDto;
import com.changhong.sei.core.api.BaseEntityApi;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

/**
 * 话题(Topic)API
 *
 * @author sei
 * @since 2020-06-04 10:07:39
 */
@Valid
@FeignClient(name = "sei-help", path = "topic")
public interface TopicApi extends BaseEntityApi<TopicDto> {

}