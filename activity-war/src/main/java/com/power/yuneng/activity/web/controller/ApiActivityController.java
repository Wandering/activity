/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 15:06:12 $ 
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
import com.power.yuneng.activity.entity.Activity;
import com.power.yuneng.activity.service.IActivityService;
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
public class ApiActivityController{
    private static final Logger logger = LoggerFactory.getLogger(ApiActivityController.class);

    @Autowired
    private IActivityService activityService;

   /**
     * 新增 活动表
     * @param activity
     * @return  处理条数
     */
    @ResponseBody
    @RequestMapping(value = "/activity", method = RequestMethod.POST)
    public Object addActivity(@RequestBody Activity activity) {
        try {
            String msg = "";

            if(activity==null) {
                msg = "添加活动表参数不正确";
            }else if(activity.getName() != null){
                msg = "活动表活动名称不能为空";
            }else if(activity.getType() != null){
                msg = "活动表活动类型不能为空";
            }else if(activity.getStartTime() != null){
                msg = "活动表开始时间(1234567890)unix标准时间不能为空";
            }else if(activity.getEndTime() != null){
                msg = "活动表结束时间(1234567890)unix标准时间不能为空";
            }

            if(StringUtils.isNotBlank(msg)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), msg);
            }

            activityService.create(activity);
            return new BooleanWrapper(true);
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "新增活动表异常");
        }
    }


    /**
     * 删除 活动表
     *
     * @param {activityId} 活动表ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/activity/{activityId}" ,method = RequestMethod.DELETE)
    public Object delActivity(@PathVariable String activityId) {
        try {
            if(StringUtils.isBlank(activityId)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "删除活动表失败，参数不正确");
            }
            activityService.deleteByCondition(ConditionBuilder.condition("id", SearchEnum.eq, activityId));
            return new BooleanWrapper(true);
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "删除活动表异常");
        }
    }


    /**
     * 修改活动表数据
     * @param activity 提交上来的活动表JSON数据
     * @param activityId  活动表ID
     * @return  修改条数
     */
    @ResponseBody
    @RequestMapping(value = "/activity/{activityId}", method = RequestMethod.PATCH)
    public StringWrapper editActivity(@RequestBody Activity activity, @PathVariable String activityId) {
        try {
            String msg = "";
            if(activity==null) {
                msg = "添加活动表参数不正确";
            }else if(activity.getName() != null){
                msg = "活动表活动名称不能为空";
            }else if(activity.getType() != null){
                msg = "活动表活动类型不能为空";
            }else if(activity.getStartTime() != null){
                msg = "活动表开始时间(1234567890)unix标准时间不能为空";
            }else if(activity.getEndTime() != null){
                msg = "活动表结束时间(1234567890)unix标准时间不能为空";

            }

            if(StringUtils.isNotBlank(msg)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), msg);
            }

            Activity activity_old = (Activity) activityService.view(activity.getId());
            if(activity==null) {
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "修改失败，系统未找到相关数据");
            }

            if(activity.getId() != null){
                activity_old.setId(activity.getId());
            }
            if(activity.getName() != null){
                activity_old.setName(activity.getName());
            }
            if(activity.getType() != null){
                activity_old.setType(activity.getType());
            }
            if(activity.getBonuses() != null){
                activity_old.setBonuses(activity.getBonuses());
            }
            if(activity.getContent() != null){
                activity_old.setContent(activity.getContent());
            }
            if(activity.getStartTime() != null){
                activity_old.setStartTime(activity.getStartTime());
            }
            if(activity.getEndTime() != null){
                activity_old.setEndTime(activity.getEndTime());
            }


            int row=activityService.edit(activity_old);
            if(row > 0) {
                return new StringWrapper("修改活动表成功");
            }else{
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "修改活动表失败");
            }
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "修改活动表数据异常");
        }
    }


    /**
     * 获取单活动表实体
     *
     * @param activityId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/activity/{activityId}", method = RequestMethod.GET)
    public Activity getActivity(@PathVariable String activityId) {
        try {
            if(StringUtils.isBlank(activityId)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "参数不正确！");
            }
            Map<String,Object> whereParams = new HashMap<String, Object>();
            whereParams.put("id", activityId);
            Activity activity= (Activity) activityService.viewOne(null, whereParams, null);
            if(null == activity){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "系统未找到活动表相关数据！");
            }
            return activity;
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "获取单活动表实体异常");
        }
    }


    /**
     * 活动表数据输出 带分页
     *
     * @param activity 过滤条件
     * @param page      第几页
     * @return 业务数据列表实体，最终转换为json数据输出
     * @throws ServletException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/activitylist", method = RequestMethod.POST)
    public BizData4Page activitylist(Activity activity, Integer page) {
        try {
            Map<String, Object> whereParams = new HashMap<String, Object>();
            if(activity.getId() != null){
                whereParams.put("id", new SearchField("id", "=", activity.getId()));
            }
            if(activity.getName() != null){
                whereParams.put("name", new SearchField("name", "=", activity.getName()));
            }
            if(activity.getType() != null){
                whereParams.put("type", new SearchField("type", "=", activity.getType()));
            }
            if(activity.getBonuses() != null){
                whereParams.put("bonuses", new SearchField("bonuses", "=", activity.getBonuses()));
            }
            if(activity.getContent() != null){
                whereParams.put("content", new SearchField("content", "=", activity.getContent()));
            }
            if(activity.getStartTime() != null){
                whereParams.put("startTime", new SearchField("startTime", "=", activity.getStartTime()));
            }
            if(activity.getEndTime() != null){
                whereParams.put("endTime", new SearchField("endTime", "=", activity.getEndTime()));
            }


            //other props filter
            whereParams.put("groupOp", "and");

        return activityService.queryPage(null, whereParams, page, 10, 10, null);
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "查询活动表数据异常");
        }
    }
}
