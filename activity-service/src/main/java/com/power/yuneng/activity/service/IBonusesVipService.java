/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 15:06:15 $ 
 */



package com.power.yuneng.activity.service;

import com.power.core.service.IBaseService;
import com.power.core.service.IPageService;
import com.power.yuneng.activity.dao.IBonusesVipDAO;
import com.power.yuneng.activity.entity.BonusesVip;

public interface IBonusesVipService extends IBaseService<Integer, IBonusesVipDAO, BonusesVip>,IPageService<IBonusesVipDAO, BonusesVip> {

}
