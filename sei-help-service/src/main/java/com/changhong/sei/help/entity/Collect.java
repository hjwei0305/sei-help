package com.changhong.sei.help.entity;

import java.util.Date;
import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
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
     * 话题id
     */
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "topic_id",referencedColumnName = "id")
    private Topic topic;
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


    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
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