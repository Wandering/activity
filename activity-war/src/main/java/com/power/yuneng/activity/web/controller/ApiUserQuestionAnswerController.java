/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 15:06:17 $ 
 */
package com.power.yuneng.activity.web.controller;


import com.alibaba.fastjson.JSON;
import com.power.core.domain.BizData4Page;
import com.power.core.domain.SearchField;
import com.power.core.domain.wrapper.BooleanWrapper;
import com.power.core.domain.wrapper.SearchEnum;
import com.power.core.domain.wrapper.StringWrapper;
import com.power.core.exception.BizException;
import com.power.core.utils.ConditionBuilder;
import com.power.core.utils.RtnCodeEnum;
import com.power.yuneng.activity.entity.Activity;
import com.power.yuneng.activity.entity.ActivityUser;
import com.power.yuneng.activity.entity.UserQuestion;
import com.power.yuneng.activity.entity.UserQuestionAnswer;
import com.power.yuneng.activity.entity.dto.UserAnswerDTO;
import com.power.yuneng.activity.entity.enums.QusetionProgressEnum;
import com.power.yuneng.activity.service.IActivityService;
import com.power.yuneng.activity.service.IActivityUserService;
import com.power.yuneng.activity.service.IUserQuestionAnswerService;
import com.power.yuneng.activity.service.IUserQuestionService;
import com.power.yuneng.activity.service.ex.IUserQuestionAnswerExService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/api")
public class ApiUserQuestionAnswerController{
    private static final Logger logger = LoggerFactory.getLogger(ApiUserQuestionAnswerController.class);

    @Autowired
    private IUserQuestionAnswerService userQuestionAnswerService;
    @Autowired
    private IUserQuestionAnswerExService userQuestionAnswerExService;
    @Autowired
    private IActivityUserService activityUserService;
    @Autowired
    private IActivityService activityService;
    @Autowired
    private IUserQuestionService userQuestionService;

