package com.changhong.sei.help.service;

import com.changhong.sei.help.entity.Collect;
import com.changhong.sei.help.dao.CollectDao;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 话题收藏(Collect)业务逻辑实现类
 *
 * @author sei
 * @since 2020-06-04 10:10:36
 */
@Service("collectService")
public class CollectService extends BaseEntityService<Collect> {
    @Autowired
    private CollectDao dao;

    @Override
    protected BaseEntityDao<Collect> getDao() {
        return dao;
    }

    /**
     * 根据用户获取分页收藏
     * @param userId 用户id
     * @param pageNo 页码
     * @param pageSize 页数
     * @return 分页收藏
     */
    public Page<Collect> findByUserId(String userId, Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, "inTime");
        Collect collect = new Collect();
        collect.setUserId(userId);
        return dao.findAll(Example.of(collect), pageable);
    }

    /**
     * 根据用户id和话题id查询
     * @param userId 用户id
     * @param topicId 话题id
     * @return 收藏
     */
    public Collect findByUserIdAndTopicId(String userId, String topicId) {
        return dao.findByUserIdAndTopicId(userId,topicId);
    }

    /**
     * 根据收藏id删除
     * @param id 收藏id
     */
    public void deleteById(String id) {
        dao.deleteById(id);
    }

    /**
     * 根据话题id删除全部收藏
     * @param topicId 话题id
     */
    public void deleteByTopicId(String topicId) {
        dao.deleteByTopicId(topicId);
    }

}