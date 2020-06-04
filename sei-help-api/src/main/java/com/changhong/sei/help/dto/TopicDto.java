package com.changhong.sei.help.dto;

import java.util.Date;
import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

/**
 * 话题(Topic)DTO类
 *
 * @author sei
 * @since 2020-06-04 10:07:38
 */
@ApiModel(description = "话题DTO")
public class TopicDto extends BaseEntityDto {
private static final long serialVersionUID = 841137223187422923L;
    /**
     * 收藏总数
     */
    @ApiModelProperty(value = "收藏总数")
    private Integer collectCount;
    /**
     * 评论总数
     */
    @ApiModelProperty(value = "评论总数")
    private Integer commentCount;
    /**
     * 主题内容
     */
    @ApiModelProperty(value = "主题内容")
    private String content;
    /**
     * 是否加精
     */
    @ApiModelProperty(value = "是否加精")
    private Boolean good;
    /**
     * 话题分类
     */
    @ApiModelProperty(value = "话题分类")
    private String tabId;
    /**
     * 业务分类
     */
    @ApiModelProperty(value = "业务分类")
    private String bizId;
    /**
     * 统计分类
     */
    @ApiModelProperty(value = "统计分类")
    private String statisId;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;
    /**
     * 是否置顶
     */
    @ApiModelProperty(value = "是否置顶")
    private Boolean top;
    /**
     * 分享链接
     */
    @ApiModelProperty(value = "分享链接")
    private String url;
    /**
     * 查看次数
     */
    @ApiModelProperty(value = "查看次数")
    private Integer view;
    /**
     * 是否匿名
     */
    @ApiModelProperty(value = "是否匿名")
    private Boolean anonymous;
    /**
     * 前端显示用户
     */
    @ApiModelProperty(value = "前端显示用户")
    private String userInfo;
    /**
     * 最近评论时间
     */
    @ApiModelProperty(value = "最近评论时间")
    private Date lastCommentTime;
    /**
     * 最近评论用户
     */
    @ApiModelProperty(value = "最近评论用户")
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