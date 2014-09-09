package com.svsos.api.common.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "lx_user_registration")
public class UserRegistration implements Serializable{

	private static final long serialVersionUID = 7211227717423940233L;
	
	private Long uid;
	private Integer continuDays = 0;
	private Timestamp lastRegistTime;
	public Timestamp getLastRegistTime() {
		return lastRegistTime;
	}
	public void setLastRegistTime(Timestamp lastRegistTime) {
		this.lastRegistTime = lastRegistTime;
	}
	public Integer getContinuDays() {
		return continuDays;
	}
	public void setContinuDays(Integer continuDays) {
		this.continuDays = continuDays;
	}
	
	@Id
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
}
