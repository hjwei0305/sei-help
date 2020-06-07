package com.changhong.sei.help.api;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.help.dto.TopicDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * 首页API
 *
 * @author sei
 * @since 2020-06-04 10:10:23
 */
@Valid
@FeignClient(name = "sei-help", path = "index")
public interface IndexApi {

    /**
     * 首页数据
     * @param pageNo 页码
     * @param pageSize 页数
     * @param tabId 话题分类
     * @param bizId 业务分类
     * @param quickSearchValue 搜索关键词
     * @return
     */
    @GetMapping("/")
    @ApiOperation(value = "查询", notes = "查询")
    ResultData<PageResult<TopicDto>> index(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "15") Integer pageSize, String tabId, String bizId, String quickSearchValue);

    }
