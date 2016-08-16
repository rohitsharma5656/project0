package com.sunilos.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.sunilos.proj0.dto.BaseDTO;
import com.sunilos.proj0.dto.StudentDTO;
import com.sunilos.proj0.util.Util;

public class StudentForm extends BaseForm {

	@NotEmpty
	@Pattern(regexp = "[a-zA-Z]*$", message = "{Pattern.form.firstName}")
	private String firstName;

	@NotEmpty
	@Pattern(regexp = "[a-zA-z]*$", message = "{Pattern.form.lastName}")
	private String lastName;

	/*@NotEmpty
	private String dob;*/
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull 
	 private Date dob;

	@NotEmpty
	@Pattern(regexp = "([7-9]{1}[0-9]{9})*$", message = "{Pattern.form.mobileNo}")
	private String mobileNo;

	@NotEmpty
	@Email
	private String emailId;

	@NotNull
	private Long collegeId;
	private String collegeName;
	private Long[] Ids;

	
	/**
	 * @return the collegeId
	 */
	public Long getCollegeId() {
		return collegeId;
	}

	/**
	 * @param collegeId the collegeId to set
	 */
	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

	/**
	 * @return the ids
	 */
	public Long[] getIds() {
		return Ids;
	}

	/**
	 * @param ids the ids to set
	 */
	public void setIds(Long[] ids) {
		Ids = ids;
	}

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

	/*public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
*/
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the collegeName
	 */
	public String getCollegeName() {
		return collegeName;
	}

	/**
	 * @param collegeName
	 *            the collegeName to set
	 */
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public BaseDTO getDto() {
		StudentDTO dto = new StudentDTO();
		dto.setId(id);
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setDob(dob);
		dto.setMobileNo(mobileNo);
		dto.setEmail(emailId);
		dto.setCollegeId(collegeId);
		dto.setCollegeName(collegeName);
		dto.setCreatedBy(createdBy);
		/* dto.setModifiedBy(modifiedBy); */
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		StudentDTO dto = (StudentDTO) bDto;
		id = dto.getId();
		firstName = dto.getFirstName();
		lastName = dto.getLastName();
		dob = dto.getDob();
		mobileNo = dto.getMobileNo();
		emailId = dto.getEmail();
		collegeId = dto.getCollegeId();
		collegeName = dto.getCollegeName();
		createdBy = dto.getCreatedBy();
		/* modifiedBy = dto.getModifiedBy(); */
		createdDatetime = dto.getCreatedDatetime().getTime();
		modifiedDatetime = dto.getModifiedDatetime().getTime();
	}

	
}
