package com.power.yuneng.activity.entity.dto;

/**
 * Created by Administrator on 2017/8/1.
 */
public class UserActivityExDTO extends UserActivityDTO{
    private String uniqueKey;
    private String openId;

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
