/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 19:50:50 $ 
 */



package com.power.yuneng.activity.service;

import com.power.core.service.IBaseService;
import com.power.core.service.IPageService;
import com.power.yuneng.activity.dao.IUserBonusesVipDAO;
import com.power.yuneng.activity.entity.UserBonusesVip;

public interface IUserBonusesVipService extends IBaseService<Long, IUserBonusesVipDAO, UserBonusesVip>,IPageService<IUserBonusesVipDAO, UserBonusesVip> {

}
