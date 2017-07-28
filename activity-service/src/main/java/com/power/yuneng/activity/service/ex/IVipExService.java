package com.power.yuneng.activity.service.ex;


import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 */
public interface IVipExService {
    boolean giveVip(List<Long> ids,Integer type,Long start,Long end);
    boolean updateBonusesVip(List<Long> ids,Integer status);
}
