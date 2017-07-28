





/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 19:50:50 $ 
 */
package com.power.yuneng.activity.entity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.power.core.domain.BaseDomain;;

import java.util.*;

public class UserBonusesVip extends BaseDomain<Long>{
    /** 用户业务ID */
    private Long accountId;
    /** 赠送用户vip列 */
    private Integer bonusesVipId;
    /** 创建时间(1234567890)unix标准时间 */
    private Long createTime;
    /** 赠送时间(1234567890)unix标准时间 */
    private Long giveTime;

    private Integer status;

	public UserBonusesVip(){
	}
    public void setAccountId(Long value) {
        this.accountId = value;
    }

    public Long getAccountId() {
        return this.accountId;
    }
    public void setBonusesVipId(Integer value) {
        this.bonusesVipId = value;
    }

    public Integer getBonusesVipId() {
        return this.bonusesVipId;
    }
    public void setCreateTime(Long value) {
        this.createTime = value;
    }

    public Long getCreateTime() {
        return this.createTime;
    }
    public void setGiveTime(Long value) {
        this.giveTime = value;
    }

    public Long getGiveTime() {
        return this.giveTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("AccountId",getAccountId())
			.append("BonusesVipId",getBonusesVipId())
			.append("CreateTime",getCreateTime())
			.append("GiveTime",getGiveTime())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserBonusesVip == false) return false;
		if(this == obj) return true;
		UserBonusesVip other = (UserBonusesVip)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

