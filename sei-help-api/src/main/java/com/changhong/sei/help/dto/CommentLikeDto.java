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
 * 评论点赞记录(CommentLike)DTO类
 *
 * @author sei
 * @since 2020-06-04 10:11:33
 */
@ApiModel(description = "评论点赞记录DTO")
public class CommentLikeDto extends BaseEntityDto {
private static final long serialVersionUID = 930996347659530814L;
    /**
     * 是否匿名
     */
    @ApiModelProperty(value = "是否匿名")
    private Boolean anonymous;
    /**
     * 评论id
     */
    @NotBlank
    @ApiModelProperty(value = "评论id")
    private String commentId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date inTime;
    /**
     * 用户信息
     */
    @ApiModelProperty(value = "用户信息")
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
        
    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

}