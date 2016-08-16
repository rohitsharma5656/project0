package com.sunilos.proj0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.sunilos.proj0.exception.DatabaseException;

import com.sunilos.proj0.dto.UserDTO;

/**
 * User Data Access Object provides Database CRUD operations. It is implemented
 * by plain Hibernate 3 API with Spring ORM.
 * 
 * All methods propagate unchecked DataAccessException. It is a generic
 * exception handling provided by Spring-DAO.
 * 
 * If DataAccessException is propagated from method then declarative transaction
 * is rolled back by Spring AOP.
 * 
 * This is plain Hibernate 3 API implementation of DAO
 * 
 * @version 1.0
 * @since 1 Jan 2015
 * @author Sunil Sahu
 * @Copyright (c) Sunil Sahu
 * @url www.sunilbooks.com
 */
@Repository(value = "userDao")
public class UserDAOHibImpl implements UserDAOInt {

	@Autowired
	private SessionFactory sessionFactory = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private static Logger log = Logger.getLogger(UserDAOHibImpl.class);

	public long add(UserDTO dto) throws DataAccessException {
		log.debug("User Dao Add Started");

		long pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("User Dao Add End");
		return pk;
	}

	public void update(UserDTO dto) throws DataAccessException {
		log.debug("User Dao Update Started");
		sessionFactory.getCurrentSession().update(dto);

		log.debug("User Dao Update End");

	}

	public void delete(long id) throws DataAccessException {
		log.debug("User Dao delete Started");
		UserDTO dto = findByPK(id);
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("User Dao delete End");

	}

	public UserDTO findByLogin(String login) throws DataAccessException {
		log.debug("User Dao findByLogin Started");
		UserDTO dto = null;
		List list = (List) sessionFactory.getCurrentSession()
				.createCriteria(UserDTO.class)
				.add(Restrictions.eq("login", login)).list();
		System.out.println(list.size());
		if (list.size() == 1) {
			dto = (UserDTO) list.get(0);
		}
		log.debug("User Dao findByLogin Started");

		return dto;
	}

	public UserDTO findByPK(long pk) throws DataAccessException {
		log.debug("User Dao findByPk Started");
		UserDTO dto = null;
		dto = (UserDTO) sessionFactory.getCurrentSession().get(UserDTO.class,
				pk);
		log.debug("User Dao findByPk Started");
		return dto;
	}

	public List search(UserDTO dto) throws DataAccessException {

		return search(dto, 0, 0);
	}

	public List search(UserDTO dto, int pageNo, int pageSize)
			throws DataAccessException {
		log.debug("DAO search Started");
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				UserDTO.class);

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
			if (dto.getLogin() != null && dto.getLogin().length() > 0) {
				criteria.add(Restrictions.like("login", dto.getLogin() + "%"));
			}
			if (dto.getPassword() != null && dto.getPassword().length() > 0) {
				criteria.add(Restrictions.like("password", dto.getPassword()
						+ "%"));
			}
			if (dto.getGender() != null && dto.getGender().length() > 0) {
				criteria.add(Restrictions.eq("gender", dto.getLastName()));
			}
			if (dto.getDob() != null && dto.getDob().getDate() > 0) {
				criteria.add(Restrictions.eq("dob", dto.getDob()));
			}
			if (dto.getLastLogin() != null && dto.getLastLogin().getTime() > 0) {
				criteria.add(Restrictions.eq("lastLogin", dto.getLastLogin()));
			}
			if (dto.getRoleId() != null && dto.getRoleId() > 0) {
				criteria.add(Restrictions.eq("roleId", dto.getRoleId()));
			}
			if (dto.getUnSuccessfulLogin() > 0) {
				criteria.add(Restrictions.eq("unSuccessfulLogin",
						dto.getUnSuccessfulLogin()));
			}
			if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
				criteria.add(Restrictions.eq("mobileNo", dto.getMobileNo()));
			}

		}

		// if page size is greater than zero the apply pagination
		if (pageSize > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}

		list = criteria.list();

		log.debug("User Dao search End ");
		return list;

	}

	public List list() throws DataAccessException {

		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws DataAccessException {
		log.debug("User Dao list Started");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				UserDTO.class);
		criteria.add(Restrictions.eq("roleId", 5L));
		if (pageSize > 0) {
			pageNo = ((pageNo - 1) * pageSize) + 1;
			criteria.setFirstResult(pageNo);
			criteria.setMaxResults(pageSize);
		}
		List list = criteria.list();
		log.debug("User Dao list End");
		return list;
	}

	/**
	 * @param login
	 *            : String login
	 * @param password
	 *            : password
	 * @throws DatabaseException
	 */

	public UserDTO authenticate(String login, String password)
			throws DataAccessException {
		log.debug("user Dao authenticate method started");
		UserDTO dto = null;
		Query q = sessionFactory.getCurrentSession().createQuery(
				"from UserDTO where login=? and password=?");

		q.setString(0, login);
		q.setString(1, password);

		List list = q.list();

		if (list.size() > 0) {
			dto = (UserDTO) list.get(0);
		}
		log.debug("user Dao authenticate method End");
		return dto;
	}

	public boolean lock(String login) throws DataAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	public List getRoles(UserDTO dto) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public UserDTO updateAccess(UserDTO dto) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
