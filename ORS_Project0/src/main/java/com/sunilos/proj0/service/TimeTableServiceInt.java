package com.sunilos.proj0.service;

import java.util.List;

import com.sunilos.proj0.dto.TimeTableDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.exception.DatabaseException;
import com.sunilos.proj0.exception.DuplicateRecordException;

public interface TimeTableServiceInt {
	/**
	 * Add a Course
	 * 
	 * @param dto
	 * @throws DuplicateRecordException
	 *             : throws when Course already exists
	 */
	public long add(TimeTableDTO dto) throws DuplicateRecordException;

	/**
	 * Update a Course
	 * 
	 * @param dto
	 * @throws DuplicateRecordException
	 *             : if updated user record is already exist
	 */
	public void update(TimeTableDTO dto) throws DuplicateRecordException;

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
	public TimeTableDTO findByName(Long fid, String name);

	/**
	 * Find Course by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 */
	public TimeTableDTO findByPK(long pk);

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
	public List search(TimeTableDTO dto, int pageNo, int pageSize);

	/**
	 * Search Course
	 * 
	 * @return list : List of Course
	 * @param dto
	 *            : Search Parameters
	 */
	public List search(TimeTableDTO dto);

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
