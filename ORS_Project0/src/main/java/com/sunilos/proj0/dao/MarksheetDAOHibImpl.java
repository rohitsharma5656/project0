package com.sunilos.proj0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunilos.proj0.dto.CollegeDTO;
import com.sunilos.proj0.dto.MarksheetDTO;
import org.springframework.dao.DataAccessException;
import com.sunilos.proj0.exception.DuplicateRecordException;

/**
 * Hibernate implementation of College DAO.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */
@Repository("marksheetDAO")
public class MarksheetDAOHibImpl implements MarksheetDAOInt {

	private static Logger log = Logger.getLogger(MarksheetDAOHibImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public long add(MarksheetDTO dto) throws DataAccessException {
		log.debug("Marksheet Dao Add started");
		long pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("Marksheet Dao Add End");
		return pk;

	}

	public void delete(long id) throws DataAccessException {
		log.debug("College Dao delete started");
		MarksheetDTO dto = new MarksheetDTO();
		dto.setId(id);
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("College Dao delete End");

	}

	public MarksheetDTO findByRollNo(String rollNo) throws DataAccessException {
		log.debug("Marksheet Dao findByRollNo Started");
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(MarksheetDTO.class);
		criteria.add(Restrictions.eq("rollNo", rollNo));
		List<MarksheetDTO> list = criteria.list();

		MarksheetDTO dto = null;

		if (list.size() == 1) {
			dto = (MarksheetDTO) list.get(0);
			session.evict(dto);
		}
		log.debug("Marksheet Dao findByRollNo End");
		return dto;

	}

	public MarksheetDTO findByPK(long pk) throws DataAccessException {
		log.debug("Marksheet Dao findBypk Started");

		Session session = sessionFactory.getCurrentSession();
		MarksheetDTO dto = (MarksheetDTO) session.get(MarksheetDTO.class, pk);
		 session.evict(dto);
		log.debug("Marksheet Dao findBypk End");
		return dto;
	}

	public void update(MarksheetDTO dto) throws DataAccessException {
		log.debug("Marksheet Dao update started");

		/*
		 * // get Student Name StudentModelInt sModel =
		 * ModelFactory.getInstance().getStudentModel(); StudentDTO studentDTO =
		 * sModel.findByPK(dto.getStudentId());
		 * dto.setName(studentDTO.getFirstName() + " " +
		 * studentDTO.getLastName());
		 */

		sessionFactory.getCurrentSession().update(dto);
		log.debug("Marksheet Dao update started");

	}

	public List search(MarksheetDTO dto) throws DataAccessException {

		return search(dto, 0, 0);
	}

	public List search(MarksheetDTO dto, int pageNo, int pageSize)
			throws DataAccessException {
		log.debug("Marksheet Dao search Started");
		List list = null;

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				MarksheetDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getRollNo() != null && dto.getRollNo().length() > 0) {
				criteria.add(Restrictions.like("rollNo", dto.getRollNo()));
			}
			if (dto.getName() != null && dto.getName().length() > 0) {
				criteria.add(Restrictions.like("name", dto.getName() + "%"));
			}
			if (dto.getPhysics() != null && dto.getPhysics() > 0) {
				criteria.add(Restrictions.eq("physics", dto.getPhysics()));
			}
			if (dto.getChemistry() != null && dto.getChemistry() > 0) {
				criteria.add(Restrictions.eq("chemistry", dto.getChemistry()));
			}
			if (dto.getMaths() != null && dto.getMaths() > 0) {
				criteria.add(Restrictions.eq("maths", dto.getMaths()));
			}
			// if page size is greater than zero the apply pagination
			if (pageSize > 0) {
				criteria.setFirstResult(((pageNo - 1) * pageSize));
				criteria.setMaxResults(pageSize);
			}
		}

		log.debug("Marksheet Dao search End");
		return criteria.list();
	}

	public List list() throws DataAccessException {

		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws DataAccessException {
		log.debug("Marksheet Dao list Started");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				CollegeDTO.class);
		if (pageSize > 0) {
			pageNo = ((pageNo - 1) * pageSize) + 1;
			criteria.setFirstResult(pageNo);
			criteria.setMaxResults(pageSize);
		}
		List list = criteria.list();
		log.debug("Marksheet Dao list End");
		return list;

	}

	public List getMeritList(int pageNo, int pageSize)
			throws DataAccessException {
		log.debug("Marksheet Dao getMeritList Started");
		String sql = " from MarksheetDTO order by (physics + chemistry + maths) desc";
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery(sql);

		log.debug("Marksheet Dao getMeritList End");
		return q.list();
	}

}
