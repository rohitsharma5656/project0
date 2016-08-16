package com.sunilos.proj0.service;

import java.util.List;

import com.sunilos.proj0.dto.CourseDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.exception.DatabaseException;
import com.sunilos.proj0.exception.DuplicateRecordException;
import com.sunilos.proj0.exception.RecordNotFoundException;

public interface CourseServiceInt {
	/**
	 * Course Service interface.
	 * 
	 * @author SunilOS
	 * @version 1.0
	 * @Copyright (c) SunilOS
	 */

	public long add(CourseDTO dto) throws DuplicateRecordException;

	/**
	 * Update a Course
	 * 
	 * @param dto
	 * @throws DuplicateRecordException
	 *             : if updated user record is already exist
	 */
	public void update(CourseDTO dto) throws DuplicateRecordException;

	/**
	 * Delete a Course
	 * 
	 * @param dto
	 */
	public void delete(long id);

	/**
	 * Find Course by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 */
	public CourseDTO findByName(String name, String sub);

	/**
	 * Find Course by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 */
	public CourseDTO findByPK(long pk);

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
	 */
	public List search(CourseDTO dto, int pageNo, int pageSize);

	/**
	 * Search Course
	 * 
	 * @return list : List of Course
	 * @param dto
	 *            : Search Parameters
	 */
	public List search(CourseDTO dto);

	/**
	 * Gets List of Course
	 * 
	 * @return list : List of Course
	 */
	public List list();

	/**
	 * get List of Course with pagination
	 * 
	 * @return list : List of Course
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 */
	public List list(int pageNo, int pageSize);

}
