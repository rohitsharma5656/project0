package com.sunilos.proj0.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.proj0.dao.CourseDAOInt;
import com.sunilos.proj0.dto.CourseDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.exception.DuplicateRecordException;
import com.sunilos.proj0.exception.RecordNotFoundException;

@Service(value = "courseService")
public class CourseServiceSpringImpl implements CourseServiceInt {

	@Autowired
	private CourseDAOInt dao;

	public void setDao(CourseDAOInt dao) {
		this.dao = dao;
	}

	private static Logger log = Logger.getLogger(CourseServiceSpringImpl.class);

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long add(CourseDTO dto) throws DuplicateRecordException {
		CourseDTO duplicateCourse = dao.findByName(dto.getCourseName());
		if (duplicateCourse != null) {
			throw new DuplicateRecordException("Course is already Exist");
		}
		long id = dao.add(dto);
		return id;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(CourseDTO dto) throws DuplicateRecordException {
		/*
		 * CourseDTO duplicateCourse = dao.findByName(dto.getCourseName(),
		 * dto.getSubject()); if (duplicateCourse == null) { throw new
		 * DuplicateRecordException("Course is already Exist"); }
		 */
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		dao.delete(id);

	}

	@Transactional(readOnly = true)
	public CourseDTO findByName(String name, String sub) {

		return dao.findByName(name);
	}

	@Transactional(readOnly = true)
	public CourseDTO findByPK(long pk) {

		return dao.findByPK(pk);
	}

	@Transactional(readOnly = true)
	public List search(CourseDTO dto, int pageNo, int pageSize) {

		return dao.search(dto, pageNo, pageSize);
	}

	@Transactional(readOnly = true)
	public List search(CourseDTO dto) {

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
