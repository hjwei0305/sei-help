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
 * 话题收藏(Collect)DTO类
 *
 * @author sei
 * @since 2020-06-04 10:10:36
 */
@ApiModel(description = "话题收藏DTO")
public class CollectDto extends BaseEntityDto {
private static final long serialVersionUID = 595828152261039802L;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date inTime;
    /**
     * 话题
     */
    @ApiModelProperty(value = "话题")
    private TopicDto topic;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private String userId;
    /**
     * 用户信息
     */
    @ApiModelProperty(value = "用户信息")
    private String userInfo;

        
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

    public TopicDto getTopic() {
        return topic;
    }

    public void setTopic(TopicDto topic) {
        this.topic = topic;
    }
}