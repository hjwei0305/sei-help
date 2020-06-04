package com.changhong.sei.help.service;

import com.changhong.sei.help.entity.Collect;
import com.changhong.sei.help.dao.CollectDao;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 话题收藏(Collect)业务逻辑实现类
 *
 * @author sei
 * @since 2020-06-04 10:10:36
 */
@Service("collectService")
public class CollectService extends BaseEntityService<Collect> {
    @Autowired
    private CollectDao dao;

    @Override
    protected BaseEntityDao<Collect> getDao() {
        return dao;
    }

}