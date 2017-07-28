/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 15:06:12 $ 
 */
        package com.power.yuneng.activity.facade.impl;


import com.power.core.service.IBaseService;
import com.power.core.service.impl.AbstractPersistenceProvider;
import com.power.yuneng.activity.facade.IActivityFacade;
import com.power.yuneng.activity.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ActivityFacadeImpl")
public class ActivityFacadeImpl extends AbstractPersistenceProvider implements IActivityFacade {
    @Autowired
    private IActivityService activityService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        activityService.add();
//    }

    @Override
    public IBaseService getMainService() {
        return activityService;
    }
}
