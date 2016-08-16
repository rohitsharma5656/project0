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
import com.sunilos.proj0.dto.CourseDTO;
import com.sunilos.proj0.dto.FacultyDTO;
import com.sunilos.proj0.dto.TimeTableDTO;

import com.sunilos.proj0.exception.DuplicateRecordException;

@Repository("timetableDAO")
public class TimeTableDAOHibImpl implements TimeTableDAOInt {

	private static Logger log = Logger.getLogger(TimeTableDAOHibImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public long add(TimeTableDTO dto) throws DataAccessException {
		log.debug("TimeTable Dao Add started");
		long pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("TimeTable Dao Add End");
		return pk;

	}

	public void update(TimeTableDTO dto) throws DataAccessException {
		log.debug("TimeTable Dao update Started");
		sessionFactory.getCurrentSession().update(dto);
		log.debug("TimeTable Dao update End");

	}

	public void delete(long id) throws DataAccessException {
		log.debug("TimeTable Dao delete started");
		TimeTableDTO dto = new TimeTableDTO();
		dto.setId(id);
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("TimeTable Dao delete End");

	}

	public TimeTableDTO findByName(Long fid, String name)
			throws DataAccessException {
		log.debug("TimeTable Dao findByName Started");
		TimeTableDTO dto = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				TimeTableDTO.class);
		criteria.add(Restrictions.eq("userId", fid));
		criteria.add(Restrictions.eq("time", name));

		List list = criteria.list();

		if (list.size() == 1) {
			dto = (TimeTableDTO) list.get(0);
		}

		log.debug("TimeTable Dao findByName End");
		return dto;
	}

	public TimeTableDTO findByPK(long pk) throws DataAccessException {
		log.debug("TimeTable Dao findBypk Started");
		Session session = sessionFactory.getCurrentSession();
		TimeTableDTO dto = (TimeTableDTO) session.get(TimeTableDTO.class, pk);
		session.evict(dto);
		log.debug("TimeTable Dao findBypk End");
		return dto;

	}

	public List search(TimeTableDTO dto, int pageNo, int pageSize)
			throws DataAccessException {
		log.debug("TimeTable Dao search Started");
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				TimeTableDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getFacultyName() != null
					&& dto.getFacultyName().length() > 0) {
				criteria.add(Restrictions.like("facultyName",
						dto.getFacultyName() + "%"));
			}
			if (dto.getCourseName() != null && dto.getCourseName().length() > 0) {
				criteria.add(Restrictions.like("courseName",
						dto.getCourseName() + "%"));
			}
			if (dto.getSubjectName() != null
					&& dto.getSubjectName().length() > 0) {
				criteria.add(Restrictions.like("subjectName", dto.getSubjectName()
						+ "%"));
			}
			// if page size is greater than zero the apply pagination
			if (pageSize > 0) {
				criteria.setFirstResult(((pageNo - 1) * pageSize));
				criteria.setMaxResults(pageSize);
			}
		}
		log.debug("TimeTable Dao search End");
		return criteria.list();
	}

	public List search(TimeTableDTO dto) throws DataAccessException {

		return search(dto, 0, 0);
	}

	public List list() throws DataAccessException {

		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws DataAccessException {
		log.debug("TimeTable Dao list Started");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				TimeTableDTO.class);
		if (pageSize > 0) {
			pageNo = ((pageNo - 1) * pageSize) + 1;
			criteria.setFirstResult(pageNo);
			criteria.setMaxResults(pageSize);
		}
		List list = criteria.list();
		log.debug("TimeTable Dao list End");
		return list;

	}

}
