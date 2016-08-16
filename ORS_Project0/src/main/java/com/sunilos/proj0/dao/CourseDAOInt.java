package com.sunilos.proj0.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.sunilos.proj0.dto.CourseDTO;

/**
 * Course DAO interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface CourseDAOInt {
	/**
	 * Add a Course
	 * 
	 * @param dto
	 * @throws DataAccessException
	 */
	public long add(CourseDTO dto) throws DataAccessException;

	/**
	 * Update a Course
	 * 
	 * @param dto
	 * @throws DataAccessException
	 */
	public void update(CourseDTO dto) throws DataAccessException;

	/**
	 * Delete a Course
	 * 
	 * @param dto
	 * @throws DataAccessException
	 */
	public void delete(long id) throws DataAccessException;

	/**
	 * Find Course by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws DataAccessException
	 */
	public CourseDTO findByName(String name)
			throws DataAccessException;

	/**
	 * Find Course by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DataAccessException
	 */
	public CourseDTO findByPK(long pk) throws DataAccessException;

	/**
	 * Search Course with pagination
	 * 
	 * @return list : List of Course
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DataAccessException
	 */
	public List search(CourseDTO dto, int pageNo, int pageSize)
			throws DataAccessException;

	/**
	 * Search Course
	 * 
	 * @return list : List of Course
	 * @param dto
	 *            : Search Parameters
	 * @throws DataAccessException
	 */
	public List search(CourseDTO dto) throws DataAccessException;

	/**
	 * Gets List of Course
	 * 
	 * @return list : List of Course
	 * @throws DataAccessException
	 */
	public List list() throws DataAccessException;

	/**
	 * get List of Course with pagination
	 * 
	 * @return list : List of Course
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DataAccessException
	 */
	public List list(int pageNo, int pageSize) throws DataAccessException;

}
