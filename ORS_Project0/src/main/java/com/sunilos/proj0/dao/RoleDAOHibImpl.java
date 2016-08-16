package com.sunilos.proj0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;
import com.sunilos.proj0.dto.CollegeDTO;
import com.sunilos.proj0.dto.RoleDTO;

import com.sunilos.proj0.exception.DuplicateRecordException;

/**
 * Hibernate implementation of Role DAO.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

@Repository("roleDAO")
public class RoleDAOHibImpl implements RoleDAOInt {

	private static Logger log = Logger.getLogger(RoleDAOHibImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public long add(RoleDTO dto) throws DataAccessException {
		log.debug("Role Dao Add Started");
		long pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("Role Dao Add End");
		return pk;

	}

	public void update(RoleDTO dto) throws DataAccessException {
		log.debug("Role Dao update Started");

		sessionFactory.getCurrentSession().update(dto);
		log.debug("Role Dao update End");
	}

	public void delete(long id) throws DataAccessException {
		log.debug("Role Dao Delete Started");
		sessionFactory.getCurrentSession().delete(id);
		log.debug("Role Dao Delete End");

	}

	public RoleDTO findByName(String name) throws DataAccessException {
		log.debug("Role DAO Find by Name Started");
		RoleDTO dto = null;
		List list = sessionFactory.getCurrentSession()
				.createCriteria(RoleDTO.class)
				.add(Restrictions.eq("roleName", name)).list();
		if (list.size() == 1) {
			dto = (RoleDTO) list.get(0);
		}
		log.debug("Role DAO Find by Name Ended");
		return dto;

	}

	public RoleDTO findByPK(long pk) throws DataAccessException {
		log.debug("RoleDAO Find by PK Started");
		RoleDTO dto = null;
		dto = (RoleDTO) sessionFactory.openSession().get(RoleDTO.class, pk);
		log.debug("RoleDAO Find by PK Ended");
		return dto;

	}

	public List search(RoleDTO dto, int pageNo, int pageSize)
			throws DataAccessException {
		log.debug("Role Dao search Started");
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RoleDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getRoleName() != null && dto.getRoleName().length() > 0) {
				criteria.add(Restrictions.like("roleName", dto.getRoleName()
						+ "%"));
			}
			if (dto.getRoleDescription() != null
					&& dto.getRoleDescription().length() > 0) {
				criteria.add(Restrictions.like("description",
						dto.getRoleDescription() + "%"));
			}
			// if page size is greater than zero the apply pagination
			if (pageSize > 0) {
				criteria.setFirstResult(((pageNo - 1) * pageSize));
				criteria.setMaxResults(pageSize);
			}

		}
		log.debug("Role Dao search End");
		return criteria.list();
	}

	public List search(RoleDTO dto) throws DataAccessException {

		return search(dto, 0, 0);
	}

	public List list() throws DataAccessException {

		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws DataAccessException {
		log.debug("Role Dao list Started");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RoleDTO.class);
		if (pageSize > 0) {
			pageNo = ((pageNo - 1) * pageSize) + 1;
			criteria.setFirstResult(pageNo);
			criteria.setMaxResults(pageSize);
		}
		List list = criteria.list();

		log.debug("Role Dao list End");
		return list;
	}

}
