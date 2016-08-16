package com.sunilos.proj0.ctl;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.ws.BindingType;

import org.springframework.validation.BindingResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sunilos.proj0.dto.RoleDTO;
import com.sunilos.proj0.dto.UserDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.exception.DuplicateRecordException;
import com.sunilos.proj0.exception.RecordNotFoundException;
import com.sunilos.proj0.form.ForgetPasswordForm;
import com.sunilos.proj0.form.LoginForm;
import com.sunilos.proj0.form.UserRegistrationForm;
import com.sunilos.proj0.service.UserServiceInt;

@Controller
public class LoginCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(LoginCtl.class);

	protected static final String OP_SINGIN = "SignIn";
	protected static final String OP_SIGNUP = "SignUp";

	@Autowired
	private UserServiceInt service;

	@Autowired
	private MessageSource messageSources;

	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String display(@ModelAttribute("form") LoginForm form,
			HttpSession session, Model model) {
		log.debug("login display started");
		session.invalidate();
		return "Login";
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String submit(Locale locale, HttpSession session,
			HttpServletResponse response,

			@ModelAttribute("form") @Valid LoginForm form,
			BindingResult bindingResult, Model model)
			throws ApplicationException, RecordNotFoundException, IOException {

		log.debug("Login submit started");
		if (bindingResult.hasErrors()) {
			return "Login";
		}
		if (OP_SINGIN.equalsIgnoreCase(form.getOperation())) {
			UserDTO dto = new UserDTO();
			dto.setLogin(form.getEmailId());
			dto.setPassword(form.getPassword());

			dto = service.authenticate(dto);
			if (dto != null) {

				session.setAttribute("user", dto);
				model.addAttribute("message", "Welcome :" + form.getEmailId());
			} else {
				String msg = messageSources.getMessage("login.error", null,
						locale);
				model.addAttribute("error", msg);
				return "Login";
			}
		}
		log.debug("Login submit End");
		return "Welcome";

	}

	@RequestMapping(value = "/ForgetPassword", method = RequestMethod.GET)
	public String display(@ModelAttribute("form") ForgetPasswordForm form,
			Model model) {

		return "ForgetPassword";

	}

	@RequestMapping(value = "/ForgetPassword", method = RequestMethod.POST)
	public String submit(Locale locale,
			@ModelAttribute("form") @Valid ForgetPasswordForm form,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "ForgetPassword";
		}
		UserDTO dto = service.findByLogin(form.getLogin());

		if (dto == null) {
			String msg = messageSources.getMessage("forgetpass.error", null,
					locale);
			model.addAttribute("error", msg);
			return "ForgetPassword";
		}
		try {
			service.forgetPassword(form.getLogin());
			model.addAttribute("success",
					"Password has been sent to your registered Email ID!!");
		} catch (ApplicationException e) {
			log.error("Critical Issue ", e);
			return "Error";
		}

		return "ForgetPassword";
	}

	@RequestMapping(value = "/SignUp", method = RequestMethod.GET)
	public String dispaly(@ModelAttribute("form") UserRegistrationForm form,
			Model model) {
		log.debug("UserRegistration display method started");
		return "UserRegistration";
	}

	@RequestMapping(value = "/SignUp", method = RequestMethod.POST)
	public String submit(Model model,
			@ModelAttribute("form") @Valid UserRegistrationForm form,
			BindingResult bindingResult, Locale locale) {
		log.debug("userRegistration submit method started");
		if (bindingResult.hasErrors()) {
			return "UserRegistration";
		}
		UserDTO dto = null;
		dto = service.findByLogin(form.getLogin());
		if (dto != null) {
			model.addAttribute("error", "Login Id is already exist");
			return "UserRegistration";
		} else {

		}
		dto = (UserDTO) form.getDto();
		dto.setRoleId((long) RoleDTO.STUDENT);
		try {
			service.registerUser(dto);
		} catch (ApplicationException e) {
			return "Error";
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
		}
		model.addAttribute("success", "Registration processed Succesfully");
		return "UserRegistration";

	}

	/*
	 * @RequestMapping(value = "/Error", method = RequestMethod.GET) public
	 * String error() { return "Error"; }
	 */

}
