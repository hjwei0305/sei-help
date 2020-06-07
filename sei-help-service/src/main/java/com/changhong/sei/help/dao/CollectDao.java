package com.changhong.sei.help.dao;

import com.changhong.sei.help.entity.Collect;
import com.changhong.sei.core.dao.BaseEntityDao;
import org.springframework.stereotype.Repository;

/**
 * 话题收藏(Collect)数据库访问类
 *
 * @author sei
 * @since 2020-06-04 10:10:36
 */
@Repository
public interface CollectDao extends BaseEntityDao<Collect> {

    /**
     * 根据用户id和话题id查询
     * @param userId 用户id
     * @param topicId 话题id
     * @return 收藏
     */
    Collect findByUserIdAndTopicId(String userId, String topicId);

}