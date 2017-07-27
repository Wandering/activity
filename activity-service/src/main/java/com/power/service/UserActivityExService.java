package com.power.service;

import com.power.entity.UserActivityExEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-27 18:58:56
 */
public interface UserActivityExService {
	
	UserActivityExEntity queryObject(Integer id);
	
	List<UserActivityExEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserActivityExEntity userActivityEx);
	
	void update(UserActivityExEntity userActivityEx);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
