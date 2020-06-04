package com.changhong.sei.help.service;

import com.changhong.sei.core.service.bo.ResponseData;
import com.changhong.sei.help.entity.DataDictItem;
import com.changhong.sei.help.dao.DataDictItemDao;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 数据字典子项(DataDictItem)业务逻辑实现类
 *
 * @author sei
 * @since 2020-06-04 10:12:05
 */
@Service("dataDictItemService")
public class DataDictItemService extends BaseEntityService<DataDictItem> {
    @Autowired
    private DataDictItemDao dao;

    @Override
    protected BaseEntityDao<DataDictItem> getDao() {
        return dao;
    }

    /**
     * 根据字段类别代码，获取字典项目
     *
     * @param categoryCode 字典类别代码
     * @param isAll true - 全部 false - 未冻结
     * @return 返回当前类别下的字典项目
     */
    public List<DataDictItem> getDataDictItems(String categoryCode, Boolean isAll) {
        List<DataDictItem> dataDictItems;
        if (isAll == null || isAll) {
            dataDictItems = dao.findByCategoryCodeOrderByRank(categoryCode);
        } else {
            dataDictItems = dao.findByCategoryCodeAndFrozenIsFalseOrderByRank(categoryCode);
        }
        return dataDictItems;
    }

}