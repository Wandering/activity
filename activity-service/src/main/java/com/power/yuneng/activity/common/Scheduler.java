package com.power.yuneng.activity.common;

import com.power.core.cache.IRedisRepository;
import com.power.core.cache.RedisRepository;
import com.power.yuneng.activity.entity.BonusesVip;
import com.power.yuneng.activity.entity.UserBonusesVip;
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
    private static final String flag = "giveVip";
    @Scheduled(cron = "0 0 0 ? * ? ") //每天零点执行一次
    public void statusGiveVip() {
        if (!repository.exists(flag)) {
            synchronized (Scheduler.class) {
                if (!repository.exists(flag)) {
                    repository.set(flag,true,10, TimeUnit.MINUTES);
                    Map<Integer,List<UserBonusesVip>> map = new HashMap<>();
                    Map<String,Object> params = new HashMap<>();
                    params.put("status",1);
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
                        vipExService.giveVip(ids,bonusesVip.getVipId(),bonusesVip.getStartTime(),bonusesVip.getEndTime());
                        vipExService.updateBonusesVip(ids,1);
                    }

                }
            }
        }
    }

}