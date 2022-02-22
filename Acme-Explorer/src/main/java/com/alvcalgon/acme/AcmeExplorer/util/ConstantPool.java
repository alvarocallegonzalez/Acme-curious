package com.alvcalgon.acme.AcmeExplorer.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ConstantPool {

	public static final String BLANK = "";

	// KIND OF ROLES -----------------------------------------------
	public static final String ADMINISTRATOR = "ADMINISTRATOR";
	public static final String RANGER = "RANGER";
	public static final String EXPLORER = "EXPLORER";
	public static final String MANAGER = "MANAGER";

	public static final String ERROR_MESSAGE = "errorMessage";
	public static final String ERROR_MESSAGE_DEFAULT = "Unknown error";

	// VALIDATION ERROR MESSAGES
	public static final String MSG_NOT_BLANK = "{field.not.blank}";
	public static final String MSG_EMAIL = "{field.email}";
	public static final String MSG_NOT_NUMBERS = "{field.not.numbers}";
	public static final String MSG_RANGE = "{field.range}";

	// PATTERN
	public static final String PATTERN_NO_NUMBERS = "^[^0-9]+$";
	public static final String PATTERN_IBAN = "\"[ES]{2}[0-9]{6}[0-9]{4}[0-9]{4}[0-9]{4}[0-9]{4}\"";

	// HTML
	public static final String HTML_ADMIN_EDIT = "actor/adminEdit";
	public static final String HTML_HOME_LOGIN = "home/login";

	// VIEW
	public static final String VIEW_ADMIN_FORM = "administratorForm";

	// ASSERT
	public static final String ASSERT_ERROR_MSG_NUMBERS_NOT_ALLOWED = "Numbers are not allowed";
	public static final String ASSERT_ERROR_MSG_AUTHORITY__DID_NOT_INCLUDE = "Authority didn't include";
	public static final String ASSERT_ERROR_MSG_AUTHORITY_INCLUDED = "Authority already included";
	public static final String ASSERT_ERROR_MSG_NULL_ELEMENT = "Null element";
	public static final String ASSERT_ERROR_MSG_PASSWORD_MATCH = "Passwords don't match";
	public static final String ASSERT_ERROR_MSG_UNIQUE_USERNAME = "There's already a user with the same user name";

	public static final String KEY_ENGLISH = "en";
	public static final String KEY_SPANISH = "es";

	public static final Map<String, Map<String, String>> MAP_I18_ERROR_MESSAGE;

	static {
		Map<String, Map<String, String>> mapAux = new HashMap<>();
		Map<String, String> mapTemp = new HashMap<>();

		MAP_I18_ERROR_MESSAGE = Collections.unmodifiableMap(mapAux);
	}
}
