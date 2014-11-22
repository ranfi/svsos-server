package com.svsos.backend.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "ss_menu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Menu extends IdEntity<Integer>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2875422511614334286L;
	private String name;
	private String linkAddress;
	private Integer isLeafNode;
	private Integer pid;
	private Long createUid;
	private Timestamp createTime;
	private Integer status;
	private Integer sortKey;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLinkAddress() {
		return linkAddress;
	}
	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}
	public Integer getIsLeafNode() {
		return isLeafNode;
	}
	public void setIsLeafNode(Integer isLeafNode) {
		this.isLeafNode = isLeafNode;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Long getCreateUid() {
		return createUid;
	}
	public void setCreateUid(Long createUid) {
		this.createUid = createUid;
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
	public Integer getSortKey() {
		return sortKey;
	}
	public void setSortKey(Integer sortKey) {
		this.sortKey = sortKey;
	}
	
	
}
