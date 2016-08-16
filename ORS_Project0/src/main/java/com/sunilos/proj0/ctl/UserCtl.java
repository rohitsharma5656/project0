package com.sunilos.proj0.ctl;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sunilos.proj0.dto.CollegeDTO;
import com.sunilos.proj0.dto.RoleDTO;
import com.sunilos.proj0.dto.StudentDTO;
import com.sunilos.proj0.dto.UserDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.exception.DuplicateRecordException;
import com.sunilos.proj0.form.ChangePasswordForm;
import com.sunilos.proj0.form.MyProfileForm;
import com.sunilos.proj0.form.UserForm;
import com.sunilos.proj0.service.RoleServiceInt;
import com.sunilos.proj0.service.UserServiceInt;
import com.sunilos.proj0.util.DataValidator;

@Controller
@RequestMapping(value = "/ctl/User")
public class UserCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(UserCtl.class);

	@Autowired
	private UserServiceInt service;

	@Autowired
	private RoleServiceInt roleService;

	@Autowired
	MessageSource messageSource;

	@Override
	public void preload(Model model) {
		List list = roleService.list();
		model.addAttribute("roleList", list);

	}

	@RequestMapping(method = RequestMethod.GET)
	public String display(@RequestParam(required = false) Long id,
			@ModelAttribute("form") UserForm form, Model model)
			throws ApplicationException {
		log.debug("UserCtl dispaly method Started");
		if (id != null && id > 0) {
			UserDTO dto = service.findByPK(id);
			form.populate(dto);
		}
		return "User";

	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(Locale locale,@RequestParam(required = false) String operation,
			@ModelAttribute("form") @Valid UserForm form,
			BindingResult bindingResult, HttpSession session, Model model)
			throws ApplicationException, IOException, MessagingException {
		log.debug("UserCtl submit started");
		long id1 = form.getDto().getId();
		if (OP_CANCEL.equals(operation) && (id1 != 0)) {
			return "redirect:User/search";
		} else if (OP_CANCEL.equals(operation)) {
			return "redirect:User";
		}
		if (bindingResult.hasErrors()) {
			List list = bindingResult.getAllErrors();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				Object ob = it.next();
				System.out.println(ob);
			}
			return "User";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(operation)) 
			{
				/*if (form.getDob() != null) {
					if (!DataValidator.ageLimit(form.getDob())) {

						bindingResult.rejectValue("dob", "dob.ageLimit");

						return "User";
					}
				}
*/

				UserDTO dto = (UserDTO) form.getDto();
				if (dto.getId() != 0) {
					service.update(dto);
					String msg = messageSource.getMessage("message.update",
							null, locale);
					model.addAttribute("success", msg);

				} else {
					service.add(dto);
					String msg = messageSource.getMessage("message.success",
							null, locale);
					model.addAttribute("success", msg);

				}
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			log.error(e);
			e.printStackTrace();
		}

		return "User";

	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchList(@ModelAttribute("form") UserForm form, Model model)
			throws ApplicationException {
		log.debug("searchList method by request  Getmethod started");
		UserDTO dto = (UserDTO) form.getDto();
		int i = service.search(dto).size();
		int size = 0;
		if (i % 5 == 0) {

			size = i / 5;
		} else {
			size = (i / 5) + 1;
		}
		model.addAttribute("size", size);

		model.addAttribute("list",
				service.search(dto, form.getPageNo(), form.getPageSize()));
		return "UserList";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchList(Model model,
			@ModelAttribute("form") UserForm form,
			@RequestParam(required = false) Integer pageNO,
			@RequestParam(required = false) String operation, Locale locale)
			throws ApplicationException {
		log.debug("In searchList Method");

		// Calculate next page number
		int pageNo = form.getPageNo();
		if (pageNO != null && pageNO > 0) {
			pageNo = pageNO;

		}
		if (OP_SEARCH.equals(operation)) {
			pageNo = 1;
		}
		pageNo = (pageNo < 1) ? 1 : pageNo;

		form.setPageNo(pageNo);
		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					service.delete(id);
					String msg = messageSource.getMessage("message.delete",
							null, locale);
					model.addAttribute("success", msg);
				}
			} else {
				String msg = messageSource.getMessage("message.delete.error",
						null, locale);
				model.addAttribute("error", msg);
			}
		}

		// Get search attributes
		UserDTO dto = (UserDTO) form.getDto();

		model.addAttribute("list",
				service.search(dto, pageNo, form.getPageSize()));

		int i = service.search(dto).size();
		int size = 0;
		if (i % 5 == 0) {
			size = i / 5;
		} else {
			size = (i / 5) + 1;
		}
		if (i == 0) {
			String msg = messageSource.getMessage("error.notFound", null,
					locale);
			model.addAttribute("error", msg);
		}
		model.addAttribute("size", size);

		return "UserList";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String dispalyProfile(HttpSession session,
			@ModelAttribute("form") MyProfileForm form, Model model)
			throws ApplicationException {
		log.debug("MyProfileCtl dispaly method started");
		UserDTO dto = (UserDTO) session.getAttribute("user");
		UserDTO dto2 = service.findByPK(dto.getId());
		form.populate(dto2);
		return "MyProfile";

	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String submitProfile(Locale locale,
			@RequestParam(required = false) String operation,
			@ModelAttribute("form") @Valid MyProfileForm form,
			BindingResult bindingResult, Model model)
			throws ApplicationException {
		log.debug("MyProfileCtl submit method started");

		if (bindingResult.hasErrors()) {
			return "MyProfile";
		}

		UserDTO dto = service.findByPK(form.getId());
		dto.setLogin(form.getLogin());
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setGender(form.getGender());
		dto.setMobileNo(form.getMobileNo());
		dto.setDob(new Date(form.getDob()));
		try {
			service.update(dto);
			/*
			 * String msg = messageSource.getMessage("message.success", null,
			 * locale); model.addAttribute("success", msg);
			 */
			model.addAttribute("success", "Data is successfully updated");
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", "LoginId does not exist");

			e.printStackTrace();
		}
		if (OP_CANCEL.equals(form.getOperation())) {

			return "AboutUs";

		}
		return "MyProfile";

	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public String displayChangePassword(
			@ModelAttribute("form") ChangePasswordForm form, Model model) {
		log.debug("dispalyChangePassword started");
		return "ChangePassword";

	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public String submitChangePassword(Locale locale,
			@RequestParam(required = false) String operation,
			@ModelAttribute("form") @Valid ChangePasswordForm form,
			BindingResult bindingResult, Model model, HttpSession session)
			throws DuplicateRecordException, ApplicationException {
		log.debug("submitChangePassword Started");
		if (bindingResult.hasErrors()) {
			return "ChangePassword";
		}

		if (form.getNewpassword().equals(form.getConfirmpassword())) {
			UserDTO dto = (UserDTO) session.getAttribute("user");

			dto = service.findByPK(dto.getId());
			if (dto.getPassword().equals(form.getOldpassword())) {
				dto.setPassword(form.getNewpassword());
				service.update(dto);

				model.addAttribute("success", "Password Changed successfully");
			} else {
				model.addAttribute("error", "Old Pasword is not valid");
			}

		} else {
			model.addAttribute("error",
					"New password and confirm Password does not match");
			return "ChangePassword";
		}

		return "ChangePassword";

	}
}
