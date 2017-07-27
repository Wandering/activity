package com.power.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-27 18:58:56
 */
public class UserActivityExEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer userId;
	//
	private Integer activityId;
	//
	private Long createDt;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	/**
	 * 获取：
	 */
	public Integer getActivityId() {
		return activityId;
	}
	/**
	 * 设置：
	 */
	public void setCreateDt(Long createDt) {
		this.createDt = createDt;
	}
	/**
	 * 获取：
	 */
	public Long getCreateDt() {
		return createDt;
	}
}
