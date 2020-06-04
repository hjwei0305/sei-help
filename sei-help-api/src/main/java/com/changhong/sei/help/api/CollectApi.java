package com.changhong.sei.help.api;

import com.changhong.sei.help.dto.CollectDto;
import com.changhong.sei.core.api.BaseEntityApi;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 话题收藏(Collect)API
 *
 * @author sei
 * @since 2020-06-04 10:10:36
 */
@Valid
@FeignClient(name = "sei-help", path = "collect")
public interface CollectApi extends BaseEntityApi<CollectDto> {

}