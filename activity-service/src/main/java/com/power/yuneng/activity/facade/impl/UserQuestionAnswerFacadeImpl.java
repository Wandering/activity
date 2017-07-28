/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 15:06:17 $ 
 */
        package com.power.yuneng.activity.facade.impl;



import com.power.core.service.IBaseService;
import com.power.core.service.impl.AbstractPersistenceProvider;
import com.power.yuneng.activity.facade.IUserQuestionAnswerFacade;
import com.power.yuneng.activity.service.IUserQuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserQuestionAnswerFacadeImpl")
public class UserQuestionAnswerFacadeImpl extends AbstractPersistenceProvider implements IUserQuestionAnswerFacade {
    @Autowired
    private IUserQuestionAnswerService userQuestionAnswerService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        userQuestionAnswerService.add();
//    }

    @Override
    public IBaseService getMainService() {
        return userQuestionAnswerService;
    }
}
