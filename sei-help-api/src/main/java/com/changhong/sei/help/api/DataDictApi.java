package com.changhong.sei.help.api;

import com.changhong.sei.core.api.FindAllApi;
import com.changhong.sei.help.dto.DataDictDto;
import com.changhong.sei.core.api.BaseEntityApi;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 数据字典(DataDict)API
 *
 * @author sei
 * @since 2020-06-04 10:11:55
 */
@Valid
@FeignClient(name = "sei-help", path = "dataDict")
public interface DataDictApi extends BaseEntityApi<DataDictDto>, FindAllApi<DataDictDto> {

}