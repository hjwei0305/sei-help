package com.changhong.sei.help.service;

import com.changhong.sei.core.service.bo.OperateResult;
import com.changhong.sei.help.dao.DataDictItemDao;
import com.changhong.sei.help.entity.DataDict;
import com.changhong.sei.help.dao.DataDictDao;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.help.entity.DataDictItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


/**
 * 数据字典(DataDict)业务逻辑实现类
 *
 * @author sei
 * @since 2020-06-04 10:11:55
 */
@Service("dataDictService")
public class DataDictService extends BaseEntityService<DataDict> {
    @Autowired
    private DataDictDao dao;
    @Autowired
    private DataDictItemDao dictItemDao;

    @Override
    protected BaseEntityDao<DataDict> getDao() {
        return dao;
    }

    @Override
    protected OperateResult preDelete(String id) {
        DataDict entity = findOne(id);
        if (entity != null) {
            List<DataDictItem> dictItems = dictItemDao.findByCategoryCodeOrderByRank(entity.getCode());
            if (dictItems != null && dictItems.size() > 0) {
                //当前字典类别【{0}】存在字典项目不允许删除！
                return OperateResult.operationSuccess("00002", entity.getCode());
            }
            return  super.preDelete(id);
        } else {
            return OperateResult.operationFailure("00003");
        }

    }
}