package com.power.api;

public interface VipService {

	/**
	 * 填写问卷调查后添加三个月黄金会员
	 * @param userId
	 * @return
	 */
	Boolean AddVip(Long userId );
}
