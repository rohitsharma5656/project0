package com.sunilos.proj0.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.sunilos.proj0.dto.BaseDTO;
import com.sunilos.proj0.dto.CollegeDTO;

/**
 * Contains College form elements and their declarative input validations.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public class CollegeForm extends BaseForm {

	@NotEmpty
	@Pattern(regexp = "[a-z A-Z ]*$", message = "{Pattern.form.collegeName}")
	private String name;

	@NotEmpty
	@Pattern(regexp = "[a-z A-Z 0-9 ]*$", message = "{Pattern.form.address}")
	private String address;

	@NotEmpty
	@Pattern(regexp = "[a-zA-Z]*$", message = "{Pattern.form.state}")
	private String state;

	@NotEmpty
	@Pattern(regexp = "[a-zA-Z]*$", message = "{Pattern.form.city}")
	private String city;

	@NotEmpty
	@Pattern(regexp = "([7-9]{1}[0-9]{9})*$", message = "{Pattern.form.mobileNo}")
	private String mobileNo;

	private long[] Ids;

	public long[] getIds() {
		return Ids;
	}

	public void setIds(long[] ids) {
		Ids = ids;
	}

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
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public BaseDTO getDto() {
		CollegeDTO dto = new CollegeDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setAddress(address);
		dto.setCity(city);
		dto.setState(state);
		dto.setMobileNo(mobileNo);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		CollegeDTO dto = (CollegeDTO) bDto;
		id = dto.getId();
		name = dto.getName();
		address = dto.getAddress();
		city = dto.getCity();
		state = dto.getState();
		mobileNo = dto.getMobileNo();

	}
}
