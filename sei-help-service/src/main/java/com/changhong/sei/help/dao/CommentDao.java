package com.changhong.sei.help.dao;

import com.changhong.sei.help.entity.Comment;
import com.changhong.sei.core.dao.BaseEntityDao;
import org.springframework.stereotype.Repository;

/**
 * 评论(Comment)数据库访问类
 *
 * @author sei
 * @since 2020-06-04 10:09:46
 */
@Repository
public interface CommentDao extends BaseEntityDao<Comment> {

}