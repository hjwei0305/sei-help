package com.changhong.sei.help.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.help.entity.Topic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 话题(Topic)数据库访问类
 *
 * @author sei
 * @since 2020-06-04 10:07:35
 */
@Repository
public interface TopicDao extends BaseEntityDao<Topic> {

    /**
     * 获取一段时间内的话题
     * @param dateAfter 开始时间
     * @param pageable 分页参数
     * @return List<Topic>
     */
    @Query("select t from Topic as t where t.createdDate >= :dateAfter")
    List<Topic> findInTimeTopic(@Param("dateAfter") Date dateAfter, Pageable pageable);

}