package com.power.service.impl;



import com.power.api.IVipService;
import com.power.dao.PowerUserVipDao;
import com.power.entity.PowerUserVipEntity;
import org.springframework.stereotype.Service;


@Service("VipServiceImpl")
public class IVipServiceImpl implements IVipService {

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
