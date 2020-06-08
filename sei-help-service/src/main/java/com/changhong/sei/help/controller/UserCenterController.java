package com.changhong.sei.help.controller;


import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.*;
import com.changhong.sei.help.api.UserCenterApi;
import com.changhong.sei.help.dto.CollectDto;
import com.changhong.sei.help.dto.CommentDto;
import com.changhong.sei.help.dto.TopicDto;
import com.changhong.sei.help.entity.Collect;
import com.changhong.sei.help.entity.Comment;
import com.changhong.sei.help.entity.Topic;
import com.changhong.sei.help.service.CategoryService;
import com.changhong.sei.help.service.CollectService;
import com.changhong.sei.help.service.CommentService;
import com.changhong.sei.help.service.TopicService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 实现功能：
 *
 * @author 马超(Vision.Mac)
 * @version 1.0.00  2019-07-24 23:59
 */
@RestController
@Api(value = "UserCenterApi", tags = "个人中心服务")
@RequestMapping(path = "user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserCenterController implements UserCenterApi {

    @Autowired
    private TopicService topicService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private CategoryService categoryService;


    /**
     * 获取指定用户创建的话题
     *
     * @param userId           用户id
     * @param pageNo           页码
     * @param pageSize         页数
     * @param quickSearchValue 搜索关键词
     * @return 话题分页列表
     */
    @Override
    public ResultData<PageResult<TopicDto>> topics(String userId, Integer pageNo, Integer pageSize, String quickSearchValue) {
        Search search = Search.createSearch();
        // 分页
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(pageNo);
        pageInfo.setRows(pageSize);
        search.setPageInfo(pageInfo);
        // 排序
        search.addSortOrder(new SearchOrder(Topic.CREATED_DATE, SearchOrder.Direction.DESC));

        search.addQuickSearchProperty("title");
        search.addQuickSearchProperty("content");
        search.setQuickSearchValue(quickSearchValue);
        search.addFilter(new SearchFilter("creatorId", userId));
        PageResult<Topic> pageResult = topicService.findByPage(search);
        ModelMapper mapper = new ModelMapper();
        //转化为DTO
        List<TopicDto> topicDtos = pageResult.getRows().stream().map(p -> mapper.map(p,TopicDto.class)).collect(Collectors.toList());
        // 获取分类缓存
        Map<String, String> map = categoryService.getCategoryCacheMap();
        if (!CollectionUtils.isEmpty(map)) {
            for (TopicDto topic : topicDtos) {
                topic.setTabName(map.get(topic.getTabId()));
                topic.setBizName(map.get(topic.getBizId()));
                topic.setStatisName(map.get(topic.getStatisId()));
            }
        }
        PageResult<TopicDto> pageResultDto = new PageResult<>();
        pageResultDto.setPage(pageResult.getPage());
        pageResultDto.setRecords(pageResult.getRecords());
        pageResultDto.setTotal(pageResult.getTotal());
        pageResultDto.setRows(topicDtos);
        return ResultData.success(pageResultDto);
    }

    /**
     * 获取指定用户创建的评论
     *
     * @param userId           用户id
     * @param pageNo           页码
     * @param pageSize         页数
     * @param quickSearchValue 搜索关键词
     * @return 评论分页列表
     */
    @Override
    public ResultData<PageResult<CommentDto>> comments(String userId, Integer pageNo, Integer pageSize, String quickSearchValue) {
        Search search = Search.createSearch();
        // 分页
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(pageNo);
        pageInfo.setRows(pageSize);
        search.setPageInfo(pageInfo);
        // 排序
        search.addSortOrder(new SearchOrder(Comment.CREATED_DATE, SearchOrder.Direction.DESC));

        search.addQuickSearchProperty("content");
        search.setQuickSearchValue(quickSearchValue);
        search.addFilter(new SearchFilter("creatorId", userId));
        PageResult<Comment> pageResult = commentService.findByPage(search);
        ModelMapper mapper = new ModelMapper();
        //转化为DTO
        List<CommentDto> commentDtos = pageResult.getRows().stream().map(p -> mapper.map(p,CommentDto.class)).collect(Collectors.toList());

        commentDtos.forEach(comment -> {
            if (comment.getParentId() != null) {
                // todo 递归
                Comment parentComment = commentService.findOne(comment.getParentId());
                if (Objects.isNull(parentComment)){
                    parentComment = new Comment();
                    parentComment.setContent("该评论已经删除");
                    parentComment.setId(comment.getParentId());
                }
                comment.setParentComment( mapper.map(parentComment,CommentDto.class));
            }
        });
        PageResult<CommentDto> pageResultDto = new PageResult<>();
        pageResultDto.setPage(pageResult.getPage());
        pageResultDto.setRecords(pageResult.getRecords());
        pageResultDto.setTotal(pageResult.getTotal());
        pageResultDto.setRows(commentDtos);
        return ResultData.success(pageResultDto);
    }

    /**
     * 获取指定用户创建的收藏
     *
     * @param userId           用户id
     * @param pageNo           页码
     * @param pageSize         页数
     * @param quickSearchValue 搜索关键词
     * @return 收藏分页列表
     */
    @Override
    public ResultData<PageResult<CollectDto>> collects(String userId, Integer pageNo, Integer pageSize, String quickSearchValue) {
        Search search = Search.createSearch();
        // 分页
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(pageNo);
        pageInfo.setRows(pageSize);
        search.setPageInfo(pageInfo);
        // 排序
        search.addSortOrder(new SearchOrder(Collect.CREATED_DATE, SearchOrder.Direction.DESC));

        search.addQuickSearchProperty("content");
        search.setQuickSearchValue(quickSearchValue);
        search.addFilter(new SearchFilter("creatorId", userId));
        PageResult<Collect> pageResult = collectService.findByPage(search);
        ModelMapper mapper = new ModelMapper();
        //转化为DTO
        List<CollectDto> collectDtos = pageResult.getRows().stream().map(p -> mapper.map(p,CollectDto.class)).collect(Collectors.toList());
        PageResult<CollectDto> pageResultDto = new PageResult<>();
        pageResultDto.setPage(pageResult.getPage());
        pageResultDto.setRecords(pageResult.getRecords());
        pageResultDto.setTotal(pageResult.getTotal());
        pageResultDto.setRows(collectDtos);
        return ResultData.success(pageResultDto);
    }
}
