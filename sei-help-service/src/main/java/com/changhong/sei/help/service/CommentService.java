package com.changhong.sei.help.service;

import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.help.entity.Comment;
import com.changhong.sei.help.dao.CommentDao;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 评论(Comment)业务逻辑实现类
 *
 * @author sei
 * @since 2020-06-04 10:09:46
 */
@Service("commentService")
public class CommentService extends BaseEntityService<Comment> {
    @Autowired
    private CommentDao dao;

    @Override
    protected BaseEntityDao<Comment> getDao() {
        return dao;
    }

    /**
     *
     * @param topicId
     * @return
     */
    public List<Comment> findByTopicId(String topicId) {
        return dao.findByTopicIdOrderByCreatedDateAsc(topicId);
    }

    /**
     * 根据话题id删除全部评论
     * @param topicId 话题id
     */
    public void deleteByTopicId(String topicId) {
        dao.deleteByTopicId(topicId);
    }

    /**
     * 查找最新一条评论
     * @param topicId
     * @returns
     */
    public Comment findFirstByTopicIdOrderByInTimeDesc(String topicId) {
        return dao.findFirstByTopicIdOrderByCreatedDateDesc(topicId);
    }

}