package com.svsos.api.common.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@Table(name = "lx_chat_msg")
public class ChatMsg extends IdEntity<Long>{

	private static final long serialVersionUID = -8194966903326748914L;
	private Long fromUid;
	private Long toUid;
	private Integer status = 0;
	private Long relationshipId;
	private Integer type;
	private String content;
	private Long lianxiMsgId;
	
	private Timestamp updateTime;
	private Timestamp sendTime;
	
	
	public Long getFromUid() {
		return fromUid;
	}
	public void setFromUid(Long fromUid) {
		this.fromUid = fromUid;
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getSendTime() {
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
	
	
	public Long getRelationshipId() {
		return relationshipId;
	}
	public void setRelationshipId(Long relationshipId) {
		this.relationshipId = relationshipId;
	}
	public Long getToUid() {
		return toUid;
	}
	public void setToUid(Long toUid) {
		this.toUid = toUid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Long getLianxiMsgId() {
		return lianxiMsgId;
	}
	public void setLianxiMsgId(Long lianxiMsgId) {
		this.lianxiMsgId = lianxiMsgId;
	}
	
	
	
}
