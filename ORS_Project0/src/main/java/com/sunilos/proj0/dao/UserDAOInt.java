package com.sunilos.proj0.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.sunilos.proj0.dto.UserDTO;
import com.sunilos.proj0.exception.*;

/**
 * User DAO Interface provides abstract methods of CRUD operations.
 * Implementation will be done by JDBC, Hibrenate or JPA.
 * 
 * All methods propagate unchecked DataAccessException. It is a generic
 * exception handling provided by Spring-DAO.
 * 
 * If DataAccessException is propagated from a method then declarative
 * transaction is rolled back by Spring AOP.
 * 
 * @version 1.0
 * @since 1 Jan 2015
 * @author Sunil Sahu
 * @Copyright (c) Sunil Sahu
 * @url www.sunilbooks.com
 */

public interface UserDAOInt {
	/**
	 * Add a user
	 * 
	 * @param dto
	 * @throws DataAccessException
		 */
	public long add(UserDTO dto) throws DataAccessException;

	/**
	 * Update a User
	 * 
	 * @param dto
	 * @throws DataAccessException
		 */
	public void update(UserDTO dto) throws DataAccessException;

	/**
	 * Delete a user
	 * 
	 * @param dto
	 * @throws DataAccessException
	 */
	public void delete(long id) throws DataAccessException;

	/**
	 * Find user by login
	 * 
	 * @param login
	 *            : get parameter
	 * @return dto
	 * @throws DataAccessException
	 */
	public UserDTO findByLogin(String login) throws DataAccessException;

	/**
	 * Find user by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DataAccessException
	 */
	public UserDTO findByPK(long pk) throws DataAccessException;

	/**
	 * Search Users
	 * 
	 * @return list : List of Users
	 * @param dto
	 *            : Search Parameters
	 * @throws DataAccessException
	 */
	public List search(UserDTO dto) throws DataAccessException;

	/**
	 * Search Users with pagination
	 * 
	 * @return list : List of Users
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DataAccessException
	 */
	public List search(UserDTO dto, int pageNo, int pageSize)
			throws DataAccessException;

	/**
	 * Get List of Users
	 * 
	 * @return list : List of Users
	 * @throws DataAccessException
		 */
	public List list() throws DataAccessException;

	/**
	 * Get List of Users with pagination
	 * 
	 * @return list : List of Users
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DataAccessException
	 */
	public List list(int pageNo, int pageSize) throws DataAccessException;

	/**
	 * User Authentication
	 * 
	 * @return dto : Contains User's information
	 * @param login
	 *            : User Login
	 * @param password
	 *            : User Password
	 * @throws DataAccessException
	 */
	public UserDTO authenticate(String login, String password)
			throws DataAccessException;

	/**
	 * Lock User for certain time duration
	 * 
	 * @return boolean : true if success otherwise false
	 * @param login
	 *            : User Login
	 * @throws DataAccessException
		 */
	public boolean lock(String login) throws DataAccessException;

	/**
	 * Get User Roles
	 * 
	 * @return List : User Role List
	 * @param dto
	 * @throws DataAccessException
	 */
	public List getRoles(UserDTO dto) throws DataAccessException;

	/**
	 * Update User access
	 * 
	 * @return dto
	 * @param dto
	 * @throws DataAccessException
	 */
	public UserDTO updateAccess(UserDTO dto) throws DataAccessException;

}
