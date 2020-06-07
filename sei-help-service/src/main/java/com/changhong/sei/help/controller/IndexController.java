package com.changhong.sei.help.controller;

import com.changhong.sei.core.controller.DefaultBaseController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.*;
import com.changhong.sei.help.api.IndexApi;
import com.changhong.sei.help.dao.TopicDao;
import com.changhong.sei.help.dto.CommentDto;
import com.changhong.sei.help.dto.TopicDto;
import com.changhong.sei.help.entity.Topic;
import com.changhong.sei.help.service.CategoryService;
import com.changhong.sei.help.service.TopicService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 首页控制类
 *
 * @author sei
 * @since 2020-06-04 10:10:23
 */
@RestController
@Api(value = "IndexApi", tags = "首页服务")
@RequestMapping(path = "index", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class IndexController implements IndexApi {

    @Autowired
    private TopicService topicService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 首页数据
     *
     * @param pageNo           页码
     * @param pageSize         页数
     * @param tabId            话题分类
     * @param bizId            业务分类
     * @param quickSearchValue 搜索关键词
     * @return
     */
    @Override
    public ResultData<PageResult<TopicDto>> index(Integer pageNo, Integer pageSize, String tabId, String bizId, String quickSearchValue) {
        Search search = Search.createSearch();
        // 分页
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(pageNo);
        pageInfo.setRows(pageSize);
        search.setPageInfo(pageInfo);
        // 排序
        search.addSortOrder(new SearchOrder("top", SearchOrder.Direction.DESC));
        search.addSortOrder(new SearchOrder("lastCommentTime", SearchOrder.Direction.DESC));
        search.addSortOrder(new SearchOrder(Topic.CREATED_DATE, SearchOrder.Direction.DESC));

        search.addQuickSearchProperty("title");
        search.addQuickSearchProperty("content");
        search.setQuickSearchValue(quickSearchValue);

        if (!StringUtils.isEmpty(tabId)) {
            if ("good".equals(tabId)) {
                search.addFilter(new SearchFilter("good", Boolean.TRUE));
            } else {
                search.addFilter(new SearchFilter("tabId", tabId));
            }
        }

        if (!StringUtils.isEmpty(bizId)) {
            search.addFilter(new SearchFilter("bizId", bizId));
        }

        PageResult<Topic> topics = topicService.findByPage(search);
        //转化为DTO
        ModelMapper mapper = new ModelMapper();
        List<TopicDto> topicDtos = topics.getRows().stream().map(p -> mapper.map(p,TopicDto.class)).collect(Collectors.toList());
        PageResult<TopicDto> pageResult = new PageResult<>();
        pageResult.setPage(topics.getPage());
        pageResult.setRecords(topics.getRecords());
        pageResult.setTotal(topics.getTotal());
        pageResult.setRows(topicDtos);

        // 获取分类缓存
        Map<String, String> map = categoryService.getCategoryCacheMap();
        if (!CollectionUtils.isEmpty(map)) {
            for (TopicDto topic : topicDtos) {
                topic.setTabName(map.get(topic.getTabId()));
                topic.setBizName(map.get(topic.getBizId()));
                topic.setStatisName(map.get(topic.getStatisId()));
            }
        }

        return ResultData.success(pageResult);
    }
}
