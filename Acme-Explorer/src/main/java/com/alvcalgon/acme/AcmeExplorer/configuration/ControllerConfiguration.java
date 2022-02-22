package com.alvcalgon.acme.AcmeExplorer.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.alvcalgon.acme.AcmeExplorer.services.UtilityService;

@ControllerAdvice
public class ControllerConfiguration {

	@Autowired
	private UtilityService utilityService;

	// Handlers ---------------------------------------------------------------

	@ExceptionHandler(Throwable.class)
	public ModelAndView handleException(final Throwable oops) {
		ModelAndView result;

		result = new ModelAndView("home/panic");
		result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

		result.addObject("moment", this.utilityService.getCurrentMomentString());
		result.addObject("oops", oops);

		return result;
	}

}
