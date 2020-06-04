package com.changhong.sei.help.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 评论(Comment)实体类
 *
 * @author sei
 * @since 2020-06-04 10:09:46
 */
@Entity
@Table(name = "comment")
@DynamicInsert
@DynamicUpdate
public class Comment extends BaseAuditableEntity implements Serializable {
private static final long serialVersionUID = 551976767612107434L;
    /**
     * 评论内容
     */
    @Column(name = "content")
    private String content;
    /**
     * 话题id
     */
    @Column(name = "topic_id")
    private String topicId;
    /**
     * 是否匿名
     */
    @Column(name = "is_anonymous")
    private Boolean anonymous;
    /**
     * 前端显示用户
     */
    @Column(name = "user_info")
    private String userInfo;
    /**
     * 上层评论
     */
    @Column(name = "parent_id")
    private String parentId;
    /**
     * 附件数量
     */
    @Column(name = "doc_count")
    private Integer docCount;
    /**
     * 是否加精
     */
    @Column(name = "is_good")
    private Boolean good;
    /**
     * 点赞数量
     */
    @Column(name = "like_count")
    private Integer likeCount;

        
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
        
    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
        
    public Boolean getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }
        
    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
        
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
        
    public Integer getDocCount() {
        return docCount;
    }

    public void setDocCount(Integer docCount) {
        this.docCount = docCount;
    }
        
    public Boolean getGood() {
        return good;
    }

    public void setGood(Boolean good) {
        this.good = good;
    }
        
    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

}