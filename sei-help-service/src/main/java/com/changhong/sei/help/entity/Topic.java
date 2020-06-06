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
 * 话题(Topic)实体类
 *
 * @author sei
 * @since 2020-06-04 10:07:32
 */
@Entity
@Table(name = "topic")
@DynamicInsert
@DynamicUpdate
public class Topic extends BaseAuditableEntity implements Serializable {
private static final long serialVersionUID = -99759720216322498L;
    /**
     * 收藏总数
     */
    @Column(name = "collect_count")
    private Integer collectCount;
    /**
     * 评论总数
     */
    @Column(name = "comment_count")
    private Integer commentCount;
    /**
     * 主题内容
     */
    @Column(name = "content")
    private String content;
    /**
     * 是否加精
     */
    @Column(name = "is_good")
    private Boolean good;
    /**
     * 话题分类
     */
    @Column(name = "tab_id")
    private String tabId;
    /**
     * 业务分类
     */
    @Column(name = "biz_id")
    private String bizId;
    /**
     * 统计分类
     */
    @Column(name = "statis_id")
    private String statisId;
    /**
     * 标题
     */
    @Column(name = "title")
    private String title;
    /**
     * 是否置顶
     */
    @Column(name = "is_top")
    private Boolean top;
    /**
     * 分享链接
     */
    @Column(name = "url")
    private String url;
    /**
     * 查看次数
     */
    @Column(name = "view")
    private Integer view;
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
     * 最近评论时间
     */
    @Column(name = "last_comment_time")
    private Date lastCommentTime;
    /**
     * 最近评论用户
     */
    @Column(name = "last_comment_user_info")
    private String lastCommentUserInfo;

        
    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }
        
    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
        
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
        
    public Boolean getGood() {
        return good;
    }

    public void setGood(Boolean good) {
        this.good = good;
    }
        
    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }
        
    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }
        
    public String getStatisId() {
        return statisId;
    }

    public void setStatisId(String statisId) {
        this.statisId = statisId;
    }
        
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
        
    public Boolean getTop() {
        return top;
    }

    public void setTop(Boolean top) {
        this.top = top;
    }
        
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
        
    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }
        
    public Boolean getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }
        
    public String getUserInfo() {
        Boolean anonymous = getAnonymous();
        if (anonymous != null && anonymous) {
            return "匿名";
        }
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
        
    public Date getLastCommentTime() {
        return lastCommentTime;
    }

    public void setLastCommentTime(Date lastCommentTime) {
        this.lastCommentTime = lastCommentTime;
    }
        
    public String getLastCommentUserInfo() {
        return lastCommentUserInfo;
    }

    public void setLastCommentUserInfo(String lastCommentUserInfo) {
        this.lastCommentUserInfo = lastCommentUserInfo;
    }

}