package com.power.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-26 15:55:57
 */
public class PowerUserVipEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//用户accountId
	private Long id;
	//类型(对应modul表中的id)
	private Integer type;
	//会员起始时间
	private Long start;
	//结束时间
	private Long end;

	/**
	 * 设置：用户accountId
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：用户accountId
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：类型(对应modul表中的id)
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型(对应modul表中的id)
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：会员起始时间
	 */
	public void setStart(Long start) {
		this.start = start;
	}
	/**
	 * 获取：会员起始时间
	 */
	public Long getStart() {
		return start;
	}
	/**
	 * 设置：结束时间
	 */
	public void setEnd(Long end) {
		this.end = end;
	}
	/**
	 * 获取：结束时间
	 */
	public Long getEnd() {
		return end;
	}
}
