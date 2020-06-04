package com.changhong.sei.help.controller;

import com.changhong.sei.help.api.CollectApi;
import com.changhong.sei.help.dto.CollectDto;
import com.changhong.sei.help.entity.Collect;
import com.changhong.sei.help.service.CollectService;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;

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
    private CollectService service;

    @Override
    public BaseEntityService<Collect> getService() {
        return service;
    }

}