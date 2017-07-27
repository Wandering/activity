package com.power.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.power.dao.UserActivityDao;
import com.power.entity.UserActivityEntity;
import com.power.service.UserActivityService;



@Service("userActivityService")
public class UserActivityServiceImpl implements UserActivityService {
	@Autowired
	private UserActivityDao userActivityDao;
	
	@Override
	public UserActivityEntity queryObject(Integer id){
		return userActivityDao.queryObject(id);
	}
	
	@Override
	public List<UserActivityEntity> queryList(Map<String, Object> map){
		return userActivityDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userActivityDao.queryTotal(map);
	}
	
	@Override
	public void save(UserActivityEntity userActivity){
		userActivityDao.save(userActivity);
	}
	
	@Override
	public void update(UserActivityEntity userActivity){
		userActivityDao.update(userActivity);
	}
	
	@Override
	public void delete(Integer id){
		userActivityDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		userActivityDao.deleteBatch(ids);
	}
	
}
