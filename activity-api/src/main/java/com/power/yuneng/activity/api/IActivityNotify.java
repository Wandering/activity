package com.power.yuneng.activity.api;

import com.power.yuneng.activity.entity.dto.UserActivityDTO;
import com.power.yuneng.activity.entity.dto.UserActivityExDTO;

/**
 * Created by Administrator on 2017/7/28.
 */
public interface IActivityNotify {
    boolean giveBonuses(UserActivityExDTO userActivity);
}
