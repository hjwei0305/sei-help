package com.changhong.sei.help.service.cust;

import com.changhong.sei.apitemplate.ApiTemplate;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实现功能：
 *
 * @author 马超(Vision.Mac)
 * @version 1.0.00  2020-06-30 08:51
 */
public class BasicIntegrationCustBase implements BasicIntegration {

    @Autowired
    private ApiTemplate apiTemplate;

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
    @Override
    public List<String> getNormalUserAuthorizedEntities(String entityClassName, String featureCode, String userId) {
        String appCode = "sei-basic";
        String path = "user/getNormalUserAuthorizedEntities";
        Map<String, String> params = new HashMap<>();
        params.put("entityClassName", entityClassName);
        params.put("featureCode", featureCode);
        params.put("userId", userId);

        try {
            ResultData<List<String>> resultData = apiTemplate.getByAppModuleCode(appCode, path, new ParameterizedTypeReference<ResultData<List<String>>>() {
            }, params);
            if (resultData.failed()) {
                throw new ServiceException("从平台基础应用获取一般用户有权限的数据实体Id清单失败: " + resultData.getMessage());
            }
            return resultData.getData();
        } catch (Exception e) {
            throw new ServiceException("从平台基础应用获取一般用户有权限的数据实体Id清单异常！", e);
        }
    }
}
