package com.sunilos.proj0.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.sunilos.proj0.dto.TimeTableDTO;
import com.sunilos.proj0.exception.*;

public interface TimeTableDAOInt {
	/**
	 * Add a Course
	 * 
	 * @param dto
	 * @throws DataAccessException
	 */
	public long add(TimeTableDTO dto) throws DataAccessException;

	/**
	 * Update a Course
	 * 
	 * @param dto
	 * @throws DataAccessException
	 */
	public void update(TimeTableDTO dto) throws DataAccessException
			;

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
	public TimeTableDTO findByName(Long fid, String name)
			throws DataAccessException;

	/**
	 * Find Course by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DataAccessException
	 */
	public TimeTableDTO findByPK(long pk) throws DataAccessException;

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
	public List search(TimeTableDTO dto, int pageNo, int pageSize)
			throws DataAccessException;

	/**
	 * Search Course
	 * 
	 * @return list : List of Course
	 * @param dto
	 *            : Search Parameters
	 * @throws DataAccessException
	 */
	public List search(TimeTableDTO dto) throws DataAccessException;

	/**
	 * Gets List of Course
	 * 
	 * @return list : List of Course
	 * @throws DatabaseException
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
