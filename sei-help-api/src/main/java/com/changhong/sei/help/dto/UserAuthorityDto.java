package com.changhong.sei.help.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

/**
 * (UserAuthority)DTO类
 *
 * @author sei
 * @since 2020-06-04 10:12:27
 */
@ApiModel(description = "DTO")
public class UserAuthorityDto extends BaseEntityDto {
private static final long serialVersionUID = 154602534669566842L;
    
    
    private String account;
    
    
    private String authorityCode;
    
    
    private String userName;
    
    
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