package com.sunilos.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.sunilos.proj0.dto.BaseDTO;
import com.sunilos.proj0.dto.TimeTableDTO;

public class TimeTableForm extends BaseForm {

	@NotNull
	private Long userId;

	@NotNull
	private Long courseId;

	@NotEmpty
	private String subjectName;

	@NotEmpty
	private String day;

	@NotEmpty
	private String time;

	private String facultyName;
	private String courseName;

	private Long[] ids;

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
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

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
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

	@Override
	public BaseDTO getDto() {
		TimeTableDTO dto = new TimeTableDTO();
		dto.setId(id);
		dto.setUserId(userId);
		dto.setFacultyName(facultyName);
		dto.setCourseId(courseId);
		dto.setCourseName(courseName);
		dto.setSubjectName(subjectName);
		dto.setDay(day);
		dto.setTime(time);
		dto.setCreatedBy(createdBy);
		/* dto.setModifiedBy(modifiedBy); */
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		TimeTableDTO dto = (TimeTableDTO) bDto;
		id = dto.getId();
		userId = dto.getUserId();
		facultyName = dto.getFacultyName();
		courseId = dto.getCourseId();
		courseName = dto.getCourseName();
		subjectName = dto.getSubjectName();
		day = dto.getDay();
		time = dto.getTime();
		createdBy = dto.getCreatedBy();
		/* modifiedBy = dto.getModifiedBy(); */
		createdDatetime = dto.getCreatedDatetime().getTime();
		modifiedDatetime = dto.getModifiedDatetime().getTime();
	}
}
