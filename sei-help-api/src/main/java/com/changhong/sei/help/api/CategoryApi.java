package com.changhong.sei.help.api;

import com.changhong.sei.core.api.DataAuthEntityApi;
import com.changhong.sei.core.dto.IRank;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.dto.serach.SearchOrder;
import com.changhong.sei.help.dto.CategoryDto;
import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.help.dto.CategoryType;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * 话题分类(Category)API
 *
 * @author sei
 * @since 2020-06-04 10:10:23
 */
@Valid
@FeignClient(name = "sei-help", path = "category")
public interface CategoryApi extends BaseEntityApi<CategoryDto>, DataAuthEntityApi<CategoryDto> {

    /**
     * 新增主题分类
     * @param category 分类
     * @return 保存结果
     */
    @PostMapping("/tab/save")
    @ApiOperation(value = "新增主题分类", notes = "新增主题分类")
    ResultData<CategoryDto> topic(@RequestBody CategoryDto category);

    /**
     * 新增业务分类
     * @param category 分类
     * @return 保存结果
     */
    @PostMapping("/biz/save")
    @ApiOperation(value = "新增业务分类", notes = "新增业务分类")
    ResultData<CategoryDto> biz(@RequestBody CategoryDto category);

    /**
     * 新增统计分类
     * @param category 分类
     * @return 保存结果
     */
    @PostMapping("/statis/save")
    @ApiOperation(value = "新增统计分类", notes = "新增统计分类")
    ResultData<CategoryDto> statis(@RequestBody CategoryDto category);

    /**
     * 获取主题分类清单
     * @return 主题分类清单
     */
    @GetMapping("/tab/list")
    @ApiOperation(value = "主题分类清单", notes = "主题分类清单")
    ResultData<List<CategoryDto>> topicList();

    /**
     * 获取业务分类清单
     * @return 业务分类清单
     */
    @GetMapping("/biz/list/{parentId}")
    @ApiOperation(value = "业务分类清单", notes = "业务分类清单")
    ResultData<List<CategoryDto>> bizList(@PathVariable("parentId") String parentId);

    /**
     * 获取统计分类清单
     * @return 统计分类清单
     */
    @GetMapping("/statis/list")
    @ApiOperation(value = "统计分类清单", notes = "统计分类清单")
    ResultData<List<CategoryDto>> statisList();

}