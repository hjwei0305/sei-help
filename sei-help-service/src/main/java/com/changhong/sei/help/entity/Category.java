package com.changhong.sei.help.entity;

import com.changhong.sei.core.dto.IRank;
import com.changhong.sei.core.dto.auth.IDataAuthEntity;
import com.changhong.sei.core.dto.serializer.EnumJsonSerializer;
import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.changhong.sei.core.entity.ICodeUnique;
import com.changhong.sei.core.entity.ISoftDelete;
import com.changhong.sei.help.dto.CategoryType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 话题分类(Category)实体类
 *
 * @author sei
 * @since 2020-06-04 10:10:23
 */
@Entity
@Table(name = "category")
@DynamicInsert
@DynamicUpdate
public class Category extends BaseAuditableEntity implements Serializable, IRank, ICodeUnique, IDataAuthEntity {
private static final long serialVersionUID = 886059933024602107L;

    /**
     * 代码
     */
    @Column(name = "code")
    private String code;
    /**
     * 分类名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 分类类型
     */
    @Enumerated
    @JsonSerialize(using = EnumJsonSerializer.class)
    @Column(name = "type")
    private CategoryType type;
    /**
     * 是否删除
     */
    @Column(name = "deleted")
    private Boolean deleted;
    /**
     * 排序
     */
    @Column(name = "rank")
    private Integer rank;

    /**
     * 父节点id
     */
    @Column(name = "parent_id")
    private String parentId;

        
    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }
        
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getTenantCode() {
        return null;
    }

    @Override
    public void setTenantCode(String tenantCode) {

    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public Integer getRank() {
        return this.rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}