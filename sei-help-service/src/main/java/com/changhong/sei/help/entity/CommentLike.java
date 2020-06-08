package com.changhong.sei.help.entity;

import java.util.Date;
import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
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
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "comment_id",referencedColumnName = "id")
    private Comment comment;
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

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
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