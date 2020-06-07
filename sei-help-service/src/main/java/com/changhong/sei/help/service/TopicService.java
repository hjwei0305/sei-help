package com.changhong.sei.help.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.help.dao.TopicDao;
import com.changhong.sei.help.entity.Topic;
import com.changhong.sei.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


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

    @Override
    protected BaseEntityDao<Topic> getDao() {
        return topicDao;
    }

    /**
     * 根据标题查询话题
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
     * @return 评论top10的一周内的话题
     */
    public List<Topic> findHotCommentedTopic(){
        //一周时间
        Date lastWeek = DateUtils.nDaysAgo(7,DateUtils.getCurrentDateTime());
        //分页
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "commentCount");
        return topicDao.findInTimeTopic(lastWeek,pageable);
    }

}