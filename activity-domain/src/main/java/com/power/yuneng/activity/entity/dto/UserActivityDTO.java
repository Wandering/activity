package com.power.yuneng.activity.entity.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 */
public class UserActivityDTO implements Serializable{
    /** 活动ID */
    private Integer activityId;
    /** 用户bizID == accountId */
    private Long userId;

    /** 问卷ID */
    private Integer questionnaireId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }


}
