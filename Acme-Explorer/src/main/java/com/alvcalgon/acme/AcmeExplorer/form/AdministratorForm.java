package com.alvcalgon.acme.AcmeExplorer.form;

public class AdministratorForm extends ActorForm {

	private static final long serialVersionUID = 1L;

	public AdministratorForm() {
		super();
	}

	public AdministratorForm(String name, String surname, String email, String phoneNumber, String address,
			String username, String password, String confirmPassword) {
		super(name, surname, email, phoneNumber, address, username, password, confirmPassword);
	}

}
