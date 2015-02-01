package com.svsos.backend.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ss_sp_worker")
public class WorkUser implements Serializable {

	private static final long serialVersionUID = 52286212785215867L;
	private Integer id;
	private String account;
	private String loginPwd;
	private String name;
	private String mobilephone;
	private String address;
	private String weixin;
	private String skill;
	private Integer acceptedOrderNum = 0;
	private Integer finishedOrderNum = 0;

	private Integer complaintNum = 0;
	private Integer todoOrderNum = 0;
	private Timestamp createTime;
	private Timestamp loginTime;
	private Integer status = 1;
	private Integer spId = 0;
	private String wxId;

	private ServicePoint servicePoint;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public Integer getAcceptedOrderNum() {
		return acceptedOrderNum;
	}

	public void setAcceptedOrderNum(Integer acceptedOrderNum) {
		this.acceptedOrderNum = acceptedOrderNum;
	}

	public Integer getFinishedOrderNum() {
		return finishedOrderNum;
	}

	public void setFinishedOrderNum(Integer finishedOrderNum) {
		this.finishedOrderNum = finishedOrderNum;
	}

	public Integer getComplaintNum() {
		return complaintNum;
	}

	public void setComplaintNum(Integer complaintNum) {
		this.complaintNum = complaintNum;
	}

	public Integer getTodoOrderNum() {
		return todoOrderNum;
	}

	public void setTodoOrderNum(Integer todoOrderNum) {
		this.todoOrderNum = todoOrderNum;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "sp_id")
	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	@ManyToOne
	@JoinColumn(name = "sp_id", referencedColumnName = "sp_id", insertable = false, updatable = false)
	public ServicePoint getServicePoint() {
		return servicePoint;
	}

	public void setServicePoint(ServicePoint servicePoint) {
		this.servicePoint = servicePoint;
	}

}
