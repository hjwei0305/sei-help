package com.changhong.sei.help.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.help.dto.DataDictItemDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * 数据字典子项(DataDictItem)API
 *
 * @author sei
 * @since 2020-06-04 10:12:05
 */
@Valid
@FeignClient(name = "sei-help", path = "dataDictItem")
public interface DataDictItemApi extends BaseEntityApi<DataDictItemDto> {

    /**
     * 根据字段类别代码，获取所有字典项目
     *
     * @param categoryCode 字典类别代码
     * @return 返回当前类别下的字典项目
     */
    @GetMapping("/getDataDictItems")
    @ApiOperation(value = "根据字段类别代码，获取字典项目", notes = "根据字段类别代码，获取字典项目")
    ResultData<List<DataDictItemDto>> getDataDictItems(@RequestParam("categoryCode") String categoryCode,
                                                       @RequestParam(value = "isAll", defaultValue = "true") Boolean isAll);

    /**
     * 根据字段类别代码，获取字典项目
     *
     * @param categoryCode 字典类别代码
     * @return 返回当前类别下的字典项目
     */
    @GetMapping("/getDataDictItemsUnFrozen")
    @ApiOperation(value = "根据字段类别代码，获取字典项目", notes = "根据字段类别代码，获取字典项目")
    ResultData<List<DataDictItemDto>> getDataDictItemsUnFrozen(@RequestParam("categoryCode") String categoryCode);

}