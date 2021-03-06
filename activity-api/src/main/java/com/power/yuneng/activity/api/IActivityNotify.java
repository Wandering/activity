package com.power.yuneng.activity.api;

import com.power.yuneng.activity.entity.Activity;
import com.power.yuneng.activity.entity.dto.UserActivityDTO;
import com.power.yuneng.activity.entity.dto.UserActivityExDTO;

/**
 * Created by Administrator on 2017/7/28.
 */
public interface IActivityNotify {
    /**
     * 领取奖励
     * @param userActivity
     * @return
     */
    boolean giveBonuses(UserActivityExDTO userActivity);

    /**
     * 查询当前正在进行的活动
     * @return
     */
    Activity getActivityById(Integer id,String uniqueKey);
    /**
     * 是否可以领取奖励
     * @param userActivity
     * @return (true可领取，false不可领取)
     */
    boolean hasGiveBonuses(UserActivityExDTO userActivity);
}
