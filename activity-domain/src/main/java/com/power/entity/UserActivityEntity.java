package com.power.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-27 17:51:05
 */
public class UserActivityEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//活动id
	private Integer activityId;
	//活动名
	private String activityName;
	//参与活动的用户权限
	private Integer userRoles;
	//推送活动的方式
	private String type;
	//
	private Long startDt;
	//
	private Long endDt;

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
	 * 设置：活动id
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	/**
	 * 获取：活动id
	 */
	public Integer getActivityId() {
		return activityId;
	}
	/**
	 * 设置：活动名
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	/**
	 * 获取：活动名
	 */
	public String getActivityName() {
		return activityName;
	}
	/**
	 * 设置：参与活动的用户权限
	 */
	public void setUserRoles(Integer userRoles) {
		this.userRoles = userRoles;
	}
	/**
	 * 获取：参与活动的用户权限
	 */
	public Integer getUserRoles() {
		return userRoles;
	}
	/**
	 * 设置：推送活动的方式
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：推送活动的方式
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：
	 */
	public void setStartDt(Long startDt) {
		this.startDt = startDt;
	}
	/**
	 * 获取：
	 */
	public Long getStartDt() {
		return startDt;
	}
	/**
	 * 设置：
	 */
	public void setEndDt(Long endDt) {
		this.endDt = endDt;
	}
	/**
	 * 获取：
	 */
	public Long getEndDt() {
		return endDt;
	}
}
