package com.changhong.sei.help.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (UserAuthority)实体类
 *
 * @author sei
 * @since 2020-06-04 10:12:27
 */
@Entity
@Table(name = "user_authority")
@DynamicInsert
@DynamicUpdate
public class UserAuthority extends BaseAuditableEntity implements Serializable {
private static final long serialVersionUID = 557799452079885375L;
    
    @Column(name = "account")
    private String account;
    
    @Column(name = "authority_code")
    private String authorityCode;
    
    @Column(name = "user_name")
    private String userName;
    
    @Column(name = "authority_name")
    private String authorityName;

        
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
        
    public String getAuthorityCode() {
        return authorityCode;
    }

    public void setAuthorityCode(String authorityCode) {
        this.authorityCode = authorityCode;
    }
        
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
        
    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

}