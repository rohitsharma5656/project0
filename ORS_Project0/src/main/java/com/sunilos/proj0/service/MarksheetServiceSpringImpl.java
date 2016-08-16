package com.sunilos.proj0.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.proj0.dao.MarksheetDAOInt;
import com.sunilos.proj0.dto.MarksheetDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.exception.DuplicateRecordException;

@Service(value = "marksheetService")
public class MarksheetServiceSpringImpl implements MarksheetServiceInt {

	@Autowired
	private MarksheetDAOInt dao;

	public void setDao(MarksheetDAOInt dao) {
		this.dao = dao;
	}

	private static Logger log = Logger
			.getLogger(MarksheetServiceSpringImpl.class);

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long add(MarksheetDTO dto) throws DuplicateRecordException {
		MarksheetDTO duplicateMarksheet = dao.findByRollNo(dto.getRollNo());
		if (duplicateMarksheet != null) {
			throw new DuplicateRecordException("RollNo is already Exist");
		}
		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(MarksheetDTO dto) throws DuplicateRecordException {
		MarksheetDTO existDto = dao.findByRollNo(dto.getRollNo());
		if (existDto != null && existDto.getId() != dto.getId()) {
			throw new DuplicateRecordException("Record is already Exist");
		}
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		dao.delete(id);

	}

	@Transactional(readOnly = true)
	public MarksheetDTO findByRollNo(String rollNo) {

		return dao.findByRollNo(rollNo);
	}

	@Transactional(readOnly = true)
	public MarksheetDTO findByPK(long pk) {

		return dao.findByPK(pk);
	}

	@Transactional(readOnly = true)
	public List search(MarksheetDTO dto) {

		return dao.search(dto);
	}

	@Transactional(readOnly = true)
	public List search(MarksheetDTO dto, int pageNo, int pageSize) {

		return dao.search(dto, pageNo, pageSize);
	}

	@Transactional(readOnly = true)
	public List list() {

		return dao.list();
	}

	@Transactional(readOnly = true)
	public List list(int pageNo, int pageSize) {

		return dao.list(pageNo, pageSize);
	}

	@Transactional(readOnly = true)
	public List getMeritList(int pageNo, int pageSize) {

		return dao.getMeritList(pageNo, pageSize);
	}

}
