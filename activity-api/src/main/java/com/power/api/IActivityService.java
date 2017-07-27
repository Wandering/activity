package com.power.api;

import java.util.List;

import com.power.entity.ResUserActivity;
import com.power.entity.UserActivityEntity;

public interface IActivityService {
	
	/**
	 * 根据时间戳推送当前进行中活动
	 * @param nowTime
	 * @return
	 */
	List<ResUserActivity> sendActivity(Long nowTime,Long userId);
}
