package com.sunilos.proj0.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ST_FACULTY")
public class FacultyDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;
	/**
	 * Faculty Name
	 */
	@Column(name = "FACULTYNAME", length = 50)
	private String facultyName;
	/**
	 * Faculty Name
	 */
	@Column(name = "LOGIN", length = 50)
	private String login;
	/**
	 * College Name
	 */
	@Column(name = "COLLEGENAME", length = 50)
	private String collegeName;
	/**
	 * College Id Foreign Key
	 */
	@Column(name = "COLLEGEID", length = 20)
	private Long collegeId;
	/**
	 * User Id Foreign Key
	 */
	@Column(name = "USERID", length = 20)
	private Long userId;

	/**
	 * accessor
	 */

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

	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String getKey() {

		return String.valueOf(id);
	}

	@Override
	public String getValue() {

		return facultyName + "-" + login;
	}

}
