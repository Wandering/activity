package com.power.yuneng.activity.service.ex;


import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/28.
 */
public interface IVipExService {
    boolean giveVip(List<Long> ids,Integer type,Long start,Long end);
    boolean updateBonusesVip(List<Long> ids,Integer status);
    Map<String,String> getSendMsg(String uniqueKey, String msgName);
}
