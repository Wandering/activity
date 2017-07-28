





/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 15:06:12 $ 
 */
package com.power.yuneng.activity.entity;
import com.power.core.domain.BaseDomain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.*;

public class Activity extends BaseDomain<Integer> {
    /** 活动名称 */
    private String name;
    /** 活动类型 */
    private Integer type;
    /** 奖励 */
    private Integer bonuses;
    /** 奖励内容 */
    private Integer content;
    /** 开始时间(1234567890)unix标准时间 */
    private Long startTime;
    /** 结束时间(1234567890)unix标准时间 */
    private Long endTime;

	public Activity(){
	}
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setType(Integer value) {
        this.type = value;
    }

    public Integer getType() {
        return this.type;
    }
    public void setBonuses(Integer value) {
        this.bonuses = value;
    }

    public Integer getBonuses() {
        return this.bonuses;
    }
    public void setContent(Integer value) {
        this.content = value;
    }

    public Integer getContent() {
        return this.content;
    }
    public void setStartTime(Long value) {
        this.startTime = value;
    }

    public Long getStartTime() {
        return this.startTime;
    }
    public void setEndTime(Long value) {
        this.endTime = value;
    }

    public Long getEndTime() {
        return this.endTime;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Type",getType())
			.append("Bonuses",getBonuses())
			.append("Content",getContent())
			.append("StartTime",getStartTime())
			.append("EndTime",getEndTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Activity == false) return false;
		if(this == obj) return true;
		Activity other = (Activity)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

