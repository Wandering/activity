package com.power.dao;

import java.util.ArrayList;
import java.util.List;

import com.power.common.dao.BaseDao;
import com.power.entity.UserActivityEntity;
/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-27 17:51:05
 */
public interface UserActivityDao extends BaseDao<UserActivityEntity> {

	List<UserActivityEntity> queryByTime(Long nowTime);
	
}
