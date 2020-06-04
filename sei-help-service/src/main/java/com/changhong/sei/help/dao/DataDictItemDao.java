package com.changhong.sei.help.dao;

import com.changhong.sei.help.entity.DataDictItem;
import com.changhong.sei.core.dao.BaseEntityDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据字典子项(DataDictItem)数据库访问类
 *
 * @author sei
 * @since 2020-06-04 10:12:05
 */
@Repository
public interface DataDictItemDao extends BaseEntityDao<DataDictItem> {

    /**
     * 根据字段类别代码，获取字典项目
     *
     * @param categoryCode 字典类别代码
     * @return 返回当前类别下的字典项目
     */
    List<DataDictItem> findByCategoryCodeOrderByRank(String categoryCode);


    /**
     * 根据字段类别代码，获取未冻结字典项目
     *
     * @param categoryCode 字典类别代码
     * @return 返回当前类别下的字典项目
     */
    List<DataDictItem> findByCategoryCodeAndFrozenIsFalseOrderByRank(String categoryCode);


    /**
     * 根据字段类别代码，代码获取字典项目
     * @param categoryCode 字段类别代码
     * @param code 代码
     * @return
     */
    DataDictItem findByCategoryCodeAndCode(String categoryCode, String code);

}