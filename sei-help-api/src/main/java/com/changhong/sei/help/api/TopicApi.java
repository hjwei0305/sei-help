package com.changhong.sei.help.api;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.help.dto.TopicDto;
import com.changhong.sei.core.api.BaseEntityApi;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 话题(Topic)API
 *
 * @author sei
 * @since 2020-06-04 10:07:39
 */
@Valid
@FeignClient(name = "sei-help", path = "topic")
public interface TopicApi extends BaseEntityApi<TopicDto> {

    /**
     * 获取话题明细
     * @param id 话题id
     * @param mdrender 判断内容是否要被渲染成html内容
     * @return 话题明细
     */
    @GetMapping("/detail")
    @ApiOperation(value = "话题明细", notes = "话题明细")
    ResultData<Map<String, Object>> detail(String id, @RequestParam(defaultValue = "false") Boolean mdrender);

    /**
     * 加精/取消加精
     * @param id 话题id
     * @return 结果
     */
    @PostMapping("/good/{id}")
    @ApiOperation(value = "加精/取消加精", notes = "加精/取消加精")
    ResultData<String> good(@PathVariable("id") String id);

    /**
     * 置顶/取消置顶
     * @param id 话题id
     * @return 结果
     */
    @PostMapping("/top/{id}")
    @ApiOperation(value = "置顶/取消置顶", notes = "置顶/取消置顶")
    ResultData<String> top(@PathVariable("id") String id);

    /**
     * 获取热门评论话题
     * @return 热门评论话题
     */
    @GetMapping("/hot")
    @ApiOperation(value = "获取热门评论话题", notes = "获取热门评论话题")
    ResultData<List<TopicDto>> hot();


}