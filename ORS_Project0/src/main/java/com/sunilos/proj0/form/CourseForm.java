package com.sunilos.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.sunilos.proj0.dto.BaseDTO;
import com.sunilos.proj0.dto.CourseDTO;

public class CourseForm extends BaseForm {

	@NotEmpty
	@Pattern(regexp = "[a-zA-Z]*$", message = "{Pattern.form.courseName}")
	private String courseName;

	@NotEmpty
	private String description;

	private Long[] ids;

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

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	@Override
	public BaseDTO getDto() {
		CourseDTO dto = new CourseDTO();
		dto.setId(id);
		dto.setCourseName(courseName);
		dto.setDescription(description);
		dto.setCreatedBy(createdBy);
		// dto.setModifiedBy(modifiedBy);
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		CourseDTO dto = (CourseDTO) bDto;

		id = dto.getId();
		courseName = dto.getCourseName();
		description = dto.getDescription();
		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		if (dto.getCreatedDatetime() != null) {
			createdDatetime = dto.getCreatedDatetime().getTime();
		}
		if (dto.getModifiedDatetime() != null) {
			modifiedDatetime = dto.getModifiedDatetime().getTime();
		}
		super.populate(bDto);
	}
}
