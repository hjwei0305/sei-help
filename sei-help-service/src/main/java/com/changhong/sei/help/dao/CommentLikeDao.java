package com.changhong.sei.help.dao;

import com.changhong.sei.help.entity.CommentLike;
import com.changhong.sei.core.dao.BaseEntityDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论点赞记录(CommentLike)数据库访问类
 *
 * @author sei
 * @since 2020-06-04 10:11:33
 */
@Repository
public interface CommentLikeDao extends BaseEntityDao<CommentLike> {

    /**
     * 根据话题id和用户id查找下面评论点赞记录
     * @param topicId 话题id
     * @param userId 用户id
     * @return 评论点赞记录列表
     */
    List<CommentLike> findByCommentTopicIdAndUserId(String topicId, String userId);

    /**
     * 根据评论id和用户id查找点赞记录
     * @param userId 用户id
     * @param commentId 评论id
     * @return 评论点赞记录列表
     */
    CommentLike findByUserIdAndCommentId(String userId, String commentId);

}