/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 15:06:17 $ 
 */




package com.power.yuneng.activity.service.ex.impl;


import com.power.yuneng.activity.dao.IUserQuestionAnswerDAO;
import com.power.yuneng.activity.dao.ex.IUserQuestionAnswerExDAO;
import com.power.yuneng.activity.entity.UserQuestionAnswer;
import com.power.yuneng.activity.service.ex.IUserQuestionAnswerExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserQuestionAnswerExServiceImpl")
public class UserQuestionAnswerServiceExImpl implements IUserQuestionAnswerExService {
    @Autowired
    private IUserQuestionAnswerDAO userQuestionAnswerDAO;
    @Autowired
    private IUserQuestionAnswerExDAO userQuestionAnswerExDAO;


    @Override
    public boolean saveAnswers(List<UserQuestionAnswer> answers) {
        return userQuestionAnswerExDAO.saveAnswers(answers);
    }
}
