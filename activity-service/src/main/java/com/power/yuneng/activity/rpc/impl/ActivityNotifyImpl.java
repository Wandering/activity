package com.power.yuneng.activity.rpc.impl;

import com.power.core.exception.BizException;
import com.power.core.utils.RtnCodeEnum;
import com.power.yuneng.activity.entity.*;
import com.power.yuneng.activity.entity.dto.UserActivityDTO;
import com.power.yuneng.activity.entity.enums.QusetionProgressEnum;
import com.power.yuneng.activity.api.IActivityNotify;
import com.power.yuneng.activity.service.*;
import com.power.yuneng.activity.service.ex.IUserQuestionAnswerExService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
    /**
     * 赠送奖励
     * @param userActivity
     * @return
     */
    @Override
    public boolean giveBonuses(UserActivityDTO userActivity) {
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
                    userBonusesVip.setCreateTime(currTime);
                    userBonusesVip.setGiveTime(currTime);
                    userBonusesVipService.create(userBonusesVip);
                    activityUser.setUpdateTime(System.currentTimeMillis()/1000);
                    activityUser.setProgress(QusetionProgressEnum.END.getValue());
                    activityUserService.edit(activityUser);
                }
            }
        }
        return true;
    }
}
