package com.sunilos.proj0.form;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.sunilos.proj0.dto.BaseDTO;
import com.sunilos.proj0.dto.UserDTO;
import com.sunilos.proj0.util.Util;

public class MyProfileForm extends BaseForm {

	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "FirstName does not contain space")
	private String firstName;

	@NotEmpty
	@Pattern(regexp = "[a-zA-Z]*")
	private String lastName;

	private String login;

	@NotEmpty
	private String gender;

	@NotEmpty
	@Pattern(regexp = "^[7-9][0-9]{9}$", message = "Mobile no start from 789 series")
	private String mobileNo;

	@NotEmpty
	private String dob;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public void populate(BaseDTO bDto) {
		UserDTO dto = (UserDTO) bDto;
		id = dto.getId();
		firstName = dto.getFirstName();
		lastName = dto.getLastName();
		login = dto.getLogin();
		mobileNo = dto.getMobileNo();
		gender = dto.getGender();
		dob = Util.getDate(dto.getDob());
		super.populate(bDto);

	};

	@Override
	public BaseDTO getDto() {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setLogin(login);
		dto.setGender(gender);
		dto.setMobileNo(mobileNo);
		dto.setDob(Util.getDate(dob));
		return dto;
	}
}
