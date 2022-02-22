package com.alvcalgon.acme.AcmeExplorer.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.alvcalgon.acme.AcmeExplorer.util.ConstantPool;

@ControllerAdvice
public class ErrorController {

	private static Log log = LogFactory.getLog(ErrorController.class);

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleException(Throwable throwable) {
		ModelAndView result = new ModelAndView("home/error");

		String errorMessage = throwable != null ? throwable.getLocalizedMessage() : "Unknown error";
		result.addObject(ConstantPool.ERROR_MESSAGE, errorMessage);

		log.error("Error capturado", throwable);

		return result;
	}

}
