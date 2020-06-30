package com.changhong.sei.help.service.cust;

import java.util.List;

/**
 * 实现功能：sei基础应用(basic)集成
 *
 * @author 马超(Vision.Mac)
 * @version 1.0.00  2020-06-30 08:51
 */
public interface BasicIntegration {

    /**
     * 提供基础应用的用户权限管理API调用
     * 从平台基础应用获取一般用户有权限的数据实体Id清单
     * 对于数据权限对象的业务实体，需要override，使用BASIC提供的通用工具来获取
     *
     * @param entityClassName 权限对象实体类型
     * @param featureCode     功能项代码
     * @param userId          用户Id
     * @return 数据实体Id清单
     */
    List<String> getNormalUserAuthorizedEntities(String entityClassName, String featureCode, String userId);
}
