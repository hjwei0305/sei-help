package com.changhong.sei.help.service.client;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.edm.sdk.DocumentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.Collection;

/**
 * @author <a href="mailto:xiaogang.su@changhong.com">粟小刚</a>
 * @description 实现功能:EDM文档服务
 * @date 2020/06/05 16:00
 */
@Component
public class EDMDocumentManager {

    @Autowired
    private DocumentManager documentManager;

    /**
     * 提交业务实体的文档信息
     *
     * @param entityId 绑定业务实体文档信息请求
     * @param documentIds   绑定业务实体文档信息请求
     */
    public ResultData<String> bindBusinessDocuments(String entityId, Collection<String> documentIds){
        return documentManager.bindBusinessDocuments(entityId, documentIds);
    }

    /**
     * 删除业务实体的文档信息
     *
     * @param entityId 业务实体Id
     */
    public ResultData<String> deleteBusinessInfos(@NotBlank String entityId) {
        return documentManager.deleteBusinessInfos(entityId);
    }

}
