





/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 15:06:15 $ 
 */
package com.power.yuneng.activity.entity;
import com.power.core.domain.BaseDomain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.*;

public class BonusesVip extends BaseDomain<Integer> {
    /** 赠送VIPID */
    private Integer vipId;
    /** 赠送类型 */
    private Integer type;
    /** 生效时间(1234567890)unix标准时间 */
    private Long startTime;
    /** 结束时间(1234567890)unix标准时间 */
    private Long endTime;

	public BonusesVip(){
	}
    public void setVipId(Integer value) {
        this.vipId = value;
    }

    public Integer getVipId() {
        return this.vipId;
    }
    public void setType(Integer value) {
        this.type = value;
    }

    public Integer getType() {
        return this.type;
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
			.append("VipId",getVipId())
			.append("Type",getType())
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
		if(obj instanceof BonusesVip == false) return false;
		if(this == obj) return true;
		BonusesVip other = (BonusesVip)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

