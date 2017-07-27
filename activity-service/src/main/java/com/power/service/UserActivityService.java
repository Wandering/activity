package com.power.service;

import com.power.entity.UserActivityEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-27 17:51:05
 */
public interface UserActivityService {
	
	UserActivityEntity queryObject(Integer id);
	
	List<UserActivityEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserActivityEntity userActivity);
	
	void update(UserActivityEntity userActivity);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
