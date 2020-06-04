package com.changhong.sei.help.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

/**
 * 评论(Comment)DTO类
 *
 * @author sei
 * @since 2020-06-04 10:09:46
 */
@ApiModel(description = "评论DTO")
public class CommentDto extends BaseEntityDto {
private static final long serialVersionUID = -55697659277985866L;
    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    private String content;
    /**
     * 话题id
     */
    @ApiModelProperty(value = "话题id")
    private String topicId;
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
     * 上层评论
     */
    @ApiModelProperty(value = "上层评论")
    private String parentId;
    /**
     * 附件数量
     */
    @ApiModelProperty(value = "附件数量")
    private Integer docCount;
    /**
     * 是否加精
     */
    @ApiModelProperty(value = "是否加精")
    private Boolean good;
    /**
     * 点赞数量
     */
    @ApiModelProperty(value = "点赞数量")
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