package com.svsos.api.common.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "lx_ms_task_record")
public class MsTaskRecord  extends IdEntity<Long>{

	private static final long serialVersionUID = 7415539221365781082L;

	private Long taskId;
	private Long masterUid;
	private Long servantUid;
	private Long msRelationshipId;
	private Timestamp acceptTime;
	private Timestamp endTime;
	private Timestamp createTime;
	private Integer score;
	private String comment;
	private Integer status;	
	
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public Long getMasterUid() {
		return masterUid;
	}
	public void setMasterUid(Long masterUid) {
		this.masterUid = masterUid;
	}
	public Long getServantUid() {
		return servantUid;
	}
	public void setServantUid(Long servantUid) {
		this.servantUid = servantUid;
	}
	public Timestamp getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(Timestamp acceptTime) {
		this.acceptTime = acceptTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getMsRelationshipId() {
		return msRelationshipId;
	}
	public void setMsRelationshipId(Long msRelationshipId) {
		this.msRelationshipId = msRelationshipId;
	}
	
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
