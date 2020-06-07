package com.changhong.sei.help.dao;

import com.changhong.sei.help.entity.Comment;
import com.changhong.sei.core.dao.BaseEntityDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论(Comment)数据库访问类
 *
 * @author sei
 * @since 2020-06-04 10:09:46
 */
@Repository
public interface CommentDao extends BaseEntityDao<Comment> {

    /**
     * 根据话题id删除
     * @param topicId 话题id
     */
    void deleteByTopicId(String topicId);

    /**
     * 根据话题id获取最新一条评论
     * @param topicId 话题id
     * @return 最新一条评论
     */
    Comment findFirstByTopicIdOrderByCreatedDateDesc(String topicId);

    /**
     * 根据时间先后获取指定话题评论列表
     * @param topicId 话题id
     * @return 评论列表
     */
    List<Comment> findByTopicIdOrderByCreatedDateAsc(String topicId);

}