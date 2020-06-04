package com.changhong.sei.help.service;

import com.changhong.sei.help.entity.CommentLike;
import com.changhong.sei.help.dao.CommentLikeDao;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

}