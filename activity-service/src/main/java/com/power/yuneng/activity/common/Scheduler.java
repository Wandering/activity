package com.power.yuneng.activity.common;

import com.alibaba.fastjson.JSON;
import com.power.core.cache.IRedisRepository;
import com.power.core.cache.RedisRepository;
import com.power.yuneng.activity.entity.ActivityUser;
import com.power.yuneng.activity.entity.BonusesVip;
import com.power.yuneng.activity.entity.UserBonusesVip;
import com.power.yuneng.activity.entity.enums.QusetionProgressEnum;
import com.power.yuneng.activity.service.IActivityUserService;
import com.power.yuneng.activity.service.IBonusesVipService;
import com.power.yuneng.activity.service.IUserBonusesVipService;
import com.power.yuneng.activity.service.ex.IVipExService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/7/28.
 */
@Component
public class Scheduler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IVipExService vipExService;

    @Autowired
    private RedisRepository repository;
    @Autowired
    private IBonusesVipService bonusesVipService;
    @Autowired
    private IUserBonusesVipService userBonusesVipService;
    @Autowired
    private IActivityUserService activityUserService;
    private static final String flag = "giveVip";

    /**
     * 定时任务
     * 延时赠送VIP会员
     */
    @Scheduled(cron = "0 0/5 * * * ? ") //每天零点执行一次
    public void statusGiveVip() {
        if (!repository.exists(flag)) {
            synchronized (Scheduler.class) {
                if (!repository.exists(flag)) {
                    repository.set(flag,true,4, TimeUnit.MINUTES);
                    Map<Integer,List<UserBonusesVip>> map = new HashMap<>();
                    Map<String,Object> params = new HashMap<>();
                    params.put("status",0);
                    List<UserBonusesVip> userBonusesVips = userBonusesVipService.viewList(params);
                    Iterator<UserBonusesVip> iterator = userBonusesVips.iterator();
                    while (iterator.hasNext()){
                        List<UserBonusesVip> userBonusesVips1 = null;
                        UserBonusesVip userBonusesVip = iterator.next();
                        if (map.containsKey(userBonusesVip.getBonusesVipId())){
                            userBonusesVips1 = map.get(userBonusesVip.getBonusesVipId());
                        }else {
                            userBonusesVips1 = new ArrayList<>();
                            map.put(userBonusesVip.getBonusesVipId(),userBonusesVips1);
                        }
                        userBonusesVips1.add(userBonusesVip);
                    }
                    Iterator<Map.Entry<Integer,List<UserBonusesVip>>> $map = map.entrySet().iterator();
                    while ($map.hasNext()) {
                        Map.Entry<Integer,List<UserBonusesVip>> entry = $map.next();
                        Integer key = entry.getKey();
                        List<UserBonusesVip> listValue = entry.getValue();

                        BonusesVip bonusesVip =bonusesVipService.view(key);
                        List<Long> ids = new ArrayList<>();
                        for (UserBonusesVip userBonusesVip : listValue) {
                            ids.add(userBonusesVip.getAccountId());
                        }
                        Integer activityId = userBonusesVips.get(0).getActivityId();
//                        Integer currProgress,Integer nextProgress,Integer activityId
                        List<Long> userVips = vipExService.queryUserVip();
                        ids.remove(userVips);
                        vipExService.giveVip2(ids,bonusesVip.getVipId(),bonusesVip.getStartTime(),bonusesVip.getEndTime());
                        vipExService.updateBonusesVip(ids,activityId,1);
                        vipExService.updateActivityUser(QusetionProgressEnum.END.getValue(), QusetionProgressEnum.END.getValue(),activityId);
                        logger.info("奖励发放完成!\n活动编号:{}\nids:{}\n时间:{}",activityId, JSON.toJSONString(ids),System.currentTimeMillis());
                    }
                }
            }
        }
    }

}