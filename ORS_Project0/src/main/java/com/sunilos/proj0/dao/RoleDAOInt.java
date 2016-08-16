package com.sunilos.proj0.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.sunilos.proj0.dto.RoleDTO;
import com.sunilos.proj0.exception.*;

/**
 * 
 * Role DAO interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface RoleDAOInt {

	/**
	 * Add a Role
	 * 
	 * @param dto
	 * @throws DataAccessException
		 */
	public long add(RoleDTO dto) throws DataAccessException;

	/**
	 * Update a Role
	 * 
	 * @param dto
	 * @throws DataAccessException
		 */
	public void update(RoleDTO dto) throws DataAccessException;

	/**
	 * Delete a Role
	 * 
	 * @param dto
	 * @throws DataAccessException
	 */
	public void delete(long id) throws DataAccessException;

	/**
	 * Find Role by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws DataAccessException
	 */
	public RoleDTO findByName(String name) throws DataAccessException;

	/**
	 * Find Role by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DataAccessException
	 */
	public RoleDTO findByPK(long pk) throws DataAccessException;

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
	 * @throws DataAccessException
	 */
	public List search(RoleDTO dto, int pageNo, int pageSize)
			throws DataAccessException;

	/**
	 * Search Role
	 * 
	 * @return list : List of Role
	 * @param dto
	 *            : Search Parameters
	 * @throws DataAccessException
	 */
	public List search(RoleDTO dto) throws DataAccessException;

	/**
	 * Gets List of Role
	 * 
	 * @return list : List of Role
	 * @throws DataAccessException
	
	 */
	public List list() throws DataAccessException;

	/**
	 * get List of Role with pagination
	 * 
	 * @return list : List of Role
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DataAccessException
	 */
	public List list(int pageNo, int pageSize) throws DataAccessException;

}
