package com.sunilos.proj0.service;

import java.util.List;

import com.sunilos.proj0.dto.CollegeDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.exception.DatabaseException;
import com.sunilos.proj0.exception.DuplicateRecordException;

public interface CollegeServiceInt {

	/**
	 * Add a College
	 * 
	 * @param dto
	 * @throws DuplicateRecordException
	 *             : throws when college already exists
	 */
	public long add(CollegeDTO dto) throws DuplicateRecordException;

	/**
	 * Update a College
	 * 
	 * @param dto
	 * @throws DuplicateRecordException
	 *             : if updated user record is already exist
	 */
	public void update(CollegeDTO dto) throws DuplicateRecordException;

	/**
	 * Delete a College
	 * 
	 * @param dto
	 */
	public void delete(long id);

	/**
	 * Find College by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 */
	public CollegeDTO findByName(String name);

	/**
	 * Find College by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 */
	public CollegeDTO findByPK(long pk);

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
	 */
	public List search(CollegeDTO dto, int pageNo, int pageSize);

	/**
	 * Search College
	 * 
	 * @return list : List of College
	 * @param dto
	 *            : Search Parameters
	 */
	public List search(CollegeDTO dto);

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
