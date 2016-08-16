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
import com.sunilos.proj0.dto.CollegeDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.form.CollegeForm;
import com.sunilos.proj0.service.CollegeServiceInt;

@Controller
@RequestMapping(value = "/rest/college")
public class CollegeRestfullWS extends BaseCtl {

	private static Logger log = Logger.getLogger(CollegeRestfullWS.class);

	@Autowired
	private CollegeServiceInt service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CollegeDTO get(@PathVariable long id) throws ApplicationException {
		CollegeDTO dto = service.findByPK(id);
		return dto;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public List getList(CollegeForm form) throws ApplicationException {
		CollegeDTO dto = (CollegeDTO) form.getDto();
		return service.search(dto, form.getPageNo(), form.getPageSize());
	}

	/*@RequestMapping(value = "/{id}")
	@ResponseBody
	public CollegeDTO delete(@PathVariable long id) throws ApplicationException {
		CollegeDTO dto = service.findByPK(id);
		service.delete(id);
		return dto;
	}*/
}
