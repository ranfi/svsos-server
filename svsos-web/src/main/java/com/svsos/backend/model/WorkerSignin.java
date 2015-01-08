package com.svsos.backend.model;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "ss_sp_worker_signin")
public class WorkerSignin implements Serializable {

	private static final long serialVersionUID = 8711171123886969324L;
	private Integer id;
	private Integer spId;
	private Integer workerId;
	// 经度
	private BigDecimal lngBaidu;
	// 纬度
	private BigDecimal latBaidu;
	private String location;
	// 签到日期
	private Date signinDate;
	// 签到详细时间
	private Timestamp signinTime;
	private Integer status = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public Integer getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Integer workerId) {
		this.workerId = workerId;
	}

	public BigDecimal getLngBaidu() {
		return lngBaidu;
	}

	public void setLngBaidu(BigDecimal lngBaidu) {
		this.lngBaidu = lngBaidu;
	}

	public BigDecimal getLatBaidu() {
		return latBaidu;
	}

	public void setLatBaidu(BigDecimal latBaidu) {
		this.latBaidu = latBaidu;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getSigninDate() {
		return signinDate;
	}

	public void setSigninDate(Date signinDate) {
		this.signinDate = signinDate;
	}


	public Timestamp getSigninTime() {
		return signinTime;
	}

	public void setSigninTime(Timestamp signinTime) {
		this.signinTime = signinTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
