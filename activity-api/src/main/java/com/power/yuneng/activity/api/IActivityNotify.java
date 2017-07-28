package com.power.yuneng.activity.api;

import com.power.yuneng.activity.entity.dto.UserActivityDTO;

/**
 * Created by Administrator on 2017/7/28.
 */
public interface IActivityNotify {
    boolean giveBonuses(UserActivityDTO userActivity);
}
