package com.changhong.sei.help.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.IRank;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.dto.serach.SearchOrder;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.service.bo.OperateResult;
import com.changhong.sei.core.service.bo.OperateResultWithData;
import com.changhong.sei.help.api.CategoryApi;
import com.changhong.sei.help.dto.CategoryDto;
import com.changhong.sei.help.dto.CategoryType;
import com.changhong.sei.help.entity.Category;
import com.changhong.sei.help.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * 话题分类(Category)控制类
 *
 * @author sei
 * @since 2020-06-04 10:10:23
 */
@RestController
@Api(value = "CategoryApi", tags = "话题分类服务")
@RequestMapping(path = "category", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CategoryController extends BaseEntityController<Category, CategoryDto> implements CategoryApi {
    /**
     * 话题分类服务对象
     */
    @Autowired
    private CategoryService service;

    @Override
    public BaseEntityService<Category> getService() {
        return service;
    }

    /**
     * 新增主题分类
     *
     * @param category 分类
     * @return 保存结果
     */
    @Override
    public ResultData<CategoryDto> topic(@RequestBody CategoryDto category) {
        category.setType(CategoryType.tab);
        OperateResultWithData<Category> result = service.save(convertToEntity(category));
        if (result.successful()) {
            return ResultData.success(convertToDto(result.getData()));
        } else {
            return ResultData.fail(result.getMessage());
        }
    }

    /**
     * 新增业务分类
     *
     * @param category 分类
     * @return 保存结果
     */
    @Override
    public ResultData<CategoryDto> biz(@RequestBody CategoryDto category) {
        category.setType(CategoryType.biz);
        OperateResultWithData<Category> result = service.save(convertToEntity(category));
        if (result.successful()) {
            return ResultData.success(convertToDto(result.getData()));
        } else {
            return ResultData.fail(result.getMessage());
        }
    }

    /**
     * 新增统计分类
     *
     * @param category 分类
     * @return 保存结果
     */
    @Override
    public ResultData<CategoryDto> statis(@RequestBody CategoryDto category) {
        category.setType(CategoryType.statis);
        OperateResultWithData<Category> result = service.save(convertToEntity(category));
        if (result.successful()) {
            return ResultData.success(convertToDto(result.getData()));
        } else {
            return ResultData.fail(result.getMessage());
        }
    }

    /**
     * 删除分类
     *
     * @param id id
     * @return 结果
     */
    @Override
    public ResultData<String> delete(@PathVariable("id") String id) {
        OperateResult result = service.delete(id);
        if (result.successful()) {
            return ResultData.success("ok");
        } else {
            return ResultData.fail(result.getMessage());
        }
    }

    /**
     * 获取主题分类清单
     *
     * @return 主题分类清单
     */
    @Override
    public ResultData<List<CategoryDto>> topicList() {
        Search search = Search.createSearch();
        search.addFilter(new SearchFilter(CategoryDto.FIELD_CATEGORY_TYPE, CategoryType.tab));
        search.addFilter(new SearchFilter(CategoryDto.FIELD_DELETED, Boolean.FALSE));
        search.addSortOrder(SearchOrder.asc(IRank.RANK));
        List<Category> list = service.findByFilters(search);
        return ResultData.success(convertToDtos(list));
    }

    /**
     * 获取业务分类清单
     *
     * @return 业务分类清单
     */
    @Override
    public ResultData<List<CategoryDto>> bizList(@PathVariable("parentId") String parentId) {
        Search search = Search.createSearch();
        search.addFilter(new SearchFilter(CategoryDto.FIELD_CATEGORY_TYPE, CategoryType.biz));
        search.addFilter(new SearchFilter(CategoryDto.FIELD_DELETED, Boolean.FALSE));
        search.addFilter(new SearchFilter(CategoryDto.FIELD_PARENT_ID, parentId));
        search.addSortOrder(SearchOrder.asc(IRank.RANK));
        List<Category> list = service.findByFilters(search);
        return ResultData.success(convertToDtos(list));
    }

    /**
     * 获取统计分类清单
     *
     * @return 统计分类清单
     */
    @Override
    public ResultData<List<CategoryDto>> statisList() {
        Search search = Search.createSearch();
        search.addFilter(new SearchFilter(CategoryDto.FIELD_CATEGORY_TYPE, CategoryType.statis));
        search.addFilter(new SearchFilter(CategoryDto.FIELD_DELETED, Boolean.FALSE));
        search.addSortOrder(SearchOrder.asc(IRank.RANK));
        List<Category> list = service.findByFilters(search);
        return ResultData.success(convertToDtos(list));
    }

}