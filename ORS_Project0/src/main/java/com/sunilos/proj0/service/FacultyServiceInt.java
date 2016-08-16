package com.sunilos.proj0.service;

import java.util.List;

import com.sunilos.proj0.dto.FacultyDTO;
import com.sunilos.proj0.exception.DuplicateRecordException;

/**
 * Faculty Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public interface FacultyServiceInt {

	/**
	 * Add a Faculty
	 * 
	 * @param dto
	 * @throws DuplicateRecordException
	 *             : if updated user record is already exist
	 */

	public long add(FacultyDTO dto) throws DuplicateRecordException;

	/**
	 * Update a Faculty
	 * 
	 * @param dto
	 * @throws DuplicateRecordException
	 *             : if updated user record is already exist
	 */
	public void update(FacultyDTO dto) throws DuplicateRecordException;

	/**
	 * Delete a Faculty
	 * 
	 * @param dto
	 */
	public void delete(long id);

	/**
	 * Find Faculty by UserId
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 */
	public FacultyDTO findByUserId(Long userId);

	/**
	 * Find Faculty by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 */
	public FacultyDTO findByPK(long pk);

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
	 */
	public List search(FacultyDTO dto, int pageNo, int pageSize);

	/**
	 * Search Faculty
	 * 
	 * @return list : List of Faculty
	 * @param dto
	 *            : Search Parameters
	 */
	public List search(FacultyDTO dto);

	/**
	 * Gets List of Faculty
	 * 
	 * @return list : List of Faculty
	 */
	public List list();

	/**
	 * get List of Faculty with pagination
	 * 
	 * @return list : List of Faculty
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 */
	public List list(int pageNo, int pageSize);

}
