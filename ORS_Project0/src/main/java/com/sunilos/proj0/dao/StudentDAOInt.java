package com.sunilos.proj0.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.sunilos.proj0.dto.StudentDTO;
import com.sunilos.proj0.exception.*;

/**
 * 
 * Student DAO interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface StudentDAOInt {
	/**
	 * Add a Student
	 * 
	 * @param dto
	 * @throws DataAccessException
		 */
	public long add(StudentDTO dto) throws DataAccessException;

	/**
	 * Update a Student
	 * 
	 * @param dto
	 * @throws DataAccessException
		 */
	public void update(StudentDTO dto) throws DataAccessException;

	/**
	 * Delete a Student
	 * 
	 * @param dto
	 * @throws DataAccessException
	 */
	public void delete(long id) throws DataAccessException;

	/**
	 * Find Student by EmailId
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws DataAccessException
	 */
	public StudentDTO findByEmailId(String emailId) throws DataAccessException;

	/**
	 * Find Student by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DataAccessException
	 */
	public StudentDTO findByPK(long pk) throws DataAccessException;

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
	 * @throws DataAccessException
	 */
	public List search(StudentDTO dto, int pageNo, int pageSize)
			throws DataAccessException;

	/**
	 * Search Student
	 * 
	 * @return list : List of Student
	 * @param dto
	 *            : Search Parameters
	 * @throws DataAccessException
	 */
	public List search(StudentDTO dto) throws DataAccessException;

	/**
	 * Gets List of College
	 * 
	 * @return list : List of College
	 * @throws DataAccessException
	
	 */
	public List list() throws DataAccessException;

	/**
	 * get List of College with pagination
	 * 
	 * @return list : List of College
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DataAccessException
	 */
	public List list(int pageNo, int pageSize) throws DataAccessException;

}
