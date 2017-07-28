/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 15:06:14 $ 
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
        import com.power.yuneng.activity.entity.ActivityUser;
        import com.power.yuneng.activity.service.IActivityUserService;
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
public class ApiActivityUserController{
    private static final Logger logger = LoggerFactory.getLogger(ApiActivityUserController.class);

    @Autowired
    private IActivityUserService activityUserService;

   /**
     * 新增 用户活动参与表
     * @param activityUser
     * @return  处理条数
     */
    @ResponseBody
    @RequestMapping(value = "/activityuser", method = RequestMethod.POST)
    public Object addActivityUser(@RequestBody ActivityUser activityUser) {
        try {
            String msg = "";

            if(activityUser==null) {
                msg = "添加用户活动参与表参数不正确";
            }else if(activityUser.getAccountId() != null){
                msg = "用户活动参与表用户业务ID不能为空";
            }else if(activityUser.getActivityId() != null){
                msg = "用户活动参与表用户参加活动ID不能为空";
            }else if(activityUser.getProgress() != null){
                msg = "用户活动参与表用户处理活动进度不能为空";
            }else if(activityUser.getCreateTime() != null){
                msg = "用户活动参与表创建时间(1234567890)unix标准时间不能为空";
            }else if(activityUser.getUpdateTime() != null){
                msg = "用户活动参与表更新时间(1234567890)unix标准时间不能为空";
            }

            if(StringUtils.isNotBlank(msg)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), msg);
            }

            activityUserService.create(activityUser);
            return new BooleanWrapper(true);
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "新增用户活动参与表异常");
        }
    }


    /**
     * 删除 用户活动参与表
     *
     * @param {activityUserId} 用户活动参与表ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/activityuser/{activityUserId}" ,method = RequestMethod.DELETE)
    public Object delActivityUser(@PathVariable String activityUserId) {
        try {
            if(StringUtils.isBlank(activityUserId)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "删除用户活动参与表失败，参数不正确");
            }
            activityUserService.deleteByCondition(ConditionBuilder.condition("id", SearchEnum.eq, activityUserId));
            return new BooleanWrapper(true);
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "删除用户活动参与表异常");
        }
    }


    /**
     * 修改用户活动参与表数据
     * @param activityUser 提交上来的用户活动参与表JSON数据
     * @param activityUserId  用户活动参与表ID
     * @return  修改条数
     */
    @ResponseBody
    @RequestMapping(value = "/activityuser/{activityUserId}", method = RequestMethod.PATCH)
    public StringWrapper editActivityUser(@RequestBody ActivityUser activityUser, @PathVariable String activityUserId) {
        try {
            String msg = "";
            if(activityUser==null) {
                msg = "添加用户活动参与表参数不正确";
            }else if(activityUser.getAccountId() != null){
                msg = "用户活动参与表用户业务ID不能为空";
            }else if(activityUser.getActivityId() != null){
                msg = "用户活动参与表用户参加活动ID不能为空";
            }else if(activityUser.getProgress() != null){
                msg = "用户活动参与表用户处理活动进度不能为空";
            }else if(activityUser.getCreateTime() != null){
                msg = "用户活动参与表创建时间(1234567890)unix标准时间不能为空";
            }else if(activityUser.getUpdateTime() != null){
                msg = "用户活动参与表更新时间(1234567890)unix标准时间不能为空";

            }

            if(StringUtils.isNotBlank(msg)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), msg);
            }

            ActivityUser activityUser_old = (ActivityUser) activityUserService.view(activityUser.getId());
            if(activityUser==null) {
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "修改失败，系统未找到相关数据");
            }

            if(activityUser.getId() != null){
                activityUser_old.setId(activityUser.getId());
            }
            if(activityUser.getAccountId() != null){
                activityUser_old.setAccountId(activityUser.getAccountId());
            }
            if(activityUser.getActivityId() != null){
                activityUser_old.setActivityId(activityUser.getActivityId());
            }
            if(activityUser.getProgress() != null){
                activityUser_old.setProgress(activityUser.getProgress());
            }
            if(activityUser.getCreateTime() != null){
                activityUser_old.setCreateTime(activityUser.getCreateTime());
            }
            if(activityUser.getUpdateTime() != null){
                activityUser_old.setUpdateTime(activityUser.getUpdateTime());
            }


            int row=activityUserService.edit(activityUser_old);
            if(row > 0) {
                return new StringWrapper("修改用户活动参与表成功");
            }else{
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "修改用户活动参与表失败");
            }
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "修改用户活动参与表数据异常");
        }
    }


    /**
     * 获取单用户活动参与表实体
     *
     * @param activityUserId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/activityuser/{activityUserId}", method = RequestMethod.GET)
    public ActivityUser getActivityUser(@PathVariable String activityUserId) {
        try {
            if(StringUtils.isBlank(activityUserId)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "参数不正确！");
            }
            Map<String,Object> whereParams = new HashMap<String, Object>();
            whereParams.put("id", activityUserId);
            ActivityUser activityUser= (ActivityUser) activityUserService.viewOne(null, whereParams, null);
            if(null == activityUser){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "系统未找到用户活动参与表相关数据！");
            }
            return activityUser;
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "获取单用户活动参与表实体异常");
        }
    }


    /**
     * 用户活动参与表数据输出 带分页
     *
     * @param activityUser 过滤条件
     * @param page      第几页
     * @return 业务数据列表实体，最终转换为json数据输出
     * @throws ServletException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/activityuserlist", method = RequestMethod.POST)
    public BizData4Page activityUserlist(ActivityUser activityUser, Integer page) {
        try {
            Map<String, Object> whereParams = new HashMap<String, Object>();
            if(activityUser.getId() != null){
                whereParams.put("id", new SearchField("id", "=", activityUser.getId()));
            }
            if(activityUser.getAccountId() != null){
                whereParams.put("accountId", new SearchField("accountId", "=", activityUser.getAccountId()));
            }
            if(activityUser.getActivityId() != null){
                whereParams.put("activityId", new SearchField("activityId", "=", activityUser.getActivityId()));
            }
            if(activityUser.getProgress() != null){
                whereParams.put("progress", new SearchField("progress", "=", activityUser.getProgress()));
            }
            if(activityUser.getCreateTime() != null){
                whereParams.put("createTime", new SearchField("createTime", "=", activityUser.getCreateTime()));
            }
            if(activityUser.getUpdateTime() != null){
                whereParams.put("updateTime", new SearchField("updateTime", "=", activityUser.getUpdateTime()));
            }


            //other props filter
            whereParams.put("groupOp", "and");

        return activityUserService.queryPage(null, whereParams, page, 10, 10, null);
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "查询用户活动参与表数据异常");
        }
    }
}
