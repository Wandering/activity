package com.power.service;

import com.power.entity.PowerUserVipEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-26 15:55:57
 */
public interface PowerUserVipService {
	
	PowerUserVipEntity queryObject(Long id);
	
	List<PowerUserVipEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PowerUserVipEntity powerUserVip);
	
	void update(PowerUserVipEntity powerUserVip);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
