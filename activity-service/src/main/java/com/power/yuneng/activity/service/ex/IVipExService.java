package com.power.yuneng.activity.service.ex;


import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/28.
 */
public interface IVipExService {
    boolean giveVip(List<Long> ids,Integer type,Long start,Long end);
    boolean giveVip2(List<Long> ids,Integer type,Long start,Long end);
    List<Long> queryUserVip();
    boolean updateBonusesVip(List<Long> ids,Integer activityId, Integer status);
    boolean updateActivityUser(Integer currProgress,Integer nextProgress,Integer activityId);
    Map<String,String> getSendMsg(String uniqueKey, String msgName);
}
