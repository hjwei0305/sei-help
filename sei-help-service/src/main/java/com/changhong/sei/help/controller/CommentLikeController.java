package com.changhong.sei.help.controller;

import com.changhong.sei.help.api.CommentLikeApi;
import com.changhong.sei.help.dto.CommentLikeDto;
import com.changhong.sei.help.entity.CommentLike;
import com.changhong.sei.help.service.CommentLikeService;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;

/**
 * 评论点赞记录(CommentLike)控制类
 *
 * @author sei
 * @since 2020-06-04 10:11:33
 */
@RestController
@Api(value = "CommentLikeApi", tags = "评论点赞记录服务")
@RequestMapping(path = "commentLike", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CommentLikeController extends BaseEntityController<CommentLike, CommentLikeDto> implements CommentLikeApi {
    /**
     * 评论点赞记录服务对象
     */
    @Autowired
    private CommentLikeService service;

    @Override
    public BaseEntityService<CommentLike> getService() {
        return service;
    }

}