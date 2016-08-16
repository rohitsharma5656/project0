package com.sunilos.proj0.ctl;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

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

import com.sunilos.proj0.dto.RoleDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.exception.DuplicateRecordException;
import com.sunilos.proj0.form.RoleForm;
import com.sunilos.proj0.service.RoleServiceInt;

@Controller
@RequestMapping(value = "/ctl/Role")
public class RoleCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(RoleCtl.class);

	@Autowired
	private RoleServiceInt service;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public String display(@RequestParam(required = false) Long id,
			@ModelAttribute("form") RoleForm form, Model model,
			HttpSession session) throws ApplicationException {
		log.debug("RoleCtl doDisplay started");
		if (id != null && id > 0) {

			form.populate(service.findByPK(id));
		}
		return "Role";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(Locale locale,
			@ModelAttribute("form") @Valid RoleForm form,
			BindingResult bindingResult,
			@RequestParam(required = false) String operation, Model model) {
		log.debug("RoleCtl submit method started");

		long id1 = form.getDto().getId();
		if (OP_CANCEL.equals(operation) && (id1 != 0)) {
			return "redirect:Role/search";
		} else if (OP_CANCEL.equals(operation)) {
			return "redirect:Role";
		}
		if (bindingResult.hasErrors()) {
			return "Role";
		}
		if (bindingResult.hasErrors()) {
			List list = bindingResult.getAllErrors();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				Object ob = it.next();
			}
			return "Student";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(operation)) {

				RoleDTO dto = (RoleDTO) form.getDto();
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

		return "Role";

	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchList(@ModelAttribute("form") RoleForm form, Model model)
			throws ApplicationException {
		RoleDTO dto = (RoleDTO) form.getDto();
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
		return "RoleList";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchList(Locale locale,
			@ModelAttribute("form") RoleForm form, BindingResult bindingResult,
			@RequestParam(required = false) Integer pageNO,
			@RequestParam(required = false) String operation, Model model)
			throws ApplicationException {
		log.debug("RoleCtl list submit method started");

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
					model.addAttribute("success", "Record deleted successfully");
				}
			} else {
				String msg = messageSource.getMessage("message.delete.error",
						null, locale);
				model.addAttribute("error", msg);
			}
		}

		// Get search attributes
		RoleDTO dto = (RoleDTO) form.getDto();

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

		return "RoleList";
	}

}
