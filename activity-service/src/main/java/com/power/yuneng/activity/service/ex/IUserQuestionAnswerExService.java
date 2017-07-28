/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 15:06:17 $ 
 */



package com.power.yuneng.activity.service.ex;

import com.power.core.service.IBaseService;
import com.power.core.service.IPageService;
import com.power.yuneng.activity.dao.IUserQuestionAnswerDAO;
import com.power.yuneng.activity.entity.UserQuestionAnswer;

import java.util.List;

public interface IUserQuestionAnswerExService{
    boolean saveAnswers(List<UserQuestionAnswer> answers);
}
