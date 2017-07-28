/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 15:06:15 $ 
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
import com.power.yuneng.activity.entity.BonusesVip;
import com.power.yuneng.activity.service.IBonusesVipService;
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
public class ApiBonusesVipController{
    private static final Logger logger = LoggerFactory.getLogger(ApiBonusesVipController.class);

    @Autowired
    private IBonusesVipService bonusesVipService;

   /**
     * 新增 赠送VIP表
     * @param bonusesVip
     * @return  处理条数
     */
    @ResponseBody
    @RequestMapping(value = "/bonusesvip", method = RequestMethod.POST)
    public Object addBonusesVip(@RequestBody BonusesVip bonusesVip) {
        try {
            String msg = "";

            if(bonusesVip==null) {
                msg = "添加赠送VIP表参数不正确";
            }else if(bonusesVip.getVipId() != null){
                msg = "赠送VIP表赠送VIPID不能为空";
            }else if(bonusesVip.getType() != null){
                msg = "赠送VIP表赠送类型不能为空";
            }else if(bonusesVip.getStartTime() != null){
                msg = "赠送VIP表生效时间(1234567890)unix标准时间不能为空";
            }

            if(StringUtils.isNotBlank(msg)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), msg);
            }

            bonusesVipService.create(bonusesVip);
            return new BooleanWrapper(true);
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "新增赠送VIP表异常");
        }
    }


    /**
     * 删除 赠送VIP表
     *
     * @param {bonusesVipId} 赠送VIP表ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/bonusesvip/{bonusesVipId}" ,method = RequestMethod.DELETE)
    public Object delBonusesVip(@PathVariable String bonusesVipId) {
        try {
            if(StringUtils.isBlank(bonusesVipId)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "删除赠送VIP表失败，参数不正确");
            }
            bonusesVipService.deleteByCondition(ConditionBuilder.condition("id", SearchEnum.eq, bonusesVipId));
            return new BooleanWrapper(true);
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "删除赠送VIP表异常");
        }
    }


    /**
     * 修改赠送VIP表数据
     * @param bonusesVip 提交上来的赠送VIP表JSON数据
     * @param bonusesVipId  赠送VIP表ID
     * @return  修改条数
     */
    @ResponseBody
    @RequestMapping(value = "/bonusesvip/{bonusesVipId}", method = RequestMethod.PATCH)
    public StringWrapper editBonusesVip(@RequestBody BonusesVip bonusesVip, @PathVariable String bonusesVipId) {
        try {
            String msg = "";
            if(bonusesVip==null) {
                msg = "添加赠送VIP表参数不正确";
            }else if(bonusesVip.getVipId() != null){
                msg = "赠送VIP表赠送VIPID不能为空";
            }else if(bonusesVip.getType() != null){
                msg = "赠送VIP表赠送类型不能为空";
            }else if(bonusesVip.getStartTime() != null){
                msg = "赠送VIP表生效时间(1234567890)unix标准时间不能为空";

            }

            if(StringUtils.isNotBlank(msg)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), msg);
            }

            BonusesVip bonusesVip_old = (BonusesVip) bonusesVipService.view(bonusesVip.getId());
            if(bonusesVip==null) {
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "修改失败，系统未找到相关数据");
            }

            if(bonusesVip.getId() != null){
                bonusesVip_old.setId(bonusesVip.getId());
            }
            if(bonusesVip.getVipId() != null){
                bonusesVip_old.setVipId(bonusesVip.getVipId());
            }
            if(bonusesVip.getType() != null){
                bonusesVip_old.setType(bonusesVip.getType());
            }
            if(bonusesVip.getStartTime() != null){
                bonusesVip_old.setStartTime(bonusesVip.getStartTime());
            }
            if(bonusesVip.getEndTime() != null){
                bonusesVip_old.setEndTime(bonusesVip.getEndTime());
            }


            int row=bonusesVipService.edit(bonusesVip_old);
            if(row > 0) {
                return new StringWrapper("修改赠送VIP表成功");
            }else{
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "修改赠送VIP表失败");
            }
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "修改赠送VIP表数据异常");
        }
    }


    /**
     * 获取单赠送VIP表实体
     *
     * @param bonusesVipId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/bonusesvip/{bonusesVipId}", method = RequestMethod.GET)
    public BonusesVip getBonusesVip(@PathVariable String bonusesVipId) {
        try {
            if(StringUtils.isBlank(bonusesVipId)){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "参数不正确！");
            }
            Map<String,Object> whereParams = new HashMap<String, Object>();
            whereParams.put("id", bonusesVipId);
            BonusesVip bonusesVip= (BonusesVip) bonusesVipService.viewOne(null, whereParams, null);
            if(null == bonusesVip){
                throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "系统未找到赠送VIP表相关数据！");
            }
            return bonusesVip;
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "获取单赠送VIP表实体异常");
        }
    }


    /**
     * 赠送VIP表数据输出 带分页
     *
     * @param bonusesVip 过滤条件
     * @param page      第几页
     * @return 业务数据列表实体，最终转换为json数据输出
     * @throws ServletException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/bonusesviplist", method = RequestMethod.POST)
    public BizData4Page bonusesViplist(BonusesVip bonusesVip, Integer page) {
        try {
            Map<String, Object> whereParams = new HashMap<String, Object>();
            if(bonusesVip.getId() != null){
                whereParams.put("id", new SearchField("id", "=", bonusesVip.getId()));
            }
            if(bonusesVip.getVipId() != null){
                whereParams.put("vipId", new SearchField("vipId", "=", bonusesVip.getVipId()));
            }
            if(bonusesVip.getType() != null){
                whereParams.put("type", new SearchField("type", "=", bonusesVip.getType()));
            }
            if(bonusesVip.getStartTime() != null){
                whereParams.put("startTime", new SearchField("startTime", "=", bonusesVip.getStartTime()));
            }
            if(bonusesVip.getEndTime() != null){
                whereParams.put("endTime", new SearchField("endTime", "=", bonusesVip.getEndTime()));
            }


            //other props filter
            whereParams.put("groupOp", "and");

        return bonusesVipService.queryPage(null, whereParams, page, 10, 10, null);
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "查询赠送VIP表数据异常");
        }
    }
}
