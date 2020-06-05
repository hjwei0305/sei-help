package com.changhong.sei.help.api;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.help.dto.CommentDto;
import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.help.dto.CommentLikeDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 评论(Comment)API
 *
 * @author sei
 * @since 2020-06-04 10:09:47
 */
@Valid
@FeignClient(name = "sei-help", path = "comment")
public interface CommentApi extends BaseEntityApi<CommentDto> {

    /**
     * 点赞评论
     * @param commentLike 点赞信息
     * @return 结果
     */
    @PostMapping("/like")
    @ApiOperation(value = "点赞评论", notes = "点赞评论")
    ResultData<String> like(@RequestBody CommentLikeDto commentLike);

    /**
     * 取消点赞评论
     * @param commentLike 点赞信息
     * @return 结果
     */
    @PostMapping("/dislike")
    @ApiOperation(value = "取消点赞评论", notes = "取消点赞取消评论")
    ResultData<String> dislike(@RequestBody CommentLikeDto commentLike);

    /**
     * 加精/取消加精
     * @param id 评论id
     * @return 结果
     */
    @PostMapping("/good/{id}")
    @ApiOperation(value = "评论加精/取消加精", notes = "评论加精/取消加精")
    ResultData<String> good(@PathVariable("id")String id);

}