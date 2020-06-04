package com.changhong.sei.help.dao;

import com.changhong.sei.help.entity.CommentLike;
import com.changhong.sei.core.dao.BaseEntityDao;
import org.springframework.stereotype.Repository;

/**
 * 评论点赞记录(CommentLike)数据库访问类
 *
 * @author sei
 * @since 2020-06-04 10:11:33
 */
@Repository
public interface CommentLikeDao extends BaseEntityDao<CommentLike> {

}