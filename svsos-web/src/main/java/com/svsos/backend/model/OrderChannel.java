package com.svsos.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.svsos.backend.entity.IdEntity;

@Entity
@Table(name = "base_order_channel")
public class OrderChannel extends IdEntity<Integer> {

	private static final long serialVersionUID = 9179334435039873163L;

	private String name;

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
