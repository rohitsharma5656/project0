package com.sunilos.proj0.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/AboutUs")
public class AboutUsCtl {

	@RequestMapping(method = RequestMethod.GET)
	public String display(ModelMap model) {
		return "AboutUs";
	}

}
