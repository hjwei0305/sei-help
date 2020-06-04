package com.changhong.sei.help.controller;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.context.SessionUser;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.dto.serach.SearchOrder;
import com.changhong.sei.help.api.NotificationApi;
import com.changhong.sei.help.dto.NotificationDto;
import com.changhong.sei.help.entity.Notification;
import com.changhong.sei.help.service.NotificationService;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;

import java.util.List;
import java.util.Objects;

/**
 * 通知(Notification)控制类
 *
 * @author sei
 * @since 2020-06-04 10:12:17
 */
@RestController
@Api(value = "NotificationApi", tags = "通知服务")
@RequestMapping(path = "notification", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class NotificationController extends BaseEntityController<Notification, NotificationDto> implements NotificationApi {
    /**
     * 通知服务对象
     */
    @Autowired
    private NotificationService service;

    @Override
    public BaseEntityService<Notification> getService() {
        return service;
    }

    /**
     * 分页查询业务实体
     *
     * @param search 查询参数
     * @return 分页查询结果
     */
    @Override
    public ResultData<PageResult<NotificationDto>> findByPage(Search search) {
        SessionUser user = ContextUtil.getSessionUser();
        String userId = user.getUserId();
        search.addSortOrder(new SearchOrder("read", SearchOrder.Direction.ASC));
        search.addSortOrder(new SearchOrder("inTime", SearchOrder.Direction.DESC));
        search.addFilter(new SearchFilter("targetUserId",userId));
        PageResult<Notification> data =  service.findByPage(search);
        return convertToDtoPageResult(data);
    }

    /**
     * 获取未读数量
     * @return 未读数量
     */
    @Override
    public ResultData<Long> notRead() {
        SessionUser user = ContextUtil.getSessionUser();
        String userId = user.getUserId();
        long count = service.countNotRead(userId);
        return ResultData.success(count);
    }

    @Override
    public NotificationDto convertToDto(Notification entity) {
        if (Objects.isNull(entity)){
            return null;
        }
        ModelMapper custMapper = new ModelMapper();
        // 创建自定义映射规则
        PropertyMap<Notification, NotificationDto> propertyMap = new PropertyMap<Notification, NotificationDto>() {
            @Override
            protected void configure() {
                // 使用自定义转换规则确定FeatureId
                map().setTopicId(source.getTopic().getId());
                map().setTitle(source.getTopic().getTitle());
                map().setUrl(source.getTopic().getUrl());
                map().setUserInfo(source.getTopic().getUserInfo());
            }
        };
        // 添加映射器
        custMapper.addMappings(propertyMap);
        // 转换
        return custMapper.map(entity, NotificationDto.class);
    }
}