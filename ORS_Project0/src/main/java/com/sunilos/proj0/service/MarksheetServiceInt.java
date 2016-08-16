package com.sunilos.proj0.service;

import java.util.List;

import com.sunilos.proj0.dto.MarksheetDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.exception.DatabaseException;
import com.sunilos.proj0.exception.DuplicateRecordException;

public interface MarksheetServiceInt {

	/**
	 * Marksheet Service interface.
	 * 
	 * @author SunilOS
	 * @version 1.0
	 * @Copyright (c) SunilOS
	 */

	public long add(MarksheetDTO dto) throws DuplicateRecordException;

	/**
	 * Deletes a Marksheet
	 * 
	 * @param dto
	 */
	public void delete(long id);

	/**
	 * Finds Marksheet by Roll No
	 * 
	 * @param rollNo
	 *            : get parameter
	 * @return dto
	 */
	public MarksheetDTO findByRollNo(String rollNo);

	/**
	 * Finds Marksheet by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 */

	public MarksheetDTO findByPK(long pk);

	/**
	 * Updates a Marksheet
	 * 
	 * @param dto
	 * @throws DuplicateRecordException
	 */
	public void update(MarksheetDTO dto) throws DuplicateRecordException;

	/**
	 * Searches Marksheet
	 * 
	 * @param dto
	 *            : Search Parameters
	 */
	public List search(MarksheetDTO dto);

	/**
	 * Searches Marksheet with pagination
	 * 
	 * @return list : List of Marksheets
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 */
	public List search(MarksheetDTO dto, int pageNo, int pageSize);

	/**
	 * Gets List of Marksheet
	 * 
	 * @return list : List of Marksheets
	 */
	public List list();

	/**
	 * get List of Marksheet with pagination
	 * 
	 * @return list : List of Marksheets
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 */
	public List list(int pageNo, int pageSize);

	/**
	 * get Merit List of Marksheet with pagination
	 * 
	 * @return list : List of Marksheets
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 */
	public List getMeritList(int pageNo, int pageSize);

}
