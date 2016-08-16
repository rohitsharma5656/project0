package com.sunilos.proj0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;
import com.sunilos.proj0.dto.CollegeDTO;
import com.sunilos.proj0.dto.RoleDTO;
import com.sunilos.proj0.dto.StudentDTO;

import com.sunilos.proj0.exception.DuplicateRecordException;

/**
 * Hibernate implementation of Student DAO.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */
@Repository("studentDAO")
public class StudentDAOHibImpl implements StudentDAOInt {

	private static Logger log = Logger.getLogger(StudentDAOHibImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public long add(StudentDTO dto) throws DataAccessException {
		log.debug("Student Dao Add started");
		long pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("Student Dao Add End");
		return pk;

	}

	public void update(StudentDTO dto) throws DataAccessException {
		log.debug("Student Dao update Started");
		sessionFactory.getCurrentSession().update(dto);
		log.debug("Student Dao update End");

	}

	public void delete(long id) throws DataAccessException {
		log.debug("Student Dao delete started");
		StudentDTO dto = new StudentDTO();
		dto.setId(id);
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("Student Dao delete End");

	}

	public StudentDTO findByEmailId(String emailId) throws DataAccessException {
		log.debug("Student Dao findByEmailId Started");
		StudentDTO dto = null;
		List list = sessionFactory.getCurrentSession()
				.createCriteria(StudentDTO.class)
				.add(Restrictions.eq("email", emailId)).list();
		if (list.size() == 1) {
			dto = (StudentDTO) list.get(0);

		}
		log.debug("Student Dao findByEmailId End");
		return dto;

	}

	public StudentDTO findByPK(long pk) throws DataAccessException {
		log.debug("College Dao findBypk Started");
		Session session = sessionFactory.getCurrentSession();
		StudentDTO dto = (StudentDTO) session.get(StudentDTO.class, pk);
		session.evict(dto);
		log.debug("College Dao findBypk End");
		return dto;
	}

	public List search(StudentDTO dto, int pageNo, int pageSize)
			throws DataAccessException {
		log.debug("Student Dao search Started");
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				StudentDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				criteria.add(Restrictions.like("firstName", dto.getFirstName()
						+ "%"));
			}
			if (dto.getLastName() != null && dto.getLastName().length() > 0) {
				criteria.add(Restrictions.like("lastName", dto.getLastName()
						+ "%"));
			}
			if (dto.getDob() != null && dto.getDob().getDate() > 0) {
				criteria.add(Restrictions.eq("dob", dto.getDob()));
			}
			if (dto.getEmail() != null && dto.getEmail().length() > 0) {
				criteria.add(Restrictions.like("email", dto.getEmail() + "%"));
			}
			if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
				criteria.add(Restrictions.like("mobileNo", dto.getMobileNo()
						+ "%"));
			}

		}// if page size is greater than zero the apply pagination
		if (pageSize > 0) {
			criteria.setFirstResult(((pageNo - 1) * pageSize));
			criteria.setMaxResults(pageSize);
		}

		log.debug("Student Dao search End");
		return criteria.list();
	}

	public List search(StudentDTO dto) throws DataAccessException {

		return search(dto, 0, 0);
	}

	public List list() throws DataAccessException {

		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws DataAccessException {
		log.debug("Student Dao list Started");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				StudentDTO.class);
		if (pageSize > 0) {
			pageNo = ((pageNo - 1) * pageSize) + 1;
			criteria.setFirstResult(pageNo);
			criteria.setMaxResults(pageSize);
		}
		List list = criteria.list();
		log.debug("Student Dao list End");
		return list;

	}

}
