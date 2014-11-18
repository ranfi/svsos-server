/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.svsos.backend.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class IdEntity<T> implements Serializable  {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 2453343059251110613L;
	
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
