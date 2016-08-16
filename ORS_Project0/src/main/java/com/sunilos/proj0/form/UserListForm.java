package com.sunilos.proj0.form;

import com.sunilos.proj0.dto.BaseDTO;
import com.sunilos.proj0.dto.UserDTO;

public class UserListForm extends BaseForm {

	private String firstName;

	private String login;

	private long roleId;
	
	private long Ids;
	
	

	public long getIds() {
		return Ids;
	}

	public void setIds(long ids) {
		Ids = ids;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	@Override
	public BaseDTO getDto() {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setRoleId(roleId);
		dto.setFirstName(firstName);
		dto.setLogin(login);
		

		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		UserDTO dto = (UserDTO) bDto;
		id = dto.getId();
		roleId = dto.getRoleId();
		firstName = dto.getFirstName();
		login = dto.getLogin();
		super.populate(bDto);
	}
}
