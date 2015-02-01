package com.svsos.backend.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.svsos.backend.entity.IdEntity;

@Entity
@Table(name = "base_product_category")
public class ProductCategory extends IdEntity<Integer> {

	private static final long serialVersionUID = 1386920690525988463L;

	private String name;
	private Integer parentId;
	private Integer level;
	private Integer status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
