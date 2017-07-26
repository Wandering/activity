package com.power.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-26 19:39:25
 */
public class UserQuestionnaireEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//userId
	private Integer userId;
	//电话号码
	private String phone;
	//填表时间
	private Long createDt;
	//性别
	private String sex;
	//年龄
	private Integer age;
	//是否用过充电宝
	private String havaUse;
	//不使用的原因
	private String cannotUse;
	//经常使用哪种充电宝
	private String usePower;
	//使用频率
	private String useFrequency;
	//在需要时能否找到身边的充电吧
	private String findPower;
	//使用的场所
	private String userLocal;
	//故障的概率
	private String fault;
	//租借的方式
	private String borrowModel;
	//建议
	private String suggest;

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
	 * 设置：userId
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：电话号码
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：电话号码
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：填表时间
	 */
	public void setCreateDt(Long createDt) {
		this.createDt = createDt;
	}
	/**
	 * 获取：填表时间
	 */
	public Long getCreateDt() {
		return createDt;
	}
	/**
	 * 设置：性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * 获取：年龄
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * 设置：是否用过充电宝
	 */
	public void setHavaUse(String havaUse) {
		this.havaUse = havaUse;
	}
	/**
	 * 获取：是否用过充电宝
	 */
	public String getHavaUse() {
		return havaUse;
	}
	/**
	 * 设置：不使用的原因
	 */
	public void setCannotUse(String cannotUse) {
		this.cannotUse = cannotUse;
	}
	/**
	 * 获取：不使用的原因
	 */
	public String getCannotUse() {
		return cannotUse;
	}
	/**
	 * 设置：经常使用哪种充电宝
	 */
	public void setUsePower(String usePower) {
		this.usePower = usePower;
	}
	/**
	 * 获取：经常使用哪种充电宝
	 */
	public String getUsePower() {
		return usePower;
	}
	/**
	 * 设置：使用频率
	 */
	public void setUseFrequency(String useFrequency) {
		this.useFrequency = useFrequency;
	}
	/**
	 * 获取：使用频率
	 */
	public String getUseFrequency() {
		return useFrequency;
	}
	/**
	 * 设置：在需要时能否找到身边的充电吧
	 */
	public void setFindPower(String findPower) {
		this.findPower = findPower;
	}
	/**
	 * 获取：在需要时能否找到身边的充电吧
	 */
	public String getFindPower() {
		return findPower;
	}
	/**
	 * 设置：使用的场所
	 */
	public void setUserLocal(String userLocal) {
		this.userLocal = userLocal;
	}
	/**
	 * 获取：使用的场所
	 */
	public String getUserLocal() {
		return userLocal;
	}
	/**
	 * 设置：故障的概率
	 */
	public void setFault(String fault) {
		this.fault = fault;
	}
	/**
	 * 获取：故障的概率
	 */
	public String getFault() {
		return fault;
	}
	/**
	 * 设置：租借的方式
	 */
	public void setBorrowModel(String borrowModel) {
		this.borrowModel = borrowModel;
	}
	/**
	 * 获取：租借的方式
	 */
	public String getBorrowModel() {
		return borrowModel;
	}
	/**
	 * 设置：建议
	 */
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	/**
	 * 获取：建议
	 */
	public String getSuggest() {
		return suggest;
	}
}
