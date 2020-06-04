package com.changhong.sei.help.service;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.service.bo.OperateResult;
import com.changhong.sei.core.service.bo.OperateResultWithData;
import com.changhong.sei.help.entity.Category;
import com.changhong.sei.help.dao.CategoryDao;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * 话题分类(Category)业务逻辑实现类
 *
 * @author sei
 * @since 2020-06-04 10:10:23
 */
@Service("categoryService")
public class CategoryService extends BaseEntityService<Category> {

    public static final String REDIS_KEY_CATEGORY = "help:category:all";

    @Autowired
    private CategoryDao dao;
    @Autowired
    private RedisTemplate redisTemplate;

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
            if (result.successful()){
                return OperateResult.operationSuccess();
            }else {
                return OperateResult.operationFailure(result.getMessage());
            }
        }
        return OperateResult.operationFailure("00003");
    }

    /**
     * 清除分类缓存
     */
    protected boolean cleanCategoryCache() {
        return redisTemplate.delete(REDIS_KEY_CATEGORY);
    }

    /**
     * 获取分类缓存
     */
    protected Map<String, String> getCategoryCacheMap() {
        BoundValueOperations operations = redisTemplate.boundValueOps(REDIS_KEY_CATEGORY);
        Map<String, String> map = (Map<String, String>) operations.get();
        if (Objects.isNull(map) || map.isEmpty()) {
            List<Category> categoryList = findAll();
            map = categoryList.stream().collect(Collectors.toMap(Category::getId, Category::getName));

            operations.set(map);
        }
        return map;
    }

}