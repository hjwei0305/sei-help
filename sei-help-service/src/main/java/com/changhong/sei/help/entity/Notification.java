package com.changhong.sei.help.entity;

import java.util.Date;

import com.changhong.sei.core.dto.serializer.EnumJsonSerializer;
import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.changhong.sei.core.entity.BaseEntity;
import com.changhong.sei.help.dto.NotificationAction;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 通知(Notification)实体类
 *
 * @author sei
 * @since 2020-06-04 10:12:17
 */
@Entity
@Table(name = "notification")
@DynamicInsert
@DynamicUpdate
public class Notification extends BaseEntity implements Serializable {
private static final long serialVersionUID = 827150958670329242L;
    /**
     * 动作: REPLY, COMMENT, COLLECT
     */
    @Enumerated
    @JsonSerialize(using = EnumJsonSerializer.class)
    @Column(name = "action")
    private NotificationAction action;
    /**
     * 创建时间
     */
    @Column(name = "in_time")
    private Date inTime;
    /**
     * 是否已读
     */
    @Column(name = "is_read")
    private Boolean read;
    /**
     * 通知对象ID
     */
    @Column(name = "target_user_id")
    private String targetUserId;
    /**
     * 话题id
     */
    @ManyToOne
    @JoinColumn(name = "topic_id",referencedColumnName = "id")
    private Topic topic;
    /**
     * 创建人id
     */
    @Column(name = "user_id")
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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topicId) {
        this.topic = topicId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}