package com.changhong.sei.help.service;

import com.changhong.sei.help.entity.UserAuthority;
import com.changhong.sei.help.dao.UserAuthorityDao;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * (UserAuthority)业务逻辑实现类
 *
 * @author sei
 * @since 2020-06-04 10:12:27
 */
@Service("userAuthorityService")
public class UserAuthorityService extends BaseEntityService<UserAuthority> {
    @Autowired
    private UserAuthorityDao dao;

    @Override
    protected BaseEntityDao<UserAuthority> getDao() {
        return dao;
    }

}