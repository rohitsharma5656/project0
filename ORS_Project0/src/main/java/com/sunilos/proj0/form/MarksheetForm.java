package com.sunilos.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.sunilos.proj0.dto.BaseDTO;
import com.sunilos.proj0.dto.MarksheetDTO;

public class MarksheetForm extends BaseForm {

	@NotEmpty
	@Pattern(regexp = "[a-zA-Z0-9]*$", message = "{Pattern.form.rollNo}")
	private String rollNo;

	@NotNull
	@Min(0)
	@Max(100)
	private Integer physics;

	@NotNull
	@Min(0)
	@Max(100)
	private Integer chemistry;

	@NotNull
	@Min(0)
	@Max(100)
	private Integer maths;

	@NotNull
	protected Long studentId;

	private String name;

	private Long[] Ids;

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public Integer getPhysics() {
		return physics;
	}

	public void setPhysics(Integer physics) {
		this.physics = physics;
	}

	public Integer getChemistry() {
		return chemistry;
	}

	public void setChemistry(Integer chemistry) {
		this.chemistry = chemistry;
	}

	public Integer getMaths() {
		return maths;
	}

	public void setMaths(Integer maths) {
		this.maths = maths;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long[] getIds() {
		return Ids;
	}

	public void setIds(Long[] ids) {
		Ids = ids;
	}

	@Override
	public BaseDTO getDto() {
		MarksheetDTO dto = new MarksheetDTO();
		dto.setId(id);
		dto.setMaths(maths);
		dto.setPhysics(physics);
		dto.setChemistry(chemistry);
		dto.setRollNo(rollNo);
		dto.setStudentId(studentId);
		dto.setName(name);
		dto.setCreatedBy(createdBy);
		/* dto.setModifiedBy(modifiedBy); */
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		return dto;

	}

	@Override
	public void populate(BaseDTO bDto) {
		MarksheetDTO dto = (MarksheetDTO) bDto;
		id = dto.getId();
		maths = dto.getMaths();
		physics = dto.getPhysics();
		chemistry = dto.getChemistry();
		rollNo = dto.getRollNo();
		studentId = dto.getStudentId();
		name = dto.getName();
		createdBy = dto.getCreatedBy();
		/* modifiedBy = dto.getModifiedBy(); */
		createdDatetime = dto.getCreatedDatetime().getTime();
		modifiedDatetime = dto.getModifiedDatetime().getTime();
	}
}
