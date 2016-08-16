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
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.exception.DuplicateRecordException;
import com.sunilos.proj0.form.CollegeForm;
import com.sunilos.proj0.service.CollegeServiceInt;

@Controller
@RequestMapping(value = "/ctl/College")
public class CollegeCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(CollegeCtl.class);

	@Autowired
	private CollegeServiceInt service;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public String display(Locale locale,
			@RequestParam(required = false) Long id,
			@ModelAttribute("form") CollegeForm form, Model model)
			throws ApplicationException {

		log.debug(" CollegeCtl display started " + id);

		if (id != null && id > 0) {
			CollegeDTO dto = service.findByPK(id);
			form.populate(dto);
		}
		
		return "College";

	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(Locale locale, @RequestParam String operation,
			@ModelAttribute("form") @Valid CollegeForm form,
			BindingResult bindingResult, Model model) {

		log.debug("CollegeCtl submit method started");
		long id1 = form.getDto().getId();
		if (OP_CANCEL.equals(operation) && (id1 != 0)) {
			return "redirect:College/search";
		} else if (OP_CANCEL.equals(operation)) {
			return "redirect:College";
		}
		if (bindingResult.hasErrors()) 
		{
			List list = bindingResult.getAllErrors();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				Object ob = it.next();
			}
			return "College";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(operation)) {

				CollegeDTO dto = (CollegeDTO) form.getDto();
				if (dto.getId() != 0) {
					service.update(dto);
					String msg = messageSource.getMessage("message.update",
							null, locale);
					model.addAttribute("success", msg);

				} 
				else 
				{
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

		return "College";

	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchList(@ModelAttribute("form") CollegeForm form,
			Model model) throws ApplicationException {
		CollegeDTO dto = new CollegeDTO();
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
		return "CollegeList";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchList(Locale locale,
			@ModelAttribute("form") CollegeForm form,
			@RequestParam(required = false) Integer pageNO,
			@RequestParam(required = false) String operation, Model model)
			throws ApplicationException {

		log.debug("in collegectl searchList method");

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
			if (form.getIds() != null) 
			{
				for (long id : form.getIds()) 
				{
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
		CollegeDTO dto = (CollegeDTO) form.getDto();

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

		return "CollegeList";
	}

}
