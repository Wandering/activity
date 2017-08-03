package com.power.yuneng.activity.rpc.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.power.core.exception.BizException;
import com.power.core.utils.RtnCodeEnum;
import com.power.yuneng.activity.entity.*;
import com.power.yuneng.activity.entity.dto.UserActivityDTO;
import com.power.yuneng.activity.entity.dto.UserActivityExDTO;
import com.power.yuneng.activity.entity.enums.QusetionProgressEnum;
import com.power.yuneng.activity.api.IActivityNotify;
import com.power.yuneng.activity.service.*;
import com.power.yuneng.activity.service.ex.IUserQuestionAnswerExService;
import com.power.yuneng.activity.service.ex.IVipExService;
import com.power.yuneng.user.IVoucherService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by Administrator on 2017/7/28.
 */
@Service("ActivityNotifyImpl")
public class ActivityNotifyImpl implements IActivityNotify{
    private static final Logger logger = LoggerFactory.getLogger(ActivityNotifyImpl.class);
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
    @Autowired
    private IBonusesVipService bonusesVipService;
    @Autowired
    private IUserBonusesVipService userBonusesVipService;
    @Autowired
    private IVoucherService voucherService;
    @Autowired
    private IVipExService vipExService;

    private static final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(4);

    /**
     * 赠送奖励
     * @param userActivity
     * @return
     */
    @Override
    public boolean giveBonuses(UserActivityExDTO userActivity) {
        Activity activity = activityService.view(userActivity.getActivityId());
        long currTime = System.currentTimeMillis()/1000;
        if (activity==null){
            throw new BizException(RtnCodeEnum.ACTIVITY_NOT_EXIST.getValue(), RtnCodeEnum.ACTIVITY_NOT_EXIST.getDesc());
        }
        if (activity.getStartTime()>currTime){
            throw new BizException(RtnCodeEnum.ACTIVITY_NOT_START.getValue(), RtnCodeEnum.ACTIVITY_NOT_START.getDesc());
        }
        if (activity.getEndTime()<currTime){
            throw new BizException(RtnCodeEnum.ACTIVITY_END.getValue(), RtnCodeEnum.ACTIVITY_END.getDesc());
        }
        if (userActivity==null){
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "参数为空");
        }
        if (userActivity.getUserId()==null){
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "用户ID为空");
        }
        if (userActivity.getQuestionnaireId()==null){
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "问卷编号为空");
        }
        UserQuestion userQuestion = userQuestionService.view(userActivity.getQuestionnaireId());
        if (userQuestion == null){
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "问卷不存在");
        }
        if (userActivity.getActivityId()==null){
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "活动编码为空");
        }

        Map<String,Object> map = new HashMap<>();
        map .put("accountId",userActivity.getUserId());
        map .put("activityId",userActivity.getActivityId());
        ActivityUser activityUser = activityUserService.viewOne(map);
        if (activityUser == null){
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(),"非法提交");
        }
        //判断当前活动奖励
        if (activity.getBonuses()==1){
            BonusesVip bonusesVip = bonusesVipService.view(activity.getContent());
            if (bonusesVip.getType()==1){

            }else if (bonusesVip.getType()==2){
                //延时
                map = new HashMap<>();
                map.put("accountId",userActivity.getUserId());
                map.put("bonusesVipId",bonusesVip.getId());
                UserBonusesVip userBonusesVip = userBonusesVipService.viewOne(map);
                if (userBonusesVip ==null){
                    userBonusesVip = new UserBonusesVip();
                    userBonusesVip.setAccountId(userActivity.getUserId());
                    userBonusesVip.setBonusesVipId(bonusesVip.getId());
                    userBonusesVip.setStatus(0);
                    userBonusesVip.setActivityId(userActivity.getActivityId());
                    userBonusesVip.setCreateTime(currTime);
                    userBonusesVip.setGiveTime(currTime);
                    userBonusesVipService.create(userBonusesVip);
                    activityUser.setUpdateTime(System.currentTimeMillis()/1000);
                    activityUser.setProgress(QusetionProgressEnum.END_ASYNC.getValue());
                    activityUserService.edit(activityUser);
                    String uniqueKey = userActivity.getUniqueKey();
                    scheduledThreadPool.submit(() -> {
                        Map<String,String> msg = vipExService.getSendMsg(uniqueKey,"ACTIVITY_VIP_MSG");
                        Map<String,Map<String,Object>> sendMap = JSON.parseObject(msg.get("contentModel"),HashMap.class);
                        List<WxMpTemplateData>  list = new ArrayList<>();
                        WxMpTemplateData data = null;
                        Iterator<Map.Entry<String, Map<String,Object>>> $it = sendMap.entrySet().iterator();
                        while ($it.hasNext()){
                            Map.Entry<String,Map<String,Object>> it =  $it.next();
                            String key = it.getKey();
                            Map<String,Object> values = it.getValue();
                            data = new WxMpTemplateData();
                            data.setName(key);
                            data.setValue(values.get("value").toString());
                            data.setColor(values.get("color").toString());
                            list.add(data);
                        }
                        voucherService.sendTemplateMsg(userActivity.getUniqueKey(),msg.get("msgId"),userActivity.getOpenId(),list);
                    });
                }
            }
        }

        return true;
    }



    /**
     * 是否可以领取奖励
     * @param userActivity
     * @return
     */
    @Override
    public boolean hasGiveBonuses(UserActivityExDTO userActivity) {
        Activity activity = activityService.view(userActivity.getActivityId());
        long currTime = System.currentTimeMillis()/1000;
        if (activity==null){
            throw new BizException(RtnCodeEnum.ACTIVITY_NOT_EXIST.getValue(), RtnCodeEnum.ACTIVITY_NOT_EXIST.getDesc());
        }
        if (activity.getStartTime()>currTime){
            throw new BizException(RtnCodeEnum.ACTIVITY_NOT_START.getValue(), RtnCodeEnum.ACTIVITY_NOT_START.getDesc());
        }
        if (activity.getEndTime()<currTime){
            throw new BizException(RtnCodeEnum.ACTIVITY_END.getValue(), RtnCodeEnum.ACTIVITY_END.getDesc());
        }
        if (userActivity==null){
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "参数为空");
        }
        if (userActivity.getUserId()==null){
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "用户ID为空");
        }
        if (userActivity.getQuestionnaireId()==null){
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "问卷编号为空");
        }
        UserQuestion userQuestion = userQuestionService.view(userActivity.getQuestionnaireId());
        if (userQuestion == null){
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "问卷不存在");
        }
        if (userActivity.getActivityId()==null){
            throw new BizException(RtnCodeEnum.UNKNOW.getValue(), "活动编码为空");
        }

        Map<String,Object> map = new HashMap<>();
        map .put("accountId",userActivity.getUserId());
        map .put("activityId",userActivity.getActivityId());
        ActivityUser activityUser = activityUserService.viewOne(map);
        if (activityUser == null){
            return false;
        }
        //判断当前活动奖励是否已经发放或正在发放
        if (activityUser.getProgress()>=QusetionProgressEnum.END_ASYNC.getValue()){
            logger.debug("奖励已经发放或正在发放！");
            return false;
        }
        logger.debug("奖励没有发放");
        return true;
    }


    /**
     * 查询当前正在进行的活动
     *
     * @return
     */
    @Override
    public Activity getActivityById(Integer id,String uniqueKey) {
        return activityService.view(id);
    }
}
