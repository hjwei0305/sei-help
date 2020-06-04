package com.changhong.sei.help.api;

import com.changhong.sei.help.dto.CommentDto;
import com.changhong.sei.core.api.BaseEntityApi;
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

}