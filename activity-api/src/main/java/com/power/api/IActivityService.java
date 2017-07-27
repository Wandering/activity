package com.power.api;

import java.util.List;

import com.power.entity.UserActivityEntity;

public interface IActivityService {
	
	/**
	 * 根据时间戳推送当前进行中活动
	 * @param nowTime
	 * @return
	 */
	List<UserActivityEntity> sendActivity(Long nowTime);
}
