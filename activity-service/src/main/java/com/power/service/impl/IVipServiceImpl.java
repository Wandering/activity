package com.power.service.impl;



import com.power.api.IVipService;
import com.power.dao.PowerUserVipDao;
import com.power.dao.UserActivityExDao;
import com.power.entity.PowerUserVipEntity;
import com.power.entity.UserActivityExEntity;

import java.util.Date;

import org.springframework.stereotype.Service;


@Service("VipServiceImpl")
public class IVipServiceImpl implements IVipService {

	private boolean isAdd = true;

	private PowerUserVipDao powerUserVipDao ;
	
	private UserActivityExEntity userActivityExEntity;
	
	private UserActivityExDao userActivityExDao;


	@SuppressWarnings("null")
	@Override
	public Boolean AddVip(int userId,int activityId) {

		PowerUserVipEntity user = powerUserVipDao.queryObject(userId);
	    if(user==null){
	    	user.setId(Long.valueOf(userId));
	    	user.setType(3);
	    	user.setStart(1504195200L);
	    	user.setEnd(1512144000L);
	    	userActivityExEntity = new UserActivityExEntity();
	    	userActivityExEntity.setUserId(userId);
	    	userActivityExEntity.setActivityId(activityId);
	    	userActivityExEntity.setCreateDt(new Date().getTime()/1000);
	    	userActivityExDao.save(userActivityExEntity);
	    }
	    	
	    	

		return isAdd;
	}

}
