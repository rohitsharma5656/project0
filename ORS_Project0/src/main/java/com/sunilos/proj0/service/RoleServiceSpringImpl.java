package com.sunilos.proj0.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.proj0.dao.RoleDAOInt;
import com.sunilos.proj0.dto.RoleDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.exception.DuplicateRecordException;

@Service(value = "roleService")
public class RoleServiceSpringImpl implements RoleServiceInt {

	@Autowired
	private RoleDAOInt dao;

	public void setDao(RoleDAOInt dao) {
		this.dao = dao;
	}

	private static Logger log = Logger.getLogger(RoleServiceSpringImpl.class);

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public long add(RoleDTO dto) throws DuplicateRecordException {
		RoleDTO existDto = dao.findByName(dto.getRoleName());
		if (existDto != null) {
			throw new DuplicateRecordException("Role is already exists");
		}
		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void update(RoleDTO dto) throws DuplicateRecordException {
		/*
		 * RoleDTO existDto = dao.findByName(dto.getRoleName()); if (existDto !=
		 * null && existDto.getId() != dto.getId()) { throw new
		 * DuplicateRecordException("Role is already exists"); }
		 */
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		dao.delete(id);

	}

	@Transactional(readOnly = true)
	public RoleDTO findByName(String name) {

		return dao.findByName(name);
	}

	@Transactional(readOnly = true)
	public RoleDTO findByPK(long pk) {

		return dao.findByPK(pk);
	}

	@Transactional(readOnly = true)
	public List search(RoleDTO dto, int pageNo, int pageSize) {

		return dao.search(dto, pageNo, pageSize);
	}

	@Transactional(readOnly = true)
	public List search(RoleDTO dto) {

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
