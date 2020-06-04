package com.changhong.sei.help.api;

import com.changhong.sei.help.dto.UserAuthorityDto;
import com.changhong.sei.core.api.BaseEntityApi;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * (UserAuthority)API
 *
 * @author sei
 * @since 2020-06-04 10:12:27
 */
@Valid
@FeignClient(name = "sei-help", path = "userAuthority")
public interface UserAuthorityApi extends BaseEntityApi<UserAuthorityDto> {

}