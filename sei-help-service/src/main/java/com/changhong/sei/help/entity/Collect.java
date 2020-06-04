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
 * 话题收藏(Collect)实体类
 *
 * @author sei
 * @since 2020-06-04 10:10:36
 */
@Entity
@Table(name = "collect")
@DynamicInsert
@DynamicUpdate
public class Collect extends BaseAuditableEntity implements Serializable {
private static final long serialVersionUID = -23882721203709158L;
    /**
     * 创建时间
     */
    @Column(name = "in_time")
    private Date inTime;
    /**
     * 话题id
     */
    @Column(name = "topic_id")
    private String topicId;
    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;
    /**
     * 用户信息
     */
    @Column(name = "user_info")
    private String userInfo;

        
    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
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
        
    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

}