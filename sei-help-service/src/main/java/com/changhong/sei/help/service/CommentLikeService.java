package com.changhong.sei.help.service;

import com.changhong.sei.help.entity.CommentLike;
import com.changhong.sei.help.dao.CommentLikeDao;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 评论点赞记录(CommentLike)业务逻辑实现类
 *
 * @author sei
 * @since 2020-06-04 10:11:33
 */
@Service("commentLikeService")
public class CommentLikeService extends BaseEntityService<CommentLike> {
    @Autowired
    private CommentLikeDao dao;

    @Override
    protected BaseEntityDao<CommentLike> getDao() {
        return dao;
    }

    /**
     * 根据话题id和用户id查找下面评论点赞记录
     * @param topicId 话题id
     * @param userId 用户id
     * @return 评论点赞记录列表
     */
    public List<CommentLike> findByTopicIdAndUserId(String topicId, String userId){
        return dao.findByCommentTopicIdAndUserId(topicId,userId);
    }

    /**
     * 根据评论id和用户id查找点赞记录
     * @param userId 用户id
     * @param commentId 评论id
     * @return 评论点赞记录列表
     */
    public CommentLike findByUserIdAndCommentId(String userId,String commentId){
        return dao.findByUserIdAndCommentId(userId,commentId);
    }

}