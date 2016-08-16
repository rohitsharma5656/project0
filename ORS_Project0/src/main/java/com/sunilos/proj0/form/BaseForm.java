package com.sunilos.proj0.form;

import com.sunilos.proj0.dto.BaseDTO;

/**
 * Contains generic attributes of a form. It is extended by all form beans.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public class BaseForm {

	protected long id;

	private int pageNo = 1;

	private int pageSize = 5;

	private String operation;

	protected String createdBy;

	protected String modifiedBy;

	protected long createdDatetime;

	protected long modifiedDatetime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModeifiedBy() {
		return modifiedBy;
	}

	public void setModeifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public long getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(long createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public long getModifiedDatetime() {
		return modifiedDatetime;
	}

	public void setModifiedDatetime(long modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	/**
	 * Converts from into dto.
	 * 
	 * @return
	 */

	public BaseDTO getDto() {
		return null;
	}

	/**
	 * Converts fto into form.
	 * 
	 * @param bDto
	 */

	public void populate(BaseDTO bDto) {

	}
}
