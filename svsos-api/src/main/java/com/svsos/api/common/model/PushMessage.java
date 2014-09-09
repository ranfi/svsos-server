package com.svsos.api.common.model;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@Table(name = "lx_push_message")
public class PushMessage extends IdEntity<Long>{

	private static final long serialVersionUID = 8307512681430775773L;

	private Long fromUid;
	private Long toUid;
	private String content;
	private Integer acceptStatus;
	private Integer type;
	private Timestamp sendTime;
	private Timestamp updateTime;
	
	public Long getFromUid() {
		return fromUid;
	}
	public void setFromUid(Long fromUid) {
		this.fromUid = fromUid;
	}
	public Long getToUid() {
		return toUid;
	}
	public void setToUid(Long toUid) {
		this.toUid = toUid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getAcceptStatus() {
		return acceptStatus;
	}
	public void setAcceptStatus(Integer acceptStatus) {
		this.acceptStatus = acceptStatus;
	}
	public Timestamp getSendTime() {
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
