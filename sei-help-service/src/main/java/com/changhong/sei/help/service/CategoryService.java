package com.changhong.sei.help.service;

import com.changhong.sei.core.cache.CacheBuilder;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.service.DataAuthEntityService;
import com.changhong.sei.core.service.bo.OperateResult;
import com.changhong.sei.core.service.bo.OperateResultWithData;
import com.changhong.sei.help.dao.CategoryDao;
import com.changhong.sei.help.entity.Category;
import com.changhong.sei.help.service.cust.BasicIntegration;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 话题分类(Category)业务逻辑实现类
 *
 * @author sei
 * @since 2020-06-04 10:10:23
 */
@Service("categoryService")
public class CategoryService extends BaseEntityService<Category> implements DataAuthEntityService {

    public static final String REDIS_KEY_CATEGORY = "help:category:all";

    @Autowired
    private CategoryDao dao;
    @Autowired
    private CacheBuilder cacheBuilder;
    @Autowired
    private BasicIntegration basicIntegration;

    @Override
    protected BaseEntityDao<Category> getDao() {
        return dao;
    }

    @Override
    public OperateResultWithData<Category> save(Category entity) {
        // 清除缓存
        cleanCategoryCache();
        return super.save(entity);
    }

    @Override
    public OperateResult delete(String id) {
        Category category = findOne(id);
        if (Objects.nonNull(category)) {
            category.setDeleted(Boolean.TRUE);
            OperateResultWithData<Category> result = save(category);
            if (result.successful()) {
                return OperateResult.operationSuccess();
            } else {
                return OperateResult.operationFailure(result.getMessage());
            }
        }
        return OperateResult.operationFailure("00003");
    }

    /**
     * 清除分类缓存
     */
    protected boolean cleanCategoryCache() {
        cacheBuilder.remove(REDIS_KEY_CATEGORY);
        return true;
    }

    /**
     * 获取分类缓存
     */
    public Map<String, String> getCategoryCacheMap() {
        Map<String, String> map = cacheBuilder.get(REDIS_KEY_CATEGORY);
        if (Objects.isNull(map) || map.isEmpty()) {
            List<Category> categoryList = findAll();
            map = categoryList.stream().collect(Collectors.toMap(Category::getId, Category::getName));

            cacheBuilder.set(REDIS_KEY_CATEGORY, map);
        }
        return map;
    }

    /**
     * 根据id列表获取分类map
     *
     * @param ids id列表
     * @return 分类map
     */
    public Map<String, Category> getCategory(String... ids) {
        Map<String, Category> categoryMap = null;
        if (Objects.nonNull(ids) && ids.length > 0) {
            Set<String> idSet = new HashSet<>();
            for (String id : ids) {
                if (!StringUtils.isEmpty(id)) {
                    idSet.add(id);
                }
            }
            if (!idSet.isEmpty()) {
                List<Category> categoryList = findByFilter(new SearchFilter(Category.ID, idSet, SearchFilter.Operator.IN));
                if (CollectionUtils.isNotEmpty(categoryList)) {
                    categoryMap = categoryList.stream().collect(Collectors.toMap(Category::getId, o -> o));
                }
            }
        }
        if (Objects.isNull(categoryMap)) {
            categoryMap = new HashMap<>();
        }

        return categoryMap;
    }

    /**
     * 从平台基础应用获取一般用户有权限的数据实体Id清单
     * 对于数据权限对象的业务实体，需要override，使用BASIC提供的通用工具来获取
     *
     * @param entityClassName 权限对象实体类型
     * @param featureCode     功能项代码
     * @param userId          用户Id
     * @return 数据实体Id清单
     */
    @Override
    public List<String> getNormalUserAuthorizedEntitiesFromBasic(String entityClassName, String featureCode, String userId) {
        return basicIntegration.getNormalUserAuthorizedEntities(entityClassName, featureCode, userId);
    }
}