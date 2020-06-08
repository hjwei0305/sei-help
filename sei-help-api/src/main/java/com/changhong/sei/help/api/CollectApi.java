package com.changhong.sei.help.api;

import com.changhong.sei.core.dto.ResultData;
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

    /**
     * 根据话题id取消收藏
     * @param topicId 话题id
     * @return 结果
     */
    @DeleteMapping(path = "deleteByTopicId/{topicId}")
    @ApiOperation(value = "根据话题id取消收藏", notes = "根据话题id取消收藏")
    ResultData<String> deleteByTopicId(@PathVariable("topicId") String topicId);

}