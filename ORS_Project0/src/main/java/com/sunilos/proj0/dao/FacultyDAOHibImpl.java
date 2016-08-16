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

import com.sunilos.proj0.dto.CollegeDTO;
import com.sunilos.proj0.dto.FacultyDTO;
import com.sunilos.proj0.dto.UserDTO;

@Repository("facultyDAO")
public class FacultyDAOHibImpl implements FacultyDAOInt {

	private static Logger log = Logger.getLogger(FacultyDAOHibImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public long add(FacultyDTO dto) throws DataAccessException {
		log.debug("Faculty Dao Add started");
		long pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("Faculty Dao Add End");
		return pk;

	}

	public void update(FacultyDTO dto) throws DataAccessException {
		log.debug("Faculty Dao Add started");
		sessionFactory.getCurrentSession().update(dto);
		log.debug("Faculty Dao update started");
	}

	public void delete(long id) throws DataAccessException {
		log.debug("Faculty Dao delete started");
		FacultyDTO dto = new FacultyDTO();
		dto.setId(id);
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("Faculty Dao delete End");

	}

	public FacultyDTO findByUserId(Long uId) throws DataAccessException {
		log.debug("Faculty Dao findByUserId Started");
		FacultyDTO dto = null;
		List list = sessionFactory.getCurrentSession()
				.createCriteria(FacultyDTO.class)
				.add(Restrictions.eq("userId", uId)).list();
		if (list.size() == 1) {
			dto = (FacultyDTO) list.get(0);
		}
		log.debug("Faculty Dao findByUserId End");
		return dto;

	}

	public FacultyDTO findByPK(long pk) throws DataAccessException {
		log.debug("Faculty Dao findBypk Started");
		Session session = sessionFactory.getCurrentSession();
		FacultyDTO dto = (FacultyDTO) session.get(FacultyDTO.class, pk);
		session.evict(dto);
		log.debug("Faculty Dao findBypk End");
		return dto;

	}

	public List search(FacultyDTO dto, int pageNo, int pageSize)
			throws DataAccessException {
		log.debug("Faculty Dao search Started");
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				FacultyDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getFacultyName() != null
					&& dto.getFacultyName().length() > 0) {
				criteria.add(Restrictions.like("facultyName",
						dto.getFacultyName() + "%"));
			}
			if (dto.getCollegeName() != null
					&& dto.getCollegeName().length() > 0) {
				criteria.add(Restrictions.like("collegeName",
						dto.getCollegeName() + "%"));
			}

		} // if page size is greater than zero the apply pagination
		if (pageSize > 0) {
			criteria.setFirstResult(((pageNo - 1) * pageSize));
			criteria.setMaxResults(pageSize);
		}
		log.debug("Faculty Dao search End");
		return criteria.list();
	}

	public List search(FacultyDTO dto) throws DataAccessException {

		return search(dto, 0, 0);
	}

	public List list() throws DataAccessException {

		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws DataAccessException {
		log.debug("Faculty Dao list Started");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				FacultyDTO.class);
		if (pageSize > 0) {
			pageNo = ((pageNo - 1) * pageSize) + 1;
			criteria.setFirstResult(pageNo);
			criteria.setMaxResults(pageSize);
		}
		List list = criteria.list();
		log.debug("Faculty Dao list End");
		return list;

	}

}
