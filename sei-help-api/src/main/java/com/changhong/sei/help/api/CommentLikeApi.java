package com.changhong.sei.help.api;

import com.changhong.sei.help.dto.CommentLikeDto;
import com.changhong.sei.core.api.BaseEntityApi;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 评论点赞记录(CommentLike)API
 *
 * @author sei
 * @since 2020-06-04 10:11:33
 */
@Valid
@FeignClient(name = "sei-help", path = "commentLike")
public interface CommentLikeApi extends BaseEntityApi<CommentLikeDto> {

}