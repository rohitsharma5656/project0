package com.sunilos.proj0.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Role POJO class. It is persistent object.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Entity
@Table(name = "ST_ROLE")
public class RoleDTO extends BaseDTO {
	/**
	 * Predefined Role constants
	 */

	public static final int ADMIN = 1;
	public static final int STUDENT = 2;
	public static final int COLLEGE_SCHOOL = 3;
	public static final int KIOSK = 4;
	public static final int FACULTY = 5;

	@Column(name = "NAME", length = 50)
	private String roleName;

	@Column(name = "DESCRIPTION", length = 70)
	private String roleDescription;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@Override
	public String getKey() {

		return String.valueOf(id);
	}

	@Override
	public String getValue() {

		return roleName;
	}

}
