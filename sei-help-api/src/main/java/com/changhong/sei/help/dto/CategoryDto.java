package com.changhong.sei.help.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import com.changhong.sei.core.dto.serializer.EnumJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

/**
 * 话题分类(Category)DTO类
 *
 * @author sei
 * @since 2020-06-04 10:10:23
 */
@ApiModel(description = "话题分类DTO")
public class CategoryDto extends BaseEntityDto {

    private static final long serialVersionUID = 332493700917563622L;

    public static final String FIELD_CATEGORY_TYPE = "type";
    public static final String FIELD_PARENT_ID = "parentId";
    public static final String FIELD_DELETED = "deleted";

    /**
     * 代码
     */
    @ApiModelProperty(value = "代码")
    private String code;
    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    private String name;
    /**
     * 分类类型
     */
    @ApiModelProperty(value = "分类类型")
    @JsonSerialize(using = EnumJsonSerializer.class)
    private CategoryType type;
    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private Boolean deleted = Boolean.FALSE;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer rank = 0;

    /**
     * 父节点id
     */
    @ApiModelProperty(value = "父节点Id")
    private String parentId;

        
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
        
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

}