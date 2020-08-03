package com.changhong.sei.help.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.dto.serach.PageInfo;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.help.dao.TopicDao;
import com.changhong.sei.help.entity.Category;
import com.changhong.sei.help.entity.Topic;
import com.changhong.sei.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 话题(Topic)业务逻辑实现类
 *
 * @author sei
 * @since 2020-06-04 10:07:38
 */
@Service("topicService")
public class TopicService extends BaseEntityService<Topic> {
    @Autowired
    private TopicDao topicDao;
    @Autowired
    private CategoryService categoryService;

    @Override
    protected BaseEntityDao<Topic> getDao() {
        return topicDao;
    }

    /**
     * 根据标题查询话题
     *
     * @param title 标题
     * @return 话题
     */
    public Topic findByTitle(String title) {
        Topic topic = new Topic();
        topic.setTitle(title);
        List<Topic> topics = topicDao.findAll(Example.of(topic));
        if (topics.size() > 0) {
            return topics.get(0);
        }
        return null;
    }

    /**
     * 根据分享链接查询话题
     *
     * @param url 分享链接
     * @return 话题
     */
    public Topic findByUrl(String url) {
        Topic topic = new Topic();
        topic.setUrl(url);
        List<Topic> topics = topicDao.findAll(Example.of(topic));
        if (topics.size() > 0) {
            return topics.get(0);
        }
        return null;
    }

    /**
     * 获取评论top10的一周内的话题
     *
     * @return 评论top10的一周内的话题
     */
    public List<Topic> findHotCommentedTopic() {
        Search search = Search.createSearch();
        // 分页
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(1);
        pageInfo.setRows(10);
        search.setPageInfo(pageInfo);

        List<Category> list = categoryService.getUserAuthorizedEntities(null);
        if (!CollectionUtils.isEmpty(list)) {
            List<String> ids = list.parallelStream().map(Category::getId).collect(Collectors.toList());
            search.addFilter(new SearchFilter("tabId", ids, SearchFilter.Operator.IN));
            search.addFilter(new SearchFilter("bizId", ids, SearchFilter.Operator.IN));
        }

        //一周时间
        Date lastWeek = DateUtils.nDaysAgo(7, DateUtils.getCurrentDateTime());

        search.addFilter(new SearchFilter("createdDate", lastWeek, SearchFilter.Operator.GE));
        return topicDao.findByFilters(search);
    }

}