package com.sunilos.proj0.service;

import java.util.List;

import com.sunilos.proj0.dto.StudentDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.exception.DatabaseException;
import com.sunilos.proj0.exception.DuplicateRecordException;

/**
 * 
 * Student Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface StudentServiceInt {

	/**
	 * Add a Student
	 * 
	 * @param dto
	 * @throws DuplicateRecordException
	 *             : throws when Student already exists
	 */
	public long add(StudentDTO dto) throws DuplicateRecordException;

	/**
	 * Update a Student
	 * 
	 * @param dto
	 * @throws DuplicateRecordException
	 *             : if updated user record is already exist
	 */
	public void update(StudentDTO dto) throws DuplicateRecordException;

	/**
	 * Delete a Student
	 * 
	 * @param dto
	 */
	public void delete(long id);

	/**
	 * Find Student by EmailId
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 */
	public StudentDTO findByEmailId(String emailId);

	/**
	 * Find Student by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 */
	public StudentDTO findByPK(long pk);

	/**
	 * Search Student with pagination
	 * 
	 * @return list : List of Student
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 */
	public List search(StudentDTO dto, int pageNo, int pageSize);

	/**
	 * Search Student
	 * 
	 * @return list : List of Student
	 * @param dto
	 *            : Search Parameters
	 */
	public List search(StudentDTO dto);

	/**
	 * Gets List of College
	 * 
	 * @return list : List of College
	 */
	public List list();

	/**
	 * get List of College with pagination
	 * 
	 * @return list : List of College
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 */
	public List list(int pageNo, int pageSize);

}
