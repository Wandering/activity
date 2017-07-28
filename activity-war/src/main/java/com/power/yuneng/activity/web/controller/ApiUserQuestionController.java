/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 15:06:16 $ 
 */
package com.power.yuneng.activity.web.controller;


import com.power.core.domain.BizData4Page;
import com.power.core.domain.SearchField;
import com.power.core.domain.wrapper.BooleanWrapper;
import com.power.core.domain.wrapper.SearchEnum;
import com.power.core.domain.wrapper.StringWrapper;
import com.power.core.exception.BizException;
import com.power.core.utils.ConditionBuilder;
import com.power.core.utils.RtnCodeEnum;
import com.power.yuneng.activity.entity.UserQuestion;
import com.power.yuneng.activity.service.IUserQuestionService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/api")
public class ApiUserQuestionController{
    private static final Logger logger = LoggerFactory.getLogger(ApiUserQuestionController.class);

    @Autowired
    private IUserQuestionService userQuestionService;

   /**
     * 新增 问卷表
     * @param userQuestion
     * @return  处理条数
     */
    @ResponseBody
    @RequestMapping(value = "/userquestion", method = RequestMethod.POST)
    public Object addUserQuestion(@RequestBody UserQuestion userQuestion) {
        try {
            String msg = "";

            if(userQuestion==null) {
                msg = "添加问卷表参数不正确";
            }else if(userQuestion.getName() != null){
                msg = "问卷表问卷名称不能为空";
            }

            if(StringUtils.isNotBlank(msg)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), msg);
            }

            userQuestionService.create(userQuestion);
            return new BooleanWrapper(true);
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "新增问卷表异常");
        }
    }


    /**
     * 删除 问卷表
     *
     * @param {userQuestionId} 问卷表ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userquestion/{userQuestionId}" ,method = RequestMethod.DELETE)
    public Object delUserQuestion(@PathVariable String userQuestionId) {
        try {
            if(StringUtils.isBlank(userQuestionId)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "删除问卷表失败，参数不正确");
            }
            userQuestionService.deleteByCondition(ConditionBuilder.condition("id", SearchEnum.eq, userQuestionId));
            return new BooleanWrapper(true);
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "删除问卷表异常");
        }
    }


    /**
     * 修改问卷表数据
     * @param userQuestion 提交上来的问卷表JSON数据
     * @param userQuestionId  问卷表ID
     * @return  修改条数
     */
    @ResponseBody
    @RequestMapping(value = "/userquestion/{userQuestionId}", method = RequestMethod.PATCH)
    public StringWrapper editUserQuestion(@RequestBody UserQuestion userQuestion, @PathVariable String userQuestionId) {
        try {
            String msg = "";
            if(userQuestion==null) {
                msg = "添加问卷表参数不正确";
            }else if(userQuestion.getName() != null){
                msg = "问卷表问卷名称不能为空";

            }

            if(StringUtils.isNotBlank(msg)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), msg);
            }

            UserQuestion userQuestion_old = (UserQuestion) userQuestionService.view(userQuestion.getId());
            if(userQuestion==null) {
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "修改失败，系统未找到相关数据");
            }

            if(userQuestion.getId() != null){
                userQuestion_old.setId(userQuestion.getId());
            }
            if(userQuestion.getName() != null){
                userQuestion_old.setName(userQuestion.getName());
            }


            int row=userQuestionService.edit(userQuestion_old);
            if(row > 0) {
                return new StringWrapper("修改问卷表成功");
            }else{
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "修改问卷表失败");
            }
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "修改问卷表数据异常");
        }
    }


    /**
     * 获取单问卷表实体
     *
     * @param userQuestionId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userquestion/{userQuestionId}", method = RequestMethod.GET)
    public UserQuestion getUserQuestion(@PathVariable String userQuestionId) {
        try {
            if(StringUtils.isBlank(userQuestionId)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "参数不正确！");
            }
            Map<String,Object> whereParams = new HashMap<String, Object>();
            whereParams.put("id", userQuestionId);
            UserQuestion userQuestion= (UserQuestion) userQuestionService.viewOne(null, whereParams, null);
            if(null == userQuestion){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "系统未找到问卷表相关数据！");
            }
            return userQuestion;
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "获取单问卷表实体异常");
        }
    }


    /**
     * 问卷表数据输出 带分页
     *
     * @param userQuestion 过滤条件
     * @param page      第几页
     * @return 业务数据列表实体，最终转换为json数据输出
     * @throws ServletException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/userquestionlist", method = RequestMethod.POST)
    public BizData4Page userQuestionlist(UserQuestion userQuestion, Integer page) {
        try {
            Map<String, Object> whereParams = new HashMap<String, Object>();
            if(userQuestion.getId() != null){
                whereParams.put("id", new SearchField("id", "=", userQuestion.getId()));
            }
            if(userQuestion.getName() != null){
                whereParams.put("name", new SearchField("name", "=", userQuestion.getName()));
            }


            //other props filter
            whereParams.put("groupOp", "and");

        return userQuestionService.queryPage(null, whereParams, page, 10, 10, null);
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "查询问卷表数据异常");
        }
    }
}
