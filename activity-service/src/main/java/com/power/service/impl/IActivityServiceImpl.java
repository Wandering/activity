package com.power.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.power.api.IActivityService;
import com.power.dao.UserActivityDao;
import com.power.entity.UserActivityEntity;

public class IActivityServiceImpl implements IActivityService {
     private UserActivityDao userActivityDao;
     
     private List list =new ArrayList<UserActivityEntity>();
     
	@Override
	public List<UserActivityEntity> sendActivity(Long nowTime) {
		  
		return  userActivityDao.queryByTime(nowTime);
		
	}

}
