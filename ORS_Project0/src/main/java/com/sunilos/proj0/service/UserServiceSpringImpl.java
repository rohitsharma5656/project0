package com.sunilos.proj0.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.proj0.dao.UserDAOInt;
import com.sunilos.proj0.dto.UserDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.exception.DuplicateRecordException;
import com.sunilos.proj0.util.EmailBuilder;

@Service(value = "userService")
public class UserServiceSpringImpl implements UserServiceInt {

	@Autowired
	private UserDAOInt dao;

	@Autowired
	private JavaMailSenderImpl mailSender;

	public void setDao(UserDAOInt dao) {
		this.dao = dao;
	}

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	private static Logger log = Logger.getLogger(UserServiceSpringImpl.class);

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long add(UserDTO dto) throws DuplicateRecordException {
		UserDTO existDto = dao.findByLogin(dto.getLogin());
		if (existDto != null) {
			throw new DuplicateRecordException("Login Id already Exist");
		}
		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(UserDTO dto) throws DuplicateRecordException {
		log.debug("Service update Started");
		dao.update(dto);
		log.debug("Service update End");

	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		log.debug("Service Delete Started");
		dao.delete(id);
		log.debug("Service delete End");

	}

	@Transactional(readOnly = true)
	public UserDTO findByLogin(String login) {
		log.debug("Service findByLogin Started");
		UserDTO dto = dao.findByLogin(login);
		return dto;
	}

	@Transactional(readOnly = true)
	public UserDTO findByPK(long pk) {
		log.debug("Service findByPK Started");
		UserDTO dto = dao.findByPK(pk);
		return dto;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<UserDTO> search(UserDTO dto) {

		return dao.search(dto);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {

		return dao.search(dto, pageNo, pageSize);
	}

	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public List list() {

		return dao.list();
	}

	@SuppressWarnings("rawtypes")
	public List list(int pageNo, int pageSize) {

		return dao.list(pageNo, pageSize);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean changePassword(Long id, String oldPassword,String newPassword) 
	{
		boolean flag = false;
		UserDTO dtoExist = dao.findByPK(id);
		if (dtoExist != null && dtoExist.getPassword().equals(oldPassword)) {
			dtoExist.setPassword(newPassword);
			dao.update(dtoExist);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("login", dtoExist.getLogin());
			map.put("password", dtoExist.getPassword());
			map.put("firstName", dtoExist.getFirstName());
			map.put("lastName", dtoExist.getLastName());

			String message = com.sunilos.proj0.util.EmailBuilder
					.getChangePasswordMessage(map);

			MimeMessage msg = mailSender.createMimeMessage();

			// use the true flag to indicate you need a multipart message
			MimeMessageHelper helper;
			try {
				helper = new MimeMessageHelper(msg, true);
				helper.setTo(dtoExist.getLogin());
				helper.setSubject("password has been Changed successfully");
				// use the true flag to indicate the text included is HTML

				helper.setText(message, true);
			} catch (MessagingException e) {
				System.out.println("Mail sending failed");
				e.printStackTrace();
			}
			mailSender.send(msg);
			flag = true;
		} else {

		}
		log.debug("User service changePassword end");
		return flag;
	}

	@Transactional(readOnly = true)
	public UserDTO authenticate(UserDTO dto) {
		log.debug("User Service authenticate started");
		UserDTO existDto = dao.authenticate(dto.getLogin(), dto.getPassword());
		if (existDto == null) {
			/* throw new RecordNotFoundException("Login Id is Invalid "); */
		} else {
			existDto.setLastLogin(new Timestamp(new Date().getTime()));
			dao.update(existDto);
			log.debug("User Service authenticate End");

		}
		return existDto;
	}

	public boolean lock(String login) {
		// TODO Auto-generated method stub
		return false;
	}

	public List getRoles(UserDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserDTO updateAccess(UserDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long registerUser(UserDTO dto) throws DuplicateRecordException,
			ApplicationException {

		long id;
		id = add(dto);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", dto.getLogin());
		map.put("password", dto.getPassword());

		String message = EmailBuilder.getUserRegistrationMessage(map);

		MimeMessage msg = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(msg);
		try {
			helper.setTo(dto.getLogin());
			helper.setSubject("Registration is successful for ORS Project SUNRAYS Technologies.");
			// use the true flag to indicate the text included is HTML
			helper.setText(message, true);
			mailSender.send(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Error in Register User");
		}

		return id;
	}

	public boolean resetPassword(UserDTO dto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public boolean forgetPassword(String login) throws ApplicationException {
		log.debug("Service forgetPassword Started");

		UserDTO dtoExist = dao.findByLogin(login);

		if (dtoExist != null) {

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("firstName", dtoExist.getFirstName());
			map.put("lastName", dtoExist.getLastName());
			map.put("login", dtoExist.getLogin());
			map.put("password", dtoExist.getPassword());

			String message = EmailBuilder.getForgetPasswordMessage(map);

			MimeMessage msg = mailSender.createMimeMessage();

			try {
				MimeMessageHelper helper = new MimeMessageHelper(msg);
				helper.setTo(login);
				helper.setSubject("SunilOS ORS Password reset");
				// use the true flag to indicate the text included is HTML
				helper.setText(message, true);
				mailSender.send(msg);
			} catch (Exception e) {
				log.error(e);
				throw new ApplicationException("Error in ForgetPassword");
			}
		} else {
			return false;
		}
		log.debug("Service forgetPassword End");
		return true;
	}
}
