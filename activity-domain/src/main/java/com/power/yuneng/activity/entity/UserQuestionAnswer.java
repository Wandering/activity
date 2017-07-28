





/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 15:06:17 $ 
 */
package com.power.yuneng.activity.entity;
import com.power.core.domain.BaseDomain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.*;

public class UserQuestionAnswer extends BaseDomain<Long> {
    /** 问卷ID */
    private Integer questionnaireId;
    /** 题号 */
    private Integer questionNo;
    /** 用户id */
    private Long accountId;
    /** 内容 */
    private String content;

	public UserQuestionAnswer(){
	}
    public void setQuestionnaireId(Integer value) {
        this.questionnaireId = value;
    }

    public Integer getQuestionnaireId() {
        return this.questionnaireId;
    }
    public void setQuestionNo(Integer value) {
        this.questionNo = value;
    }

    public Integer getQuestionNo() {
        return questionNo;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setContent(String value) {
        this.content = value;
    }

    public String getContent() {
        return this.content;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("QuestionnaireId",getQuestionnaireId())
			.append("QuestionNo",getQuestionNo())
			.append("AccountId",getAccountId())
			.append("Content",getContent())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserQuestionAnswer == false) return false;
		if(this == obj) return true;
		UserQuestionAnswer other = (UserQuestionAnswer)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

