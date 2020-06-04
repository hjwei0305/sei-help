package com.changhong.sei.help.controller;

import com.changhong.sei.help.api.UserAuthorityApi;
import com.changhong.sei.help.dto.UserAuthorityDto;
import com.changhong.sei.help.entity.UserAuthority;
import com.changhong.sei.help.service.UserAuthorityService;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;

/**
 * (UserAuthority)控制类
 *
 * @author sei
 * @since 2020-06-04 10:12:27
 */
@RestController
@Api(value = "UserAuthorityApi", tags = "服务")
@RequestMapping(path = "userAuthority", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserAuthorityController extends BaseEntityController<UserAuthority, UserAuthorityDto> implements UserAuthorityApi {
    /**
     * 服务对象
     */
    @Autowired
    private UserAuthorityService service;

    @Override
    public BaseEntityService<UserAuthority> getService() {
        return service;
    }

}