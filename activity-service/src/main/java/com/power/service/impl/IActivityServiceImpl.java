package com.power.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.power.api.IActivityService;
import com.power.dao.UserActivityDao;
import com.power.dao.UserActivityExDao;
import com.power.entity.ResUserActivity;
import com.power.entity.UserActivityEntity;

public class IActivityServiceImpl implements IActivityService {
     private UserActivityDao userActivityDao;
     
     private UserActivityExDao userActivityExDao;
     
     private Boolean isUse ;
     
    
     
	@Override
	public List<ResUserActivity> sendActivity(Long nowTime,Long userrId) {
		  
	List<UserActivityEntity> listTemp = userActivityDao.queryByTime(nowTime);
	List<ResUserActivity> list = new <ResUserActivity> ArrayList();
		for(UserActivityEntity u:listTemp){
			int i = userActivityExDao.queryIsUse(userrId,u.getActivityId());
			if(i>0){
				isUse =true;
			}else{
				isUse= false;
			}
			ResUserActivity resUserActivity = new ResUserActivity();
			resUserActivity.setUse(isUse);
			resUserActivity.setUserActivityEntity(u);
			list.add(resUserActivity);
		}
		return list;
		
	}

}
