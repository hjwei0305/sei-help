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
import java.util.HashSet;

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
    private Integer collectCount = 0;
    /**
     * 评论总数
     */
    @ApiModelProperty(value = "评论总数")
    private Integer commentCount = 0;
    /**
     * 主题内容
     */
    @NotBlank
    @ApiModelProperty(value = "主题内容")
    private String content;
    /**
     * 是否加精
     */
    @ApiModelProperty(value = "是否加精")
    private Boolean good = Boolean.FALSE;
    /**
     * 话题分类
     */
    @NotBlank
    @ApiModelProperty(value = "话题分类")
    private String tabId;
    /**
     * 业务分类
     */
    @NotBlank
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
    @NotBlank
    @ApiModelProperty(value = "标题")
    private String title;
    /**
     * 是否置顶
     */
    @ApiModelProperty(value = "是否置顶")
    private Boolean top = Boolean.FALSE;
    /**
     * 分享链接
     */
    @ApiModelProperty(value = "分享链接")
    private String url;
    /**
     * 查看次数
     */
    @ApiModelProperty(value = "查看次数")
    private Integer view = 0;
    /**
     * 是否匿名
     */
    @ApiModelProperty(value = "是否匿名")
    private Boolean anonymous = Boolean.FALSE;
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

    /**
     * 话题分类
     */
    @ApiModelProperty(value = "话题分类")
    private String tabName;
    /**
     * 业务分类
     */
    @ApiModelProperty(value = "业务分类")
    private String bizName;
    /**
     * 统计分类
     */
    @ApiModelProperty(value = "统计分类")
    private String statisName;

    /**
     * 文档id列表
     */
    @ApiModelProperty(value = "文档id列表")
    private HashSet<String> docIds;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    protected String creatorName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    protected Date createdDate;
        
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

    public HashSet<String> getDocIds() {
        return docIds;
    }

    public void setDocIds(HashSet<String> docIds) {
        this.docIds = docIds;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getStatisName() {
        return statisName;
    }

    public void setStatisName(String statisName) {
        this.statisName = statisName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}