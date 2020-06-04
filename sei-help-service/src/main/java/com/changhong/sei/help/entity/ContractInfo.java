package com.changhong.sei.help.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 联系方式(ContractInfo)实体类
 *
 * @author sei
 * @since 2020-06-04 10:11:44
 */
@Entity
@Table(name = "contract_info")
@DynamicInsert
@DynamicUpdate
public class ContractInfo extends BaseAuditableEntity implements Serializable {
private static final long serialVersionUID = 270785040364396367L;
    /**
     * 名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 排序
     */
    @Column(name = "rank")
    private Integer rank;
    /**
     * 电话号
     */
    @Column(name = "tel_number")
    private String telNumber;

        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
        
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
        
    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

}