package com.svsos.api.common.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "lx_user_flowers")
public class UserFlowers extends IdEntity<Long>{
	private static final long serialVersionUID = 5359260641274132536L;
	private Long uid;
	private Long toUid;
	private Integer amount;
	private Integer type;
	private Timestamp createTime;
	
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Long getToUid() {
		return toUid;
	}
	public void setToUid(Long toUid) {
		this.toUid = toUid;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	
}
