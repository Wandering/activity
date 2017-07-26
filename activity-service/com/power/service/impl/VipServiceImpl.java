package com.power.service.impl;


import org.springframework.stereotype.Service;

import com.power.api.VipService;
import com.power.dao.PowerUserVipDao;
import com.power.entity.PowerUserVipEntity;


@Service("VipServiceImpl")
public class VipServiceImpl implements VipService{

	private boolean isAdd = true;
	
	private PowerUserVipDao powerUserVipDao ;
	
	
	@SuppressWarnings("null")
	@Override
	public Boolean AddVip(Long userId) {
		
		PowerUserVipEntity user = powerUserVipDao.queryObject(userId);
	    if(user==null){
	    	user.setId(userId);
	    	user.setType(3);
	    	user.setStart(1504195200L);
	    	user.setEnd(1512144000L);
	    }
		
		return isAdd;
	}

}
