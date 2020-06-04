package com.changhong.sei.help.controller;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.service.bo.ResponseData;
import com.changhong.sei.help.api.DataDictItemApi;
import com.changhong.sei.help.dto.DataDictItemDto;
import com.changhong.sei.help.entity.DataDictItem;
import com.changhong.sei.help.service.DataDictItemService;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;

import java.util.List;

/**
 * 数据字典子项(DataDictItem)控制类
 *
 * @author sei
 * @since 2020-06-04 10:12:05
 */
@RestController
@Api(value = "DataDictItemApi", tags = "数据字典子项服务")
@RequestMapping(path = "dataDictItem", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DataDictItemController extends BaseEntityController<DataDictItem, DataDictItemDto> implements DataDictItemApi {
    /**
     * 数据字典子项服务对象
     */
    @Autowired
    private DataDictItemService service;

    @Override
    public BaseEntityService<DataDictItem> getService() {
        return service;
    }

    /**
     * 根据字段类别代码，获取所有字典项目
     *
     * @param categoryCode 字典类别代码
     * @return 返回当前类别下的字典项目
     */
    @Override
    public ResultData<List<DataDictItemDto>> getDataDictItems(@RequestParam("categoryCode") String categoryCode, @RequestParam(value = "isAll", defaultValue = "true") Boolean isAll) {
        List<DataDictItem> result = service.getDataDictItems(categoryCode, isAll);
        return ResultData.success(convertToDtos(result));
    }

    /**
     * 根据字段类别代码，获取字典项目
     *
     * @param categoryCode 字典类别代码
     * @return 返回当前类别下的字典项目
     */
    @Override
    public ResultData<List<DataDictItemDto>> getDataDictItemsUnFrozen(@RequestParam("categoryCode") String categoryCode) {
        List<DataDictItem> result = service.getDataDictItems(categoryCode, false);
        return ResultData.success(convertToDtos(result));
    }

}