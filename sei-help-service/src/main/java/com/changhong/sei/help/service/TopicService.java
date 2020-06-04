package com.changhong.sei.help.entity.service;

import com.changhong.sei.help.entity.entity.Topic;
import com.changhong.sei.help.entity.dao.TopicDao;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 话题(Topic)业务逻辑实现类
 *
 * @author sei
 * @since 2020-06-04 10:07:38
 */
@Service("topicService")
public class TopicService extends BaseEntityService<Topic> {
    @Autowired
    private TopicDao dao;

    @Override
    protected BaseEntityDao<Topic> getDao() {
        return dao;
    }

}