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
import com.sunilos.proj0.dto.RoleDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.form.RoleForm;
import com.sunilos.proj0.service.RoleServiceInt;

@Controller
@RequestMapping(value = "/rest/Role")
public class RoleRestfullWS extends BaseCtl {

	@Autowired
	private RoleServiceInt service;

	private static Logger log = Logger.getLogger(RoleRestfullWS.class);

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public RoleDTO get(@PathVariable long id) throws ApplicationException {
		RoleDTO dto = service.findByPK(id);
		return dto;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public List getList(RoleForm form) throws ApplicationException {
		RoleDTO dto = (RoleDTO) form.getDto();
		return service.search(dto, form.getPageNo(), form.getPageSize());
	}

	/*
	 * @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public RoleDTO delete(@PathVariable long id) throws
	 * ApplicationException { RoleDTO dto = service.findByPK(id);
	 * service.delete(id); return dto; }
	 */
}
