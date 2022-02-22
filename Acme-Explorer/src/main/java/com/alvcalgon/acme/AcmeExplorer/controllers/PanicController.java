package com.alvcalgon.acme.AcmeExplorer.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alvcalgon.acme.AcmeExplorer.services.UtilityService;

@Controller
public class PanicController implements ErrorController {

	@Autowired
	private UtilityService utilityService;

	// ErrorController interface ----------------------------------------------
	@GetMapping("/panic")
	public ModelAndView panic(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView result;

		result = new ModelAndView("home/panic");
		result.addObject("moment", utilityService.getCurrentMomentString());

		return result;
	}

	public String getErrorPath() {
		return "/home/panic";
	}

}
