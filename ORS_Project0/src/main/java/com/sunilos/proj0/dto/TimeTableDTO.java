package com.sunilos.proj0.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ST_TIMETABLE")
public class TimeTableDTO extends BaseDTO {
	@Column(name = "USER_ID", length = 20)
	private Long userId;

	@Column(name = "FACULTY_NAME", length = 50)
	private String facultyName;

	@Column(name = "COURSE_ID", length = 20)
	private Long courseId;

	@Column(name = "COURSE_NAME", length = 50)
	private String courseName;

	@Column(name = "SUBJECT_NAME", length = 50)
	private String subjectName;

	@Column(name = "DAY", length = 20)
	private String day;

	@Column(name = "TIME")
	private String time;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
