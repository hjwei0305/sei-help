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
 * 通知(Notification)实体类
 *
 * @author sei
 * @since 2020-06-04 10:12:17
 */
@Entity
@Table(name = "notification")
@DynamicInsert
@DynamicUpdate
public class Notification extends BaseAuditableEntity implements Serializable {
private static final long serialVersionUID = 827150958670329242L;
    /**
     * 动作: REPLY, COMMENT, COLLECT
     */
    @Column(name = "action")
    private String action;
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
    @Column(name = "topic_id")
    private String topicId;
    /**
     * 创建人id
     */
    @Column(name = "user_id")
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