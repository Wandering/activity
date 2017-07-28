package com.power.yuneng.activity.service.ex.impl;

import com.power.yuneng.activity.dao.ex.IVipExDAO;
import com.power.yuneng.activity.service.ex.IVipExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public boolean updateBonusesVip(List<Long> ids, Integer status) {
        return vipExDAO.updateBonusesVip(ids,status);
    }
}
