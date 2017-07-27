package com.power.dao;

import com.power.common.dao.BaseDao;
import com.power.entity.UserActivityExEntity;
/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-27 18:58:56
 */
public interface UserActivityExDao extends BaseDao<UserActivityExEntity> {

	int queryIsUse(Long userrId, Integer activityId);
	
}
