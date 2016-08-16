package com.sunilos.proj0.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.proj0.dao.CollegeDAOInt;
import com.sunilos.proj0.dto.CollegeDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.exception.DuplicateRecordException;

/**
 * Session facade of College Service. It is transactional, apply delcarative
 * transactions with help of Spring AOP.
 * 
 * If unchecked exception is propagated from a method then transaction will be
 * rolled back.
 * 
 * Default propogation value is Propagation.REQUIRED and readOnly = false
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Service(value = "collegeService")
public class CollegeServiceSpringImpl implements CollegeServiceInt {

	@Autowired
	private CollegeDAOInt dao;

	public void setDao(CollegeDAOInt dao) {
		this.dao = dao;
	}

	private static Logger log = Logger
			.getLogger(CollegeServiceSpringImpl.class);

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long add(CollegeDTO dto) throws DuplicateRecordException {
		log.debug("College Service add started");
		CollegeDTO existDto = dao.findByName(dto.getName());
		if (existDto != null) {
			throw new DuplicateRecordException("College Name is already Exist");
		}
		long id = dao.add(dto);
		return id;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(CollegeDTO dto) throws DuplicateRecordException {
		log.debug("College Service update started");
		/*CollegeDTO existDto = dao.findByName(dto.getName());
		if (existDto != null && existDto.getId() != dto.getId()) {
			throw new DuplicateRecordException("Record is alredy exist");
		}*/
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		log.debug("College Service delete started");
		dao.delete(id);
		log.debug("College Service delete End");

	}

	@Transactional(readOnly = true)
	public CollegeDTO findByName(String name) {

		return dao.findByName(name);
	}

	@Transactional(readOnly = true)
	public CollegeDTO findByPK(long pk) {

		return dao.findByPK(pk);
	}

	@Transactional(readOnly = true)
	public List search(CollegeDTO dto, int pageNo, int pageSize) {

		return dao.search(dto, pageNo, pageSize);
	}

	@Transactional(readOnly = true)
	public List search(CollegeDTO dto) {

		return dao.search(dto);
	}

	@Transactional(readOnly = true)
	public List list() {

		return dao.list();
	}

	@Transactional(readOnly = true)
	public List list(int pageNo, int pageSize) {

		return dao.list(pageNo, pageSize);
	}

}
