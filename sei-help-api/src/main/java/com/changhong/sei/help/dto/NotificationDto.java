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
    @ApiModelProperty(value = "动作:REPLY,COMMENT,COLLECT")
    private String action;
    /**
     * 创建时间
     */
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
    @ApiModelProperty(value = "通知对象ID")
    private String targetUserId;
    /**
     * 话题id
     */
    @ApiModelProperty(value = "话题id")
    private String topicId;
    /**
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id")
    private String userId;

        
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
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

}