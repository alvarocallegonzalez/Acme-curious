package com.alvcalgon.acme.AcmeExplorer.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alvcalgon.acme.AcmeExplorer.bean.Actor;
import com.alvcalgon.acme.AcmeExplorer.services.ActorService;
import com.alvcalgon.acme.AcmeExplorer.services.UtilityService;

@Controller
@RequestMapping("/")
public class HomeController {
	private static final Log log = LogFactory.getLog(HomeController.class);

	@Autowired
	private UtilityService utilityService;
	@Autowired
	private ActorService actorService;
	
	
	@GetMapping("home")
	public ModelAndView home() {
		log.debug("Rendering home...");
		
		Actor principal = actorService.findByPrincipal();
		
		ModelAndView result = new ModelAndView("home/index");
		// TODO: Include some attributes in view
		result.addObject("principal", principal);

		return result;
	}

	@GetMapping("login")
	public ModelAndView login() {
		log.debug("Rendering login form...");
		System.out.println("Rendering login form...");
		
		ModelAndView result = new ModelAndView("home/login");
		
		return result;
	}

	@GetMapping("error")
	public ModelAndView error() {		
		ModelAndView result = new ModelAndView("home/error");
		
		return result;
	}
}
