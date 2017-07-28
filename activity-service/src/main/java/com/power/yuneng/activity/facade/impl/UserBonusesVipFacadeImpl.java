/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 19:50:50 $ 
 */
        package com.power.yuneng.activity.facade.impl;


import com.power.core.service.IBaseService;
import com.power.core.service.impl.AbstractPersistenceProvider;
import com.power.yuneng.activity.facade.IUserBonusesVipFacade;
import com.power.yuneng.activity.service.IUserBonusesVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserBonusesVipFacadeImpl")
public class UserBonusesVipFacadeImpl extends AbstractPersistenceProvider implements IUserBonusesVipFacade {
    @Autowired
    private IUserBonusesVipService userBonusesVipService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        userBonusesVipService.add();
//    }

    @Override
    public IBaseService getMainService() {
        return userBonusesVipService;
    }
}