    /**
     * 新增 批量添加问卷答案表
     * @param question
     * @return  处理条数
     */
    @ResponseBody
    @RequestMapping(value = "/question/create", method = RequestMethod.POST)
    public Object questionStart(String question) {
        try {
            if (org.apache.commons.lang.StringUtils.isEmpty(question)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "参数为空");
            }
            UserAnswerDTO userAnswerDTO = JSON.parseObject(question,UserAnswerDTO.class);
            if (userAnswerDTO==null){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "参数为空");
            }
            if (userAnswerDTO.getAccountId()==null){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "用户ID为空");
            }
            if (userAnswerDTO.getQuestionnaireId()==null){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "问卷编号为空");
            }
            UserQuestion userQuestion = userQuestionService.view(userAnswerDTO.getQuestionnaireId());
            if (userQuestion == null){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "问卷不存在");
            }
            if (userAnswerDTO.getActivityId()==null){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "活动编码为空");
            }
            Activity activity = activityService.view(userAnswerDTO.getActivityId());
            if (activity==null){
                throw new BizException(RtnCodeEnum.ACTIVITY_NOT_EXIST.getValue(), RtnCodeEnum.ACTIVITY_NOT_EXIST.getDesc());
            }
            if (activity.getStartTime()>System.currentTimeMillis()/1000){
                throw new BizException(RtnCodeEnum.ACTIVITY_NOT_START.getValue(), RtnCodeEnum.ACTIVITY_NOT_START.getDesc());
            }
            if (activity.getEndTime()<System.currentTimeMillis()/1000){
                throw new BizException(RtnCodeEnum.ACTIVITY_END.getValue(), RtnCodeEnum.ACTIVITY_END.getDesc());
            }

            Map<String,Object> map = new HashMap<>();
            map .put("accountId",userAnswerDTO.getAccountId());
            map .put("activityId",userAnswerDTO.getActivityId());
            ActivityUser activityUser = activityUserService.viewOne(map);
            if (activityUser == null){
                activityUser = new ActivityUser();
                activityUser.setProgress(QusetionProgressEnum.START.getValue());
                activityUser.setAccountId(userAnswerDTO.getAccountId());
                activityUser.setActivityId(userAnswerDTO.getActivityId());
                activityUser.setCreateTime(System.currentTimeMillis()/1000);
                activityUser.setUpdateTime(System.currentTimeMillis()/1000);
                activityUserService.create(activityUser);
            }
            return new BooleanWrapper(true);
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "用户问卷添加失败");
        }
    }

    /**
     * 新增 批量添加问卷答案表
     * @param answers
     * @return  处理条数
     */
    @ResponseBody
    @RequestMapping(value = "/userquestionanswer/saveAnswers", method = RequestMethod.POST)
    public Object saveAnswers(String answers) {
        try {
            if (org.apache.commons.lang.StringUtils.isEmpty(answers)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "问卷结果为空");
            }
            UserAnswerDTO userAnswerDTO = JSON.parseObject(answers,UserAnswerDTO.class);
            if (userAnswerDTO==null){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "问卷结果为空");
            }
            if (userAnswerDTO.getAccountId()==null){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "用户ID为空");
            }
            if (userAnswerDTO.getQuestionnaireId()==null){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "问卷编号为空");
            }
            UserQuestion userQuestion = userQuestionService.view(userAnswerDTO.getQuestionnaireId());
            if (userQuestion == null){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "问卷不存在");
            }
            if (userAnswerDTO.getActivityId()==null){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "活动编码为空");
            }
            if (userAnswerDTO.getAnswers()==null){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "问卷结果为空");
            }
            if (userAnswerDTO.getAnswers().size()==0){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "问卷结果为空");
            }
            Activity activity = activityService.view(userAnswerDTO.getActivityId());
            if (activity==null){
                throw new BizException(RtnCodeEnum.ACTIVITY_NOT_EXIST.getValue(), RtnCodeEnum.ACTIVITY_NOT_EXIST.getDesc());
            }
            if (activity.getStartTime()>System.currentTimeMillis()/1000){
                throw new BizException(RtnCodeEnum.ACTIVITY_NOT_START.getValue(), RtnCodeEnum.ACTIVITY_NOT_START.getDesc());
            }
            if (activity.getEndTime()<System.currentTimeMillis()/1000){
                throw new BizException(RtnCodeEnum.ACTIVITY_END.getValue(), RtnCodeEnum.ACTIVITY_END.getDesc());
            }
            Map<String,Object> map = new HashMap<>();
            map .put("accountId",userAnswerDTO.getAccountId());
            map .put("activityId",userAnswerDTO.getActivityId());
            ActivityUser activityUser = activityUserService.viewOne(map);
            if (activityUser == null){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(),"非法提交");
            }
            if (activityUser.getProgress()>QusetionProgressEnum.START.getValue()){
                throw new BizException(RtnCodeEnum.ANSWER_EXIST.getValue(), RtnCodeEnum.ANSWER_EXIST.getDesc());
            }
            List<UserQuestionAnswer> userQuestionAnswers = new ArrayList<>();
            for (UserAnswerDTO.Answer answer : userAnswerDTO.getAnswers()){
                UserQuestionAnswer userQuestionAnswer = new UserQuestionAnswer();
                userQuestionAnswer.setAccountId(userAnswerDTO.getAccountId());
                userQuestionAnswer.setQuestionnaireId(userAnswerDTO.getQuestionnaireId());
                userQuestionAnswer.setContent(answer.getContent());
                userQuestionAnswer.setQuestionNo(answer.getQuestionNo());
                userQuestionAnswers.add(userQuestionAnswer);
            }
            boolean flag = userQuestionAnswerExService.saveAnswers(userQuestionAnswers);

            if (activityUser.getProgress()== QusetionProgressEnum.START.getValue()){
                logger.info("用户进度+1");
                activityUser.setProgress(QusetionProgressEnum.START.getValue()+1);
                activityUser.setUpdateTime(System.currentTimeMillis()/1000);
                activityUserService.edit(activityUser);
            }
            return new BooleanWrapper(flag);
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "用户问卷添加失败");
        }
    }
   /**
     * 新增 问卷答案表
     * @param userQuestionAnswer
     * @return  处理条数
     */
    @ResponseBody
    @RequestMapping(value = "/userquestionanswer", method = RequestMethod.POST)
    public Object addUserQuestionAnswer(@RequestBody UserQuestionAnswer userQuestionAnswer) {
        try {
            String msg = "";

            if(userQuestionAnswer==null) {
                msg = "添加问卷答案表参数不正确";
            }else if(userQuestionAnswer.getQuestionnaireId() != null){
                msg = "问卷答案表问卷ID不能为空";
            }else if(userQuestionAnswer.getQuestionNo() != null){
                msg = "问卷答案表题号不能为空";
            }else if(userQuestionAnswer.getAccountId() != null){
                msg = "问卷答案表用户id不能为空";
            }else if(StringUtils.isBlank(userQuestionAnswer.getContent())){
                msg = "问卷答案表内容不能为空";
            }else if(userQuestionAnswer.getContent().length() > 255){
                msg = "问卷答案表内容长度不可超过255";
            }

            if(StringUtils.isNotBlank(msg)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), msg);
            }

            userQuestionAnswerService.create(userQuestionAnswer);
            return new BooleanWrapper(true);
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "新增问卷答案表异常");
        }
    }


    /**
     * 删除 问卷答案表
     *
     * @param {userQuestionAnswerId} 问卷答案表ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userquestionanswer/{userQuestionAnswerId}" ,method = RequestMethod.DELETE)
    public Object delUserQuestionAnswer(@PathVariable String userQuestionAnswerId) {
        try {
            if(StringUtils.isBlank(userQuestionAnswerId)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "删除问卷答案表失败，参数不正确");
            }
            userQuestionAnswerService.deleteByCondition(ConditionBuilder.condition("id", SearchEnum.eq, userQuestionAnswerId));
            return new BooleanWrapper(true);
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "删除问卷答案表异常");
        }
    }


    /**
     * 修改问卷答案表数据
     * @param userQuestionAnswer 提交上来的问卷答案表JSON数据
     * @param userQuestionAnswerId  问卷答案表ID
     * @return  修改条数
     */
    @ResponseBody
    @RequestMapping(value = "/userquestionanswer/{userQuestionAnswerId}", method = RequestMethod.PATCH)
    public StringWrapper editUserQuestionAnswer(@RequestBody UserQuestionAnswer userQuestionAnswer, @PathVariable String userQuestionAnswerId) {
        try {
            String msg = "";
            if(userQuestionAnswer==null) {
                msg = "添加问卷答案表参数不正确";
            }else if(userQuestionAnswer.getQuestionnaireId() != null){
                msg = "问卷答案表问卷ID不能为空";
            }else if(userQuestionAnswer.getQuestionNo() != null){
                msg = "问卷答案表题号不能为空";
            }else if(userQuestionAnswer.getAccountId() != null){
                msg = "问卷答案表用户id不能为空";
            }else if(StringUtils.isBlank(userQuestionAnswer.getContent())){
                msg = "问卷答案表内容不能为空";
            }else if(userQuestionAnswer.getContent().length() > 255){
                msg = "问卷答案表内容长度不可超过255";

            }

            if(StringUtils.isNotBlank(msg)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), msg);
            }

            UserQuestionAnswer userQuestionAnswer_old = (UserQuestionAnswer) userQuestionAnswerService.view(userQuestionAnswer.getId());
            if(userQuestionAnswer==null) {
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "修改失败，系统未找到相关数据");
            }

            if(userQuestionAnswer.getId() != null){
                userQuestionAnswer_old.setId(userQuestionAnswer.getId());
            }
            if(userQuestionAnswer.getQuestionnaireId() != null){
                userQuestionAnswer_old.setQuestionnaireId(userQuestionAnswer.getQuestionnaireId());
            }
            if(userQuestionAnswer.getQuestionNo() != null){
                userQuestionAnswer_old.setQuestionNo(userQuestionAnswer.getQuestionNo());
            }
            if(userQuestionAnswer.getAccountId() != null){
                userQuestionAnswer_old.setAccountId(userQuestionAnswer.getAccountId());
            }
            if(!StringUtils.isBlank(String.valueOf(ObjectUtils.defaultIfNull(userQuestionAnswer.getContent(), "")))){
                userQuestionAnswer_old.setContent(userQuestionAnswer.getContent());
            }


            int row=userQuestionAnswerService.edit(userQuestionAnswer_old);
            if(row > 0) {
                return new StringWrapper("修改问卷答案表成功");
            }else{
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "修改问卷答案表失败");
            }
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "修改问卷答案表数据异常");
        }
    }


    /**
     * 获取单问卷答案表实体
     *
     * @param userQuestionAnswerId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userquestionanswer/{userQuestionAnswerId}", method = RequestMethod.GET)
    public UserQuestionAnswer getUserQuestionAnswer(@PathVariable String userQuestionAnswerId) {
        try {
            if(StringUtils.isBlank(userQuestionAnswerId)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "参数不正确！");
            }
            Map<String,Object> whereParams = new HashMap<String, Object>();
            whereParams.put("id", userQuestionAnswerId);
            UserQuestionAnswer userQuestionAnswer= (UserQuestionAnswer) userQuestionAnswerService.viewOne(null, whereParams, null);
            if(null == userQuestionAnswer){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "系统未找到问卷答案表相关数据！");
            }
            return userQuestionAnswer;
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "获取单问卷答案表实体异常");
        }
    }


    /**
     * 问卷答案表数据输出 带分页
     *
     * @param userQuestionAnswer 过滤条件
     * @param page      第几页
     * @return 业务数据列表实体，最终转换为json数据输出
     * @throws ServletException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/userquestionanswerlist", method = RequestMethod.POST)
    public BizData4Page userQuestionAnswerlist(UserQuestionAnswer userQuestionAnswer, Integer page) {
        try {
            Map<String, Object> whereParams = new HashMap<String, Object>();
            if(userQuestionAnswer.getId() != null){
                whereParams.put("id", new SearchField("id", "=", userQuestionAnswer.getId()));
            }
            if(userQuestionAnswer.getQuestionnaireId() != null){
                whereParams.put("questionnaireId", new SearchField("questionnaireId", "=", userQuestionAnswer.getQuestionnaireId()));
            }
            if(userQuestionAnswer.getQuestionNo() != null){
                whereParams.put("questionNo", new SearchField("questionNo", "=", userQuestionAnswer.getQuestionNo()));
            }
            if(userQuestionAnswer.getAccountId() != null){
                whereParams.put("accountId", new SearchField("accountId", "=", userQuestionAnswer.getAccountId()));
            }
            if(!StringUtils.isBlank(String.valueOf(ObjectUtils.defaultIfNull(userQuestionAnswer.getContent(), "")))){
                whereParams.put("content", new SearchField("content", "like", "%" + userQuestionAnswer.getContent() + "%"));
            }


            //other props filter
            whereParams.put("groupOp", "and");

        return userQuestionAnswerService.queryPage(null, whereParams, page, 10, 10, null);
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "查询问卷答案表数据异常");
        }
    }
}
