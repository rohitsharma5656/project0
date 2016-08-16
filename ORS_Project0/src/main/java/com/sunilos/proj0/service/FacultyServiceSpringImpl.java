package com.sunilos.proj0.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.proj0.dao.FacultyDAOInt;
import com.sunilos.proj0.dto.FacultyDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.exception.DuplicateRecordException;

@Service(value = "facultyService")
public class FacultyServiceSpringImpl implements FacultyServiceInt {

	@Autowired
	private FacultyDAOInt dao;

	public void setDao(FacultyDAOInt dao) {
		this.dao = dao;
	}

	private static Logger log = Logger
			.getLogger(FacultyServiceSpringImpl.class);

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long add(FacultyDTO dto) throws DuplicateRecordException {

		FacultyDTO duplicataFaculty = dao.findByUserId(dto.getUserId());

		// Check if updated Faculty already exist
		if (duplicataFaculty != null) {
			throw new DuplicateRecordException("Faculty already exists");
		}
		long id = dao.add(dto);
		return id;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(FacultyDTO dto) throws DuplicateRecordException {
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		dao.delete(id);

	}

	@Transactional(readOnly = true)
	public FacultyDTO findByUserId(Long uId) {

		return dao.findByUserId(uId);
	}

	@Transactional(readOnly = true)
	public FacultyDTO findByPK(long pk) {

		return dao.findByPK(pk);
	}

	@Transactional(readOnly = true)
	public List search(FacultyDTO dto, int pageNo, int pageSize) {

		return dao.search(dto, pageNo, pageSize);
	}

	@Transactional(readOnly = true)
	public List search(FacultyDTO dto) {

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
