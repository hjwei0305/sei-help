package com.changhong.sei.help.api;

import com.changhong.sei.core.api.FindAllApi;
import com.changhong.sei.help.dto.ContractInfoDto;
import com.changhong.sei.core.api.BaseEntityApi;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 * 联系方式(ContractInfo)API
 *
 * @author sei
 * @since 2020-06-04 10:11:44
 */
@Valid
@FeignClient(name = "sei-help", path = "contractInfo")
public interface ContractInfoApi extends BaseEntityApi<ContractInfoDto>, FindAllApi<ContractInfoDto> {

}