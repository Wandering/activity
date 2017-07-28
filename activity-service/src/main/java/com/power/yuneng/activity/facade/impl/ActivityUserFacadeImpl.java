/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 15:06:14 $ 
 */
        package com.power.yuneng.activity.facade.impl;


import com.power.core.service.IBaseService;
import com.power.core.service.impl.AbstractPersistenceProvider;
import com.power.yuneng.activity.facade.IActivityUserFacade;
import com.power.yuneng.activity.service.IActivityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ActivityUserFacadeImpl")
public class ActivityUserFacadeImpl extends AbstractPersistenceProvider implements IActivityUserFacade {
    @Autowired
    private IActivityUserService activityUserService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        activityUserService.add();
//    }

    @Override
    public IBaseService getMainService() {
        return activityUserService;
    }
}
