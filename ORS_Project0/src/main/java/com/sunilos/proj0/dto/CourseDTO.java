package com.sunilos.proj0.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ST_COURSE")
public class CourseDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Course Name
	 */
	@Column(name = "COURSE_NAME", length = 70)
	private String courseName;

		/**
	 * Course Description
	 */
	@Column(name = "DESCRIPTION", length = 255)
	private String description;

	/**
	 * accessor
	 */

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getKey() {

		return String.valueOf(id);
	}

	@Override
	public String getValue() {

		return courseName;
	}

}
