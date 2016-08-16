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

import com.sunilos.proj0.dto.CollegeDTO;
import com.sunilos.proj0.dto.StudentDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.exception.DuplicateRecordException;
import com.sunilos.proj0.form.StudentForm;
import com.sunilos.proj0.service.CollegeServiceInt;
import com.sunilos.proj0.service.StudentServiceInt;
import com.sunilos.proj0.util.DataValidator;

@Controller
@RequestMapping(value = "/ctl/Student")
public class StudentCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(StudentCtl.class);

	@Autowired
	private StudentServiceInt service;

	@Autowired
	private CollegeServiceInt collegeService;

	@Autowired
	MessageSource messageSource;

	@Override
	public void preload(Model model) {
		List list;
		list = collegeService.list();
		model.addAttribute("collegeList", list);

	}

	@RequestMapping(method = RequestMethod.GET)
	public String dispaly(@RequestParam(required = false) Long id,
			@ModelAttribute("form") StudentForm form, Model model)
			throws ApplicationException {
		log.debug("StudentCtl display method started");

		if (id != null && id > 0) {
			form.populate(service.findByPK(id));
		}

		return "Student";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(Locale locale,
			@RequestParam(required = false) String operation,
			@ModelAttribute("form") @Valid StudentForm form,
			BindingResult bindingResult, Model model, HttpSession session) {
		log.debug("StudentCtl submit method started");
		long id1 = form.getDto().getId();
		if (OP_CANCEL.equals(operation) && (id1 != 0)) {
			return "redirect:Student/search";
		} else if (OP_CANCEL.equals(operation)) {
			return "redirect:Student";
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

				StudentDTO dto = (StudentDTO) form.getDto();
				CollegeDTO collegeDto = collegeService.findByPK(form
						.getCollegeId());
				dto.setCollegeName(collegeDto.getName());
				
				if (form.getDob() != null) {
					if (!DataValidator.ageLimit(form.getDob())) {

						bindingResult.rejectValue("dob", "dob.ageLimit");

						return "Student";
					}
				}
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

		return "Student";

	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchList(@ModelAttribute("form") StudentForm form,
			Model model) throws ApplicationException {
		log.debug("studentCtl search list method started");
		StudentDTO dto = new StudentDTO();
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
		return "StudentList";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchList(Locale locale,
			@ModelAttribute("form") StudentForm form,
			@RequestParam(required = false) Integer pageNO,
			@RequestParam(required = false) String operation, Model model) {

		log.debug("StudentCtl search list methos started");

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
		StudentDTO dto = (StudentDTO) form.getDto();

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

		return "StudentList";
	}

}
