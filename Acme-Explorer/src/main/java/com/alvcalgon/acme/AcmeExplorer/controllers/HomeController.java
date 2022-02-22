package com.alvcalgon.acme.AcmeExplorer.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alvcalgon.acme.AcmeExplorer.services.UtilityService;

@Controller
@RequestMapping("/")
public class HomeController {
	private static final Log log = LogFactory.getLog(HomeController.class);

	@Autowired
	private UtilityService utilityService;

	@GetMapping("home")
	public ModelAndView home() {
		log.debug("Rendering home...");

		ModelAndView result = new ModelAndView("home/index");
		// TODO: Include some attributes in view
		// result.addObject(attributeName, attributeValue)

		return result;
	}

	@GetMapping("login")
	public ModelAndView login() {
		log.debug("Rendering login form...");

		// TODO: cambiar mas adelante
		// ModelAndView result = new ModelAndView("home/login");
		ModelAndView result = new ModelAndView("home/login2");

		return result;
	}

	@GetMapping("logout")
	public ModelAndView logout() {
		log.debug("Rendering logout...");

		ModelAndView result = new ModelAndView("home/logout");

		return result;
	}

}
