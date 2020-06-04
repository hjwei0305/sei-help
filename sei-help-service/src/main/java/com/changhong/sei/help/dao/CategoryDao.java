package com.changhong.sei.help.dao;

import com.changhong.sei.help.entity.Category;
import com.changhong.sei.core.dao.BaseEntityDao;
import org.springframework.stereotype.Repository;

/**
 * 话题分类(Category)数据库访问类
 *
 * @author sei
 * @since 2020-06-04 10:10:23
 */
@Repository
public interface CategoryDao extends BaseEntityDao<Category> {

}