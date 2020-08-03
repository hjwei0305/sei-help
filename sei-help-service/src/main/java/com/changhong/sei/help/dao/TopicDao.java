package com.changhong.sei.help.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.help.entity.Topic;
import org.springframework.stereotype.Repository;

/**
 * 话题(Topic)数据库访问类
 *
 * @author sei
 * @since 2020-06-04 10:07:35
 */
@Repository
public interface TopicDao extends BaseEntityDao<Topic> {

}