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

import com.sunilos.proj0.dto.MarksheetDTO;
import com.sunilos.proj0.dto.StudentDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.exception.DuplicateRecordException;
import com.sunilos.proj0.form.MarksheetForm;
import com.sunilos.proj0.service.MarksheetServiceInt;
import com.sunilos.proj0.service.StudentServiceInt;

@Controller
@RequestMapping(value = "/ctl/Marksheet")
public class MarksheetCtl extends BaseCtl {
	private static Logger log = Logger.getLogger(MarksheetCtl.class);

	@Autowired
	private MarksheetServiceInt service;

	@Autowired
	private StudentServiceInt studentService;

	@Autowired
	private MessageSource messageSource;

	@Override
	public void preload(Model model) {
		List list = studentService.list();
		model.addAttribute("studentList", list);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String display(@RequestParam(required = false) Long id,
			@ModelAttribute("form") MarksheetForm form, Model model)
			throws ApplicationException {
		if (id != null && id > 0) {
			MarksheetDTO dto = service.findByPK(id);
			form.populate(dto);
		}
		return "Marksheet";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(Locale locale,
			@RequestParam(required = false) String operation,
			@ModelAttribute("form") @Valid MarksheetForm form,
			BindingResult bindingResult, Model model, HttpSession session) {
		log.debug("MarksheetCtl submit method started");
		long id1 = form.getDto().getId();
		if (OP_CANCEL.equals(operation) && (id1 != 0)) {
			return "redirect:Marksheet/search";
		} else if (OP_CANCEL.equals(operation)) {
			return "redirect:Marksheet";
		}
		if (bindingResult.hasErrors()) {
			List list = bindingResult.getAllErrors();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				Object ob = it.next();
			}
			return "Marksheet";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(operation)) {

				MarksheetDTO dto = (MarksheetDTO) form.getDto();
				StudentDTO studentDto = studentService.findByPK(form
						.getStudentId());
				dto.setName(studentDto.getFirstName() + " "
						+ studentDto.getLastName());
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

		return "Marksheet";

	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchList(@ModelAttribute("form") MarksheetForm form,
			Model model) throws ApplicationException {
		log.debug("MarksheetCtl search list method started");
		MarksheetDTO dto = new MarksheetDTO();
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
		return "MarksheetList";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchList(Locale locale,
			@ModelAttribute("form") MarksheetForm form,
			@RequestParam(required = false) Integer pageNO,
			@RequestParam(required = false) String operation, Model model) {

		log.debug("MarksheetCtl search list methos started");

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
		MarksheetDTO dto = (MarksheetDTO) form.getDto();

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

		return "MarksheetList";
	}

	@RequestMapping(value = "/meritlist", method = RequestMethod.GET)
	public String getMeritList(@ModelAttribute("form") MarksheetForm form,
			Model model) throws ApplicationException {
		List meritlist = service.getMeritList(0, 0);
		for (int i = 0; i < meritlist.size(); i++) {
			System.out.println(meritlist.get(i).toString());
		}
		model.addAttribute("list", meritlist);
		return "GetMeritList";

	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String getMarksheet(@ModelAttribute("form") MarksheetForm form,
			Model model) {
		log.debug("Marksheet Ctl getMarksheet method started");
		return "GetMarksheet";
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public String getMarksheet(@ModelAttribute("form") MarksheetForm form,
			@RequestParam(required = false) String operation, Model model,
			Locale locale) throws ApplicationException {
		log.debug("MarksheetCtl getMarksheet method started");

		if (OP_GET.equalsIgnoreCase(operation) && form.getRollNo() != null) {
			MarksheetDTO dto = service.findByRollNo(form.getRollNo());
			if (dto != null) {

				form.populate(dto);
			} else {
				String msg = messageSource.getMessage("error.notFound", null,
						locale);
				model.addAttribute("error", msg);
			}

		}
		if (OP_GET.equalsIgnoreCase(operation)
				&& form.getRollNo().length() == 0) {
			model.addAttribute("error", "Please Enter RollNo");
		}

		return "GetMarksheet";
	}

}
