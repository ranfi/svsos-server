package com.svsos.api.common.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//JPA 基类的标识
@MappedSuperclass
public abstract class IdEntity<T> implements Serializable {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 3575164986276939882L;
	protected T id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}
}