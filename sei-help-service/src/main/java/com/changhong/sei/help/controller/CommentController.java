package com.changhong.sei.help.controller;

import com.changhong.sei.help.api.CommentApi;
import com.changhong.sei.help.dto.CommentDto;
import com.changhong.sei.help.entity.Comment;
import com.changhong.sei.help.service.CommentService;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;

/**
 * 评论(Comment)控制类
 *
 * @author sei
 * @since 2020-06-04 10:09:46
 */
@RestController
@Api(value = "CommentApi", tags = "评论服务")
@RequestMapping(path = "comment", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CommentController extends BaseEntityController<Comment, CommentDto> implements CommentApi {
    /**
     * 评论服务对象
     */
    @Autowired
    private CommentService service;

    @Override
    public BaseEntityService<Comment> getService() {
        return service;
    }

}