package com.changhong.sei.help.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.help.api.TopicApi;
import com.changhong.sei.help.dto.TopicDto;
import com.changhong.sei.help.entity.Topic;
import com.changhong.sei.help.service.TopicService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 话题(Topic)控制类
 *
 * @author sei
 * @since 2020-06-04 10:07:38
 */
@RestController
@Api(value = "TopicApi", tags = "话题服务")
@RequestMapping(path = "topic", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TopicController extends BaseEntityController<Topic, TopicDto> implements TopicApi {
    /**
     * 话题服务对象
     */
    @Autowired
    private TopicService service;

    @Override
    public BaseEntityService<Topic> getService() {
        return service;
    }

}