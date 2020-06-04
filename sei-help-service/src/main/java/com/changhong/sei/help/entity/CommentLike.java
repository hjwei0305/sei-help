package com.changhong.sei.help.entity;

import java.util.Date;
import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 评论点赞记录(CommentLike)实体类
 *
 * @author sei
 * @since 2020-06-04 10:11:33
 */
@Entity
@Table(name = "comment_like")
@DynamicInsert
@DynamicUpdate
public class CommentLike extends BaseAuditableEntity implements Serializable {
private static final long serialVersionUID = 755866235997215253L;
    /**
     * 是否匿名
     */
    @Column(name = "is_anonymous")
    private Boolean anonymous;
    /**
     * 评论id
     */
    @Column(name = "comment_id")
    private String commentId;
    /**
     * 创建时间
     */
    @Column(name = "in_time")
    private Date inTime;
    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;
    /**
     * 用户信息
     */
    @Column(name = "user_info")
    private String userInfo;

        
    public Boolean getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }
        
    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
        
    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }
        
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
        
    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

}