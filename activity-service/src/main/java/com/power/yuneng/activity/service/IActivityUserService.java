/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 15:06:14 $ 
 */



package com.power.yuneng.activity.service;

import com.power.core.service.IBaseService;
import com.power.core.service.IPageService;
import com.power.yuneng.activity.dao.IActivityUserDAO;
import com.power.yuneng.activity.entity.ActivityUser;

public interface IActivityUserService extends IBaseService<Long, IActivityUserDAO, ActivityUser>,IPageService<IActivityUserDAO, ActivityUser> {

}
