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
import java.io.Serializable;

/**
 * 数据字典子项(DataDictItem)实体类
 *
 * @author sei
 * @since 2020-06-04 10:12:05
 */
@Entity
@Table(name = "data_dict_item")
@DynamicInsert
@DynamicUpdate
public class DataDictItem extends BaseAuditableEntity implements Serializable, IFrozen, IRank, ICodeUnique {
private static final long serialVersionUID = -68966533359914382L;
    /**
     * 字典类型code
     */
    @NotBlank
    @Column(name = "dict_category_code")
    private String categoryCode;
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


    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

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