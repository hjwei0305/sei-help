package com.changhong.sei.help.api;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.help.dto.CollectDto;
import com.changhong.sei.help.dto.CommentDto;
import com.changhong.sei.help.dto.TopicDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * 用户中心API
 *
 * @author sei
 * @since 2020-06-04 10:10:23
 */
@Valid
@FeignClient(name = "sei-help", path = "userCenter")
public interface UserCenterApi {

    /**
     * 获取指定用户创建的话题
     * @param userId 用户id
     * @param pageNo 页码
     * @param pageSize 页数
     * @param quickSearchValue 搜索关键词
     * @return 话题分页列表
     */
    @GetMapping("/{userId}/topics")
    ResultData<PageResult<TopicDto>> topics(@PathVariable String userId,
                                            @RequestParam(defaultValue = "1") Integer pageNo,
                                            @RequestParam(defaultValue = "15")Integer pageSize,
                                            String quickSearchValue);

    /**
     * 获取指定用户创建的评论
     * @param userId 用户id
     * @param pageNo 页码
     * @param pageSize 页数
     * @param quickSearchValue 搜索关键词
     * @return 评论分页列表
     */
    @GetMapping("/{userId}/comments")
    ResultData<PageResult<CommentDto>> comments(@PathVariable String userId,
                                                @RequestParam(defaultValue = "1") Integer pageNo,
                                                @RequestParam(defaultValue = "15")Integer pageSize,
                                                String quickSearchValue);

    /**
     * 获取指定用户创建的收藏
     * @param userId 用户id
     * @param pageNo 页码
     * @param pageSize 页数
     * @param quickSearchValue 搜索关键词
     * @return 收藏分页列表
     */
    @GetMapping("/{userId}/collects")
    ResultData<PageResult<CollectDto>> collects(@PathVariable String userId,
                                                       @RequestParam(defaultValue = "1") Integer pageNo,
                                                       @RequestParam(defaultValue = "15")Integer pageSize,
                                                       String quickSearchValue);

}
