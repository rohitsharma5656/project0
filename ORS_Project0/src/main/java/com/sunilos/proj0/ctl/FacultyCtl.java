package com.sunilos.proj0.ctl;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

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
import com.sunilos.proj0.dto.FacultyDTO;
import com.sunilos.proj0.dto.UserDTO;
import com.sunilos.proj0.exception.DuplicateRecordException;
import com.sunilos.proj0.form.FacultyForm;
import com.sunilos.proj0.service.CollegeServiceInt;
import com.sunilos.proj0.service.FacultyServiceInt;
import com.sunilos.proj0.service.UserServiceInt;

@Controller
@RequestMapping(value = "/ctl/Faculty")
public class FacultyCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(FacultyCtl.class);

	@Autowired
	private FacultyServiceInt service;

	@Autowired
	private CollegeServiceInt collegeService;

	@Autowired
	private UserServiceInt userService;

	@Autowired
	private MessageSource messageSource;

	@Override
	public void preload(Model model) {

		List facultyList;
		facultyList = userService.list();
		model.addAttribute("facultyList", facultyList);

		List collegeList;
		collegeList = collegeService.list();
		model.addAttribute("collegeList", collegeList);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String display(@RequestParam(required = false) Long id,
			@ModelAttribute("form") FacultyForm form, Model model) {
		log.debug("FacultyCtl display method started");
		if (id != null && id > 0) {
			FacultyDTO dto = service.findByPK(id);
			form.populate(dto);
		}
		return "Faculty";

	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(Locale locale,
			@RequestParam(required = false) String operation,
			@ModelAttribute("form") @Valid FacultyForm form,
			BindingResult bindingResult, Model model) {
		log.debug("FacultyCtl submit method started");

		long id1 = form.getDto().getId();
		if (OP_CANCEL.equals(operation) && (id1 != 0)) {
			return "redirect:Faculty/search";
		} else if (OP_CANCEL.equals(operation)) {
			return "redirect:Faculty";
		}
		if (bindingResult.hasErrors()) {
			List list = bindingResult.getAllErrors();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				Object ob = it.next();
			}
			return "Faculty";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(operation)) {

				FacultyDTO dto = (FacultyDTO) form.getDto();
				UserDTO userDto = userService.findByPK(form.getUserId());
				dto.setFacultyName(userDto.getFirstName() + " "
						+ userDto.getLastName());
				dto.setLogin(userDto.getLogin());
				CollegeDTO collegeDto = collegeService.findByPK(form
						.getCollegeId());
				dto.setCollegeName(collegeDto.getName());
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

		return "Faculty";

	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchList(Locale locale,
			@ModelAttribute("form") FacultyForm form, Model model) {
		log.debug("FacultyCtl search method started");
		FacultyDTO dto = (FacultyDTO) form.getDto();
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
		return "FacultyList";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchList(@RequestParam(required = false) String operation,
			@ModelAttribute("form") FacultyForm form,
			@RequestParam(required = false) Integer pageNO,
			BindingResult bindingResult, Locale locale, Model model) {
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
		FacultyDTO dto = (FacultyDTO) form.getDto();

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

		return "FacultyList";
	}

}
