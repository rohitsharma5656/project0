package com.sunilos.proj0.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunilos.proj0.ctl.BaseCtl;
import com.sunilos.proj0.dto.UserDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.form.UserForm;
import com.sunilos.proj0.service.UserServiceInt;

@Controller
@RequestMapping(value = "/rest/User")
public class UserRestfullWS extends BaseCtl {

	private static Logger log = Logger.getLogger(UserRestfullWS.class);

	@Autowired
	private UserServiceInt service;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public UserDTO get(@PathVariable long id) throws ApplicationException {
		UserDTO dto = service.findByPK(id);
		return dto;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/search")
	@ResponseBody
	public List getList(UserForm form) throws ApplicationException {
		UserDTO dto = (UserDTO) form.getDto();

		return service.search(dto, form.getPageNo(), form.getPageSize());

	}
/*
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public UserDTO delete(@PathVariable long id) throws ApplicationException {
		UserDTO dto = service.findByPK(id);
		service.delete(id);
		return dto;
	}*/
}
