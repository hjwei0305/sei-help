package com.changhong.sei.help.entity;

import com.changhong.sei.core.dto.IRank;
import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.changhong.sei.core.entity.ICodeUnique;
import com.changhong.sei.core.entity.IFrozen;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 数据字典(DataDict)实体类
 *
 * @author sei
 * @since 2020-06-04 10:11:55
 */
@Entity
@Table(name = "data_dict")
@DynamicInsert
@DynamicUpdate
public class DataDict extends BaseAuditableEntity implements Serializable, IFrozen, IRank, ICodeUnique {
private static final long serialVersionUID = -96168698401228991L;
    /**
     * 代码
     */
    @NotBlank
    @Column(name = "code")
    private String code;
    /**
     * 是否冻结
     */
    @Column(name = "frozen")
    private Boolean frozen;
    /**
     * 名称
     */
    @NotBlank
    @Column(name = "name")
    private String name;
    /**
     * 排序
     */
    @Column(name = "rank")
    private Integer rank;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

        
    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }
        
    @Override
    public Boolean getFrozen() {
        return frozen;
    }

    @Override
    public void setFrozen(Boolean frozen) {
        this.frozen = frozen;
    }
        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
        
    @Override
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
        
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}