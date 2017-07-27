package com.power.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.power.dao.PowerUserVipDao;
import com.power.entity.PowerUserVipEntity;
import com.power.service.PowerUserVipService;



@Service("powerUserVipService")
public class PowerUserVipServiceImpl implements PowerUserVipService {
	@Autowired
	private PowerUserVipDao powerUserVipDao;

	@Override
	public PowerUserVipEntity queryObject(Long id){
		return powerUserVipDao.queryObject(id);
	}

	@Override
	public List<PowerUserVipEntity> queryList(Map<String, Object> map){
		return powerUserVipDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return powerUserVipDao.queryTotal(map);
	}

	@Override
	public void save(PowerUserVipEntity powerUserVip){
		powerUserVipDao.save(powerUserVip);
	}

	@Override
	public void update(PowerUserVipEntity powerUserVip){
		powerUserVipDao.update(powerUserVip);
	}

	@Override
	public void delete(Long id){
		powerUserVipDao.delete(id);
	}

	@Override
	public void deleteBatch(Long[] ids){
		powerUserVipDao.deleteBatch(ids);
	}

}
