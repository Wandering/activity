package com.power.yuneng.activity.service.ex.impl;

import com.power.yuneng.activity.dao.ex.IVipExDAO;
import com.power.yuneng.activity.service.ex.IVipExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/28.
 */
@Service
public class VipExServiceImpl implements IVipExService{
    @Autowired
    private IVipExDAO vipExDAO;

    @Override
    public boolean giveVip(List<Long> ids, Integer type, Long start, Long end) {
        return vipExDAO.giveVip(ids,type,start,end);
    }

    @Override
    public boolean updateBonusesVip(List<Long> ids,Integer activityId, Integer status) {
        return vipExDAO.updateBonusesVip(ids,activityId,status);
    }

    @Override
    public boolean updateActivityUser(Integer currProgress, Integer nextProgress, Integer activityId) {
        return vipExDAO.updateActivityUser(currProgress,nextProgress,activityId);
    }

    @Override
    public Map<String, String> getSendMsg(String uniqueKey, String msgName) {
        return vipExDAO.getSendMsg(uniqueKey,msgName);
    }
}
