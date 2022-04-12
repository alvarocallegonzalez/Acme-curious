package com.alvcalgon.acme.AcmeExplorer.controllers.administrator;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alvcalgon.acme.AcmeExplorer.bean.Actor;
import com.alvcalgon.acme.AcmeExplorer.bean.Administrator;
import com.alvcalgon.acme.AcmeExplorer.form.AdministratorForm;
import com.alvcalgon.acme.AcmeExplorer.services.AdministratorService;
import com.alvcalgon.acme.AcmeExplorer.services.UtilityService;
import com.alvcalgon.acme.AcmeExplorer.util.ConstantPool;

@Controller
@RequestMapping("/actor/administrator")
public class ActorAdministratorController {

	private static final Log log = LogFactory.getLog(ActorAdministratorController.class);

	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private UtilityService utilityService;

	@GetMapping("/register")
	public ModelAndView register() {
		log.debug("Render administrator form...");

		Actor principal = administratorService.findByPrincipal();
		
		ModelAndView result = new ModelAndView(ConstantPool.HTML_ADMIN_EDIT);

		AdministratorForm adminForm = new AdministratorForm();

		result.addObject(ConstantPool.VIEW_ADMIN_FORM, adminForm);
		result.addObject("principal", principal);
		
		return result;

	}

	@PostMapping(value = "/register", params = "save")
	public ModelAndView register(@ModelAttribute("administratorForm") @Valid AdministratorForm administratorForm,
			final BindingResult binding) {
		ModelAndView result;

		log.error("administratorForm: " + administratorForm);

		if (binding.hasErrors()) {
			result = createEditModelAndView(administratorForm);
		} else {
			try {
				Administrator saved = this.administratorService.register(administratorForm);

				if (saved != null) {
					result = new ModelAndView(ConstantPool.HTML_HOME_LOGIN);
				} else {
					result = createEditModelAndView(administratorForm, ConstantPool.ERROR_MESSAGE,
							ConstantPool.ERROR_MESSAGE_DEFAULT);
				}
			} catch (Throwable oops) {
				String messageError = utilityService.getMessageError(oops.getMessage());

				result = createEditModelAndView(administratorForm, ConstantPool.ERROR_MESSAGE, messageError);

				log.error("Error durante el registro del administrador:", oops);
			}
		}

		return result;
	}

	// Metodos auxiliares ---------------------------------------------------
	public ModelAndView createEditModelAndView(AdministratorForm administratorForm) {
		ModelAndView result;

		result = new ModelAndView(ConstantPool.HTML_ADMIN_EDIT);
		result.addObject(ConstantPool.VIEW_ADMIN_FORM, administratorForm);

		return result;
	}

	public ModelAndView createEditModelAndView(AdministratorForm administratorForm, String messageName,
			String messageValue) {
		ModelAndView result;

		result = new ModelAndView(ConstantPool.HTML_ADMIN_EDIT);
		result.addObject(ConstantPool.VIEW_ADMIN_FORM, administratorForm);
		result.addObject(messageName, messageValue);

		return result;
	}

}
