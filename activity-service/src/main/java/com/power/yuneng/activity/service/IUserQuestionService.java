/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 15:06:16 $ 
 */



package com.power.yuneng.activity.service;

import com.power.core.service.IBaseService;
import com.power.core.service.IPageService;
import com.power.yuneng.activity.dao.IUserQuestionDAO;
import com.power.yuneng.activity.entity.UserQuestion;

public interface IUserQuestionService extends IBaseService<Integer, IUserQuestionDAO, UserQuestion>,IPageService<IUserQuestionDAO, UserQuestion> {

}
