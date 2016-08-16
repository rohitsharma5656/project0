package com.sunilos.proj0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.sunilos.proj0.dto.CourseDTO;

@Repository("courseDAO")
public class CourseDAOHibImpl implements CourseDAOInt {

	private static Logger log = Logger.getLogger(CourseDAOHibImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public long add(CourseDTO dto) throws DataAccessException {
		log.debug("Course Dao Add Started");
		long pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("Course Dao Add End");
		return pk;
	}

	public void update(CourseDTO dto) throws DataAccessException {
		log.debug("course Dao update Started");
		sessionFactory.getCurrentSession().update(dto);
		log.debug("course Dao update End");

	}

	public void delete(long id) throws DataAccessException {
		log.debug("Course Dao delete started");
		CourseDTO dto = new CourseDTO();
		dto.setId(id);
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("Course Dao delete End");

	}

	public CourseDTO findByName(String name)
			throws DataAccessException {
		log.debug("Course Dao findByName Started");
		CourseDTO dto = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				CourseDTO.class);
		criteria.add(Restrictions.eq("courseName", name));

		List list = criteria.list();

		if (list.size() == 1) {
			dto = (CourseDTO) list.get(0);
		}

		log.debug("Course Dao findByName End");
		return dto;
	}

	public CourseDTO findByPK(long pk) throws DataAccessException {
		log.debug("Course Dao findBypk Started");
		Session session = sessionFactory.getCurrentSession();
		CourseDTO dto = (CourseDTO) session.get(CourseDTO.class, pk);
		log.debug("Course Dao findBypk End");
		return dto;
	}

	public List search(CourseDTO dto, int pageNo, int pageSize)
			throws DataAccessException {
		log.debug("Course Dao search Started");
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				CourseDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getCourseName() != null && dto.getCourseName().length() > 0) {
				criteria.add(Restrictions.like("courseName",
						dto.getCourseName() + "%"));
			}
		
		}// if page size is greater than zero the apply pagination
		if (pageSize > 0) {
			criteria.setFirstResult(((pageNo - 1) * pageSize));
			criteria.setMaxResults(pageSize);
		}

		log.debug("Course Dao search End");
		return criteria.list();
	}

	public List search(CourseDTO dto) throws DataAccessException {

		return search(dto, 0, 0);
	}

	public List list() throws DataAccessException {

		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws DataAccessException {
		log.debug("Course Dao list Started");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				CourseDTO.class);
		if (pageSize > 0) {
			pageNo = ((pageNo - 1) * pageSize) + 1;
			criteria.setFirstResult(pageNo);
			criteria.setMaxResults(pageSize);
		}
		List list = criteria.list();
		log.debug("Course Dao list End");
		return list;
	}


}
