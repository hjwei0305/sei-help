package com.changhong.sei.help.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.IRank;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.auth.AuthEntityData;
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

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        List<Category> list = service.getUserAuthorizedEntities(null);
        List<Category> result = list.stream().filter(p ->
                CategoryType.tab.equals(p.getType())
                        && Boolean.FALSE.equals(p.getDeleted()))
                .sorted(Comparator.comparing(Category::getRank))
                .collect(Collectors.toList());
        return ResultData.success(convertToDtos(result));
    }

    /**
     * 获取业务分类清单
     *
     * @return 业务分类清单
     */
    @Override
    public ResultData<List<CategoryDto>> bizList(@PathVariable("parentId") String parentId) {
        List<Category> list = service.getUserAuthorizedEntities(null);
        List<Category> result = list.stream().filter(p ->
                CategoryType.biz.equals(p.getType())
                        && Boolean.FALSE.equals(p.getDeleted()
                        && parentId.equals(p.getParentId())))
                        .sorted(Comparator.comparing(Category::getRank))
                        .collect(Collectors.toList());
        return ResultData.success(convertToDtos(result));
    }

    /**
     * 获取统计分类清单
     *
     * @return 统计分类清单
     */
    @Override
    public ResultData<List<CategoryDto>> statisList() {
        List<Category> list = service.getUserAuthorizedEntities(null);
        List<Category> result = list.stream().filter(p ->
                CategoryType.statis.equals(p.getType())
                        && Boolean.FALSE.equals(p.getDeleted()))
                .sorted(Comparator.comparing(Category::getRank))
                .collect(Collectors.toList());
        return ResultData.success(convertToDtos(result));
    }

    /**
     * 通过业务实体Id清单获取数据权限实体清单
     *
     * @param ids 业务实体Id清单
     * @return 数据权限实体清单
     */
    @Override
    public ResultData<List<AuthEntityData>> getAuthEntityDataByIds(List<String> ids) {
        return ResultData.success(service.getAuthEntityDataByIds(ids));
    }

    /**
     * 获取当前用户有权限的业务实体清单
     *
     * @param featureCode 功能项代码
     * @return 有权限的业务实体清单
     */
    @Override
    public ResultData<List<CategoryDto>> getUserAuthorizedEntities(String featureCode) {
        return ResultData.success(convertToDtos(service.getUserAuthorizedEntities(featureCode)));
    }

    /**
     * 获取所有数据权限实体清单
     *
     * @return 数据权限实体清单
     */
    @Override
    public ResultData<List<AuthEntityData>> findAllAuthEntityData() {
        return ResultData.success(service.findAllAuthEntityData());
    }
}