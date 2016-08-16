package com.sunilos.proj0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.sunilos.proj0.dto.CollegeDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.exception.DuplicateRecordException;

/**
 * Hibernate implementation of College DAO.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Repository("collegeDAO")
public class CollegeDAOHibImpl implements CollegeDAOInt {

	private static Logger log = Logger.getLogger(CollegeDAOHibImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public long add(CollegeDTO dto) throws DataAccessException {
		log.debug("College Dao Add started");
		long pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("College Dao Add End");
		return pk;
	}

	public void update(CollegeDTO dto) throws DataAccessException {
		log.debug("College Dao update started");
		sessionFactory.getCurrentSession().update(dto);
		log.debug("College Dao update started");

	}

	public void delete(long id) throws DataAccessException {
		log.debug("College Dao delete started");
		CollegeDTO dto = new CollegeDTO();
		dto.setId(id);
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("College Dao delete End");
	}

	public CollegeDTO findByName(String name) throws DataAccessException {
		log.debug("College Dao findByName Started");
		CollegeDTO dto = null;
		List list = sessionFactory.getCurrentSession()
				.createCriteria(CollegeDTO.class)
				.add(Restrictions.eq("name", name)).list();
		if (list.size() == 1) {
			dto = (CollegeDTO) list.get(0);

		}
		log.debug("College Dao findByName End");
		return dto;
	}

	public CollegeDTO findByPK(long pk) throws DataAccessException {
		log.debug("College Dao findBypk Started");
		Session session = sessionFactory.getCurrentSession();
		CollegeDTO dto = (CollegeDTO) session.get(CollegeDTO.class, pk);
		session.evict(dto);
		log.debug("College Dao findBypk End");
		return dto;
	}

	public List search(CollegeDTO dto, int pageNo, int pageSize)
			throws DataAccessException {
		log.debug("College Dao search Started");
		List list = null;
		Criteria c = sessionFactory.getCurrentSession().createCriteria(
				CollegeDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				c.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getName() != null && dto.getName().length() > 0) {
				c.add(Restrictions.like("name", dto.getName() + "%"));
			}
			if (dto.getAddress() != null && dto.getAddress().length() > 0) {
				c.add(Restrictions.like("addrsss", dto.getAddress() + "%"));
			}
			if (dto.getState() != null && dto.getState().length() > 0) {
				c.add(Restrictions.like("state", dto.getState() + "%"));
			}
			if (dto.getCity() != null && dto.getCity().length() > 0) {
				c.add(Restrictions.like("city", dto.getCity() + "%"));
			}
			if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
				c.add(Restrictions.eq("phoneNo", dto.getMobileNo()));
			}
		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			c.setFirstResult((pageNo - 1) * pageSize);
			c.setMaxResults(pageSize);
		}
		log.debug("College Dao search End");
		return c.list();

	}

	public List search(CollegeDTO dto) throws DataAccessException {

		return search(dto, 0, 0);
	}

	public List list() throws DataAccessException {

		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws DataAccessException {
		log.debug("College Dao list Started");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				CollegeDTO.class);
		if (pageSize > 0) {
			pageNo = ((pageNo - 1) * pageSize) + 1;
			criteria.setFirstResult(pageNo);
			criteria.setMaxResults(pageSize);
		}
		List list = criteria.list();
		log.debug("College Dao list End");
		return list;
	}
}
