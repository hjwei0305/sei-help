package com.changhong.sei.help.dto;

import java.util.Date;
import com.changhong.sei.core.dto.BaseEntityDto;
import com.changhong.sei.core.dto.serializer.EnumJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

/**
 * 通知(Notification)DTO类
 *
 * @author sei
 * @since 2020-06-04 10:12:17
 */
@ApiModel(description = "通知DTO")
public class NotificationDto extends BaseEntityDto {
private static final long serialVersionUID = 263405606088836570L;
    /**
     * 动作: REPLY, COMMENT, COLLECT
     */
    @JsonSerialize(using = EnumJsonSerializer.class)
    @ApiModelProperty(value = "动作:REPLY,COMMENT,COLLECT")
    private NotificationAction action;
    /**
     * 创建时间
     */
    @NotNull
    @ApiModelProperty(value = "创建时间")
    private Date inTime;
    /**
     * 是否已读
     */
    @ApiModelProperty(value = "是否已读")
    private Boolean read;
    /**
     * 通知对象ID
     */
    @NotBlank
    @ApiModelProperty(value = "通知对象ID")
    private String targetUserId;
    /**
     * 话题id
     */
    @NotBlank
    @ApiModelProperty(value = "话题id")
    private String topicId;

    /**
     * 话题标题
     */
    @ApiModelProperty(value = "话题标题")
    private String title;

    /**
     * 转载文章的链接
     */
    @ApiModelProperty(value = "转载文章的链接")
    private String url;

    /**
     * 前端显示用户
     */
    @ApiModelProperty(value = "前端显示用户")
    private String userInfo;

    /**
     * 创建人id
     */
    @NotBlank
    @ApiModelProperty(value = "创建人id")
    private String userId;


    public NotificationAction getAction() {
        return action;
    }

    public void setAction(NotificationAction action) {
        this.action = action;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }
        
    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }
        
    public String getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId;
    }
        
    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
        
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
}