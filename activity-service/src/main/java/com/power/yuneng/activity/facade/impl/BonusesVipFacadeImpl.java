/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 15:06:15 $ 
 */
        package com.power.yuneng.activity.facade.impl;



import com.power.core.service.IBaseService;
import com.power.core.service.impl.AbstractPersistenceProvider;
import com.power.yuneng.activity.facade.IBonusesVipFacade;
import com.power.yuneng.activity.service.IBonusesVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("BonusesVipFacadeImpl")
public class BonusesVipFacadeImpl extends AbstractPersistenceProvider implements IBonusesVipFacade {
    @Autowired
    private IBonusesVipService bonusesVipService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        bonusesVipService.add();
//    }

    @Override
    public IBaseService getMainService() {
        return bonusesVipService;
    }
}
