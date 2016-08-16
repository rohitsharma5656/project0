package com.sunilos.proj0.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.sunilos.proj0.dto.FacultyDTO;

/**
 * Faculty DAO interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface FacultyDAOInt {
	/**
	 * Add a Faculty
	 * 
	 * @param dto
	 * @throws DataAccessException
	 */
	public long add(FacultyDTO dto) throws DataAccessException;

	/**
	 * Update a Faculty
	 * 
	 * @param dto
	 * @throws DataAccessException
	 */
	public void update(FacultyDTO dto)throws DataAccessException;

	/**
	 * Delete a Faculty
	 * 
	 * @param dto
	 * @throws DataAccessException
	 */
	public void delete(long id) throws DataAccessException;

	/**
	 * Find Faculty by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws DataAccessException
	 */
	public FacultyDTO findByUserId(Long userId) throws DataAccessException;

	/**
	 * Find Faculty by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DataAccessException
	 */
	public FacultyDTO findByPK(long pk) throws DataAccessException;

	/**
	 * Search Faculty with pagination
	 * 
	 * @return list : List of Faculty
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DataAccessException
	 */
	public List search(FacultyDTO dto, int pageNo, int pageSize) throws DataAccessException;

	/**
	 * Search Faculty
	 * 
	 * @return list : List of Faculty
	 * @param dto
	 *            : Search Parameters
	 * @throws DataAccessException
	 */
	public List search(FacultyDTO dto) throws DataAccessException;

	/**
	 * Gets List of Faculty
	 * 
	 * @return list : List of Faculty
	 * @throws DataAccessException
	 */
	public List list() throws DataAccessException;

	/**
	 * get List of Faculty with pagination
	 * 
	 * @return list : List of Faculty
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DataAccessException
	 */
	public List list(int pageNo, int pageSize) throws DataAccessException;

}
