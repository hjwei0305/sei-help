package com.changhong.sei.help.controller;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.help.api.ContractInfoApi;
import com.changhong.sei.help.dto.ContractInfoDto;
import com.changhong.sei.help.entity.ContractInfo;
import com.changhong.sei.help.service.ContractInfoService;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;

import java.util.List;

/**
 * 联系方式(ContractInfo)控制类
 *
 * @author sei
 * @since 2020-06-04 10:11:44
 */
@RestController
@Api(value = "ContractInfoApi", tags = "联系方式服务")
@RequestMapping(path = "contractInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ContractInfoController extends BaseEntityController<ContractInfo, ContractInfoDto> implements ContractInfoApi {
    /**
     * 联系方式服务对象
     */
    @Autowired
    private ContractInfoService service;

    @Override
    public BaseEntityService<ContractInfo> getService() {
        return service;
    }


    /**
     * 获取所有业务实体
     *
     * @return 业务实体清单
     */
    @Override
    public ResultData<List<ContractInfoDto>> findAll() {
        List<ContractInfo> list = service.getContractInfoCache();
        return ResultData.success(convertToDtos(list));
    }

    /**
     * 获取所有未冻结的业务实体
     *
     * @return 业务实体清单
     */
    @Override
    public ResultData<List<ContractInfoDto>> findAllUnfrozen() {
        return findAll();
    }

}