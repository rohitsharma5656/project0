package com.sunilos.proj0.service;

import java.util.List;

import com.sunilos.proj0.dto.UserDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.exception.DatabaseException;
import com.sunilos.proj0.exception.DuplicateRecordException;
import com.sunilos.proj0.exception.RecordNotFoundException;

/**
 * User Service interface.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface UserServiceInt {

	/**
	 * Add a user
	 * 
	 * @param dto
	 *            * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when user already exists
	 */
	public long add(UserDTO dto) throws ApplicationException,
			DuplicateRecordException;

	/**
	 * Update a User
	 * 
	 * @param dto
	 * @throws DuplicateRecordException
	 *             : if updated user record is already exist
	 */
	public void update(UserDTO dto) throws DuplicateRecordException;

	/**
	 * Delete a user
	 * 
	 * @param dto
	 */
	public void delete(long id);

	/**
	 * Find user by login
	 * 
	 * @param login
	 *            : get parameter
	 * @return dto
	 */
	public UserDTO findByLogin(String login);

	/**
	 * Find user by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 */
	public UserDTO findByPK(long pk);

	/**
	 * Search Users
	 * 
	 * @return list : List of Users
	 * @param dto
	 *            : Search Parameters
	 */
	public List search(UserDTO dto);

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
	 */
	public List search(UserDTO dto, int pageNo, int pageSize);

	/**
	 * Get List of Users
	 * 
	 * @return list : List of Users
	 */
	public List list();

	/**
	 * Get List of Users with pagination
	 * 
	 * @return list : List of Users
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 */
	public List list(int pageNo, int pageSize);

	/**
	 * Change Password By pk
	 * 
	 * @param pk
	 *            ,oldPassword,newPassword : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public boolean changePassword(Long id, String oldPassword,
			String newPassword) throws ApplicationException;

	/**
	 * User Authentication
	 * 
	 * @return dto : Contains User's information
	 * @param login
	 *            : User Login
	 * @param password
	 *            : User Password
	 */
	public UserDTO authenticate(UserDTO dto);

	/**
	 * Lock User for certain time duration
	 * 
	 * @return boolean : true if success otherwise false
	 * @param login
	 *            : User Login
	 */
	public boolean lock(String login);

	/**
	 * Get User Roles
	 * 
	 * @return List : User Role List
	 * @param dto
	 */
	public List getRoles(UserDTO dto);

	/**
	 * Update User access
	 * 
	 * @return dto
	 * @param dto
	 */
	public UserDTO updateAccess(UserDTO dto);

	/**
	 * Register a User
	 * 
	 * @param dto
	 * @return
	 * @throws ApplicationException
	 * @throws DuplicateRecordException 
	 */
	public long registerUser(UserDTO dto) throws ApplicationException, DuplicateRecordException;

	/**
	 * reset password
	 */
	public boolean resetPassword(UserDTO dto);

	/**
	 * forget password * @throws ApplicationException
	 */
	public boolean forgetPassword(String login) throws ApplicationException;
}
