package com.power.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.power.dao.UserActivityExDao;
import com.power.entity.UserActivityExEntity;
import com.power.service.UserActivityExService;



@Service("userActivityExService")
public class UserActivityExServiceImpl implements UserActivityExService {
	@Autowired
	private UserActivityExDao userActivityExDao;
	
	@Override
	public UserActivityExEntity queryObject(Integer id){
		return userActivityExDao.queryObject(id);
	}
	
	@Override
	public List<UserActivityExEntity> queryList(Map<String, Object> map){
		return userActivityExDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userActivityExDao.queryTotal(map);
	}
	
	@Override
	public void save(UserActivityExEntity userActivityEx){
		userActivityExDao.save(userActivityEx);
	}
	
	@Override
	public void update(UserActivityExEntity userActivityEx){
		userActivityExDao.update(userActivityEx);
	}
	
	@Override
	public void delete(Integer id){
		userActivityExDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		userActivityExDao.deleteBatch(ids);
	}
	
}
