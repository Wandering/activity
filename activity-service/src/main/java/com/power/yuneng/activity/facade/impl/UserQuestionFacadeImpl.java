/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 15:06:16 $ 
 */
        package com.power.yuneng.activity.facade.impl;


import com.power.core.service.IBaseService;
import com.power.core.service.impl.AbstractPersistenceProvider;
import com.power.yuneng.activity.facade.IUserQuestionFacade;
import com.power.yuneng.activity.service.IUserQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserQuestionFacadeImpl")
public class UserQuestionFacadeImpl extends AbstractPersistenceProvider implements IUserQuestionFacade {
    @Autowired
    private IUserQuestionService userQuestionService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        userQuestionService.add();
//    }

    @Override
    public IBaseService getMainService() {
        return userQuestionService;
    }
}
