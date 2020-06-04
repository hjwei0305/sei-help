package com.changhong.sei.help.dto;


import com.changhong.sei.annotation.Remark;

/**
 * 实现功能：
 *
 * @author 马超(Vision.Mac)
 * @version 1.0.00  2019-10-31 17:02
 */
public enum NotificationAction {

    /**
     * 回复评论通知
     */
    @Remark("回复评论通知")
    REPLY,
    /**
     * 业务
     */
    @Remark("收到评论通知")
    COMMENT,
    /**
     * 收藏通知
     */
    @Remark("话题被收藏通知")
    COLLECT
}
