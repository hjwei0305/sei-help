package com.changhong.sei.help.service;

import com.changhong.sei.core.service.bo.OperateResult;
import com.changhong.sei.core.service.bo.OperateResultWithData;
import com.changhong.sei.help.entity.ContractInfo;
import com.changhong.sei.help.dao.ContractInfoDao;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


/**
 * 联系方式(ContractInfo)业务逻辑实现类
 *
 * @author sei
 * @since 2020-06-04 10:11:44
 */
@Service("contractInfoService")
public class ContractInfoService extends BaseEntityService<ContractInfo> {

    public static final String REDIS_KEY_CONTRACT_INFO = "help:contractInfo:all";

    @Autowired
    private ContractInfoDao dao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected BaseEntityDao<ContractInfo> getDao() {
        return dao;
    }

    @Override
    public OperateResultWithData<ContractInfo> save(ContractInfo entity) {
        // 清除缓存
        cleanContractInfoCache();
        return super.save(entity);
    }

    @Override
    public OperateResult delete(String id) {
        // 清除缓存
        cleanContractInfoCache();
        super.delete(id);
        return OperateResult.operationSuccess();
    }

    /**
     * 清除联系方式缓存
     */
    public boolean cleanContractInfoCache() {
        return redisTemplate.delete(REDIS_KEY_CONTRACT_INFO);
    }

    /**
     * 获取联系方式缓存
     */
    public List<ContractInfo> getContractInfoCache() {
        BoundValueOperations operations = redisTemplate.boundValueOps(REDIS_KEY_CONTRACT_INFO);
        List<ContractInfo> list = (List<ContractInfo>) operations.get();
        if (Objects.isNull(list) || list.isEmpty()) {
            list = findAll();
            operations.set(list);
        }
        return list;
    }

}