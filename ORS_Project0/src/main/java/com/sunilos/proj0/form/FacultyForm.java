package com.sunilos.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.sunilos.proj0.dto.BaseDTO;
import com.sunilos.proj0.dto.FacultyDTO;

public class FacultyForm extends BaseForm {

	@NotNull
	private Long userId;

	@NotNull
	private Long collegeId;

	private String facultyName;
	private String login;
	private String collegeName;

	private Long[] ids;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	@Override
	public void populate(BaseDTO bDto) {
		FacultyDTO dto = (FacultyDTO) bDto;
		id = dto.getId();
		userId = dto.getUserId();
		facultyName = dto.getFacultyName();
		login = dto.getLogin();
		collegeId = dto.getCollegeId();
		collegeName = dto.getCollegeName();
		createdBy = dto.getCreatedBy();
		/* modifiedBy = dto.getModifiedBy(); */
		createdDatetime = dto.getCreatedDatetime().getTime();
		modifiedDatetime = dto.getModifiedDatetime().getTime();
	}

	@Override
	public BaseDTO getDto() {
		FacultyDTO dto = new FacultyDTO();
		dto.setId(id);
		dto.setUserId(userId);
		dto.setFacultyName(facultyName);
		dto.setLogin(login);
		dto.setCollegeId(collegeId);
		dto.setCollegeName(collegeName);
		dto.setCreatedBy(createdBy);
		/* dto.setModifiedBy(modifiedBy); */
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		return dto;
	}
}
