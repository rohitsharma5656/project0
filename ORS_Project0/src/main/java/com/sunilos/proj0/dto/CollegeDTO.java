package com.sunilos.proj0.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User DTO class
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Entity
@Table(name = "ST_COLLEGE")
public class CollegeDTO extends BaseDTO {
	/**
	 * Name of College
	 */
	@Column(name = "NAME", length = 50)
	private String name;
	/**
	 * Address of College
	 */
	@Column(name = "ADDRESS", length = 50)
	private String address;
	/**
	 * State of College
	 */
	@Column(name = "STATE", length = 50)
	private String state;
	/**
	 * City of College
	 */
	@Column(name = "CITY", length = 50)
	private String city;
	/**
	 * Phoneno of College
	 */
	@Column(name = "MOBILE_NO", length = 15)
	private String mobileNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * @param mobileNo
	 *            the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public String getKey() {

		return id + "";
	}

	@Override
	public String getValue() {

		return name;
	}

}
