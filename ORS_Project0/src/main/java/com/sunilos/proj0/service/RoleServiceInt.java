package com.sunilos.proj0.service;

import java.util.List;

import com.sunilos.proj0.dto.RoleDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.exception.DatabaseException;
import com.sunilos.proj0.exception.DuplicateRecordException;

/**
 * 
 * Role Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface RoleServiceInt {

	/**
	 * Add a Role
	 * 
	 * @param dto
	 * @throws DuplicateRecordException
	 *             : throws when Role already exists
	 */
	public long add(RoleDTO dto) throws DuplicateRecordException;

	/**
	 * Update a Role
	 * 
	 * @param dto
	 * @throws DuplicateRecordException
	 *             : if updated user record is already exist
	 */
	public void update(RoleDTO dto) throws DuplicateRecordException;

	/**
	 * Delete a Role
	 * 
	 * @param dto
	 */
	public void delete(long id);

	/**
	 * Find Role by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 */
	public RoleDTO findByName(String name);

	/**
	 * Find Role by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 */
	public RoleDTO findByPK(long pk);

	/**
	 * Search Role with pagination
	 * 
	 * @return list : List of Role
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 */
	public List search(RoleDTO dto, int pageNo, int pageSize);

	/**
	 * Search Role
	 * 
	 * @return list : List of Role
	 * @param dto
	 *            : Search Parameters
	 */
	public List search(RoleDTO dto);

	/**
	 * Gets List of Role
	 * 
	 * @return list : List of Role
	 */
	public List list();

	/**
	 * get List of Role with pagination
	 * 
	 * @return list : List of Role
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 */
	public List list(int pageNo, int pageSize);

}
