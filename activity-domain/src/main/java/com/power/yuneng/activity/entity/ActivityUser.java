





/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 15:06:14 $ 
 */
package com.power.yuneng.activity.entity;
import com.power.core.domain.BaseDomain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.*;

public class ActivityUser extends BaseDomain<Long> {
    /** 用户业务ID */
    private Long accountId;
    /** 用户参加活动ID */
    private Integer activityId;
    /** 用户处理活动进度 */
    private Integer progress;
    /** 创建时间(1234567890)unix标准时间 */
    private Long createTime;
    /** 更新时间(1234567890)unix标准时间 */
    private Long updateTime;

	public ActivityUser(){
	}
    public void setAccountId(Long value) {
        this.accountId = value;
    }

    public Long getAccountId() {
        return this.accountId;
    }
    public void setActivityId(Integer value) {
        this.activityId = value;
    }

    public Integer getActivityId() {
        return this.activityId;
    }
    public void setProgress(Integer value) {
        this.progress = value;
    }

    public Integer getProgress() {
        return this.progress;
    }
    public void setCreateTime(Long value) {
        this.createTime = value;
    }

    public Long getCreateTime() {
        return this.createTime;
    }
    public void setUpdateTime(Long value) {
        this.updateTime = value;
    }

    public Long getUpdateTime() {
        return this.updateTime;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("AccountId",getAccountId())
			.append("ActivityId",getActivityId())
			.append("Progress",getProgress())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ActivityUser == false) return false;
		if(this == obj) return true;
		ActivityUser other = (ActivityUser)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

