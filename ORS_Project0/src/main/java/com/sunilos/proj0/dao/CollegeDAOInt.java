package com.sunilos.proj0.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.sunilos.proj0.dto.CollegeDTO;
import com.sunilos.proj0.exception.*;

/**
 * College DAO interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface CollegeDAOInt {

	/**
	 * Add a College
	 * 
	 * @param dto
	   * @throws DataAccessException
	 */
	public long add(CollegeDTO dto) throws DataAccessException;

	/**
	 * Update a College
	 * 
	 * @param dto
 * @throws DataAccessException
	 */
	public void update(CollegeDTO dto) throws DataAccessException;

	/**
	 * Delete a College
	 * 
	 * @param dto
	 * @throws DataAccessException
		 */
	public void delete(long id) throws DataAccessException;

	/**
	 * Find College by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	  * @throws DataAccessException
	 */
	public CollegeDTO findByName(String name) throws DataAccessException;

	/**
	 * Find College by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DataAccessException
	 */
	public CollegeDTO findByPK(long pk) throws  DataAccessException;

	/**
	 * Search College with pagination
	 * 
	 * @return list : List of College
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DataAccessException
	 */
	public List search(CollegeDTO dto, int pageNo, int pageSize)
			throws DataAccessException;

	/**
	 * Search College
	 * 
	 * @return list : List of College
	 * @param dto
	 *            : Search Parameters
	  * @throws DataAccessException
	 */
	public List search(CollegeDTO dto) throws DataAccessException;

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
