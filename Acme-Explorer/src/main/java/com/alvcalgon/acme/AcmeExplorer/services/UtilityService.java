package com.alvcalgon.acme.AcmeExplorer.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriUtils;

import com.alvcalgon.acme.AcmeExplorer.util.ConstantPool;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class UtilityService {

	private static final Log log = LogFactory.getLog(UtilityService.class);

	// public static final String API_URI = "https://fone-api.herokuapp.com";
	public static final String API_URI = "http://localhost:8080";

	public static final int DEFAULT_LIMIT = 10;
	public static final int DEFAULT_OFFSET_TO_USER = 1;
	public static final int DEFAULT_OFFSET_TO_API = 0;

	public static final int POS_TOTAL_PAGES = 0;
	public static final int POS_TOTAL_ELEMENTS = 1;
	public static final int POS_OFFSET = 2;
	public static final String CADENA_VACIA = "";

	// @Autowired
	// private RestTemplate restTemplate;

	public String getValueOrDefault(String text, String defaultValue) {
		return StringUtils.hasText(text) ? text.trim() : defaultValue;
	}

	public String getValueOrDefault(String text) {
		return StringUtils.hasText(text) ? text.trim() : ConstantPool.BLANK;
	}

	public String getMessageError(String message) {
		if (StringUtils.hasText(message)) {
			String messageValue = ConstantPool.ERROR_MESSAGE_DEFAULT;
			Locale locale = LocaleContextHolder.getLocale();
			log.debug("Locale: " + locale);

			// TODO: We have to internationalize message error
			switch (message) {
			case ConstantPool.ASSERT_ERROR_MSG_PASSWORD_MATCH:
				messageValue = "Password and Confirm password don't match";
				break;
			case ConstantPool.ASSERT_ERROR_MSG_UNIQUE_USERNAME:
				messageValue = "Username must be unique";
				break;
			case ConstantPool.ASSERT_ERROR_MSG_NULL_ELEMENT:
				messageValue = "Null or Unknown Authority";
				break;
			case ConstantPool.ASSERT_ERROR_MSG_AUTHORITY_INCLUDED:
				messageValue = "Authority set yet";
				break;
			case ConstantPool.ASSERT_ERROR_MSG_NUMBERS_NOT_ALLOWED:
				messageValue = "Numbers are not allowed";
				break;
			default:
				log.debug("Error desconocido");
				break;
			}

			return messageValue;
		}

		return ConstantPool.BLANK;
	}

	public boolean checkPassword(String password, String passwordMatch) {
		return StringUtils.hasText(password) && StringUtils.hasText(passwordMatch) && password.equals(passwordMatch);
	}

	public UserDetails getUserDetailsByPrincipal() {
		UserDetails userDetails = null;

		SecurityContext securityContext = SecurityContextHolder.getContext();

		if (securityContext != null) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			if (authentication != null) {
				userDetails = (UserDetails) authentication.getPrincipal();
			}
		}

		return userDetails;
	}

	public String getTextEncoded(String text) {
		if (StringUtils.hasText(text)) {
			PasswordEncoder encoder = new BCryptPasswordEncoder(5);

			String textTemp = text.trim();

			return encoder.encode(textTemp);
		}

		return CADENA_VACIA;
	}

	public <T> T getObjectFromJSON(String uri, Class<T> classOutput) {
		String strJSON = getStringOfJSON(uri);

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();

		T result = gson.fromJson(strJSON, classOutput);

		return result;
	}

	public String getURI(String url, Optional<Integer> selectedPage, int totalElements) {
		// Validamos offset
		int valid_selectedPage = this.getValidOffset(selectedPage, totalElements);
		int targetPage = valid_selectedPage - 1;
		log.info("Offset de la consulta: " + targetPage);

		String result = url + "?offset=" + targetPage;

		return result;
	}

	public String getCurrentMomentString() {
		SimpleDateFormat formatter;
		String result;

		formatter = (LocaleContextHolder.getLocale().getLanguage() == "es") ? new SimpleDateFormat("dd/MM/yyyy HH:mm")
				: new SimpleDateFormat("yyyy/MM/dd HH:mm");

		result = formatter.format(new Date());

		return result;
	}

	public List<LinkedHashMap<String, Object>> listJSON(String url) {
		List<LinkedHashMap<String, Object>> results = new ArrayList<>();
		URI uri;

		try {
			uri = new URI(url);

			// TODO:results = this.restTemplate.getForObject(uri, List.class);
		} catch (URISyntaxException use) {
			log.info("Error en la url de la API: " + use.getMessage());

			results = new ArrayList<LinkedHashMap<String, Object>>();
		} catch (Throwable oops) {
			log.info("Error al parsear los objetos del json: " + oops.getMessage());

			results = new ArrayList<LinkedHashMap<String, Object>>();
		}

		return results;
	}

	public Integer countJSON(String url) {
		Integer result = 0;
		URI uri;

		try {
			uri = new URI(url);

			// result = this.restTemplate.getForObject(uri, Integer.class);
		} catch (URISyntaxException use) {
			log.info("Error en la url de la API: " + use.getMessage());

			result = null;
		} catch (Throwable oops) {
			log.info("Error al parsear los objetos del json: " + oops.getMessage());

			result = null;
		}

		return result;
	}

	public String getStringOfJSON(String strUri) {
		String result = CADENA_VACIA;

		try {
			URI uri = new URI(strUri);

			// result = this.restTemplate.getForObject(uri, String.class);
		} catch (URISyntaxException use) {
			log.error("Error al recuperar el json", use);
		}

		return result;
	}

	public String getStringOfJSON(String url, int offset) {
		String uri = url + "?offset=" + offset + "&limit=" + DEFAULT_LIMIT;

		String result = this.getStringOfJSON(uri);

		return result;
	}

	public List<Integer> getPages(int totalPages) {
		List<Integer> results;

		results = new ArrayList<Integer>();

		if (totalPages > 0) {
			results = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
		}

		return results;
	}

	public int getValidOffset(Optional<Integer> selectedPage, int totalElements) {
		int result;
		int totalPages;

		if (selectedPage != null && totalElements > 0) {
			totalPages = (totalElements % DEFAULT_LIMIT == 0) ? totalElements / DEFAULT_LIMIT
					: (totalElements / DEFAULT_LIMIT) + 1;

			result = (selectedPage.isPresent()) ? selectedPage.get() : 1;
			if (result < 1) {
				result = 1;
			}
			if (result > totalPages) {
				result = totalPages;
			}
		} else {
			result = 1;
		}

		return result;
	}

	public int getValidOffset(Optional<Integer> selectedPage) {
		int result;

		result = 1;
		if (selectedPage != null) {
			result = selectedPage.orElse(1);

			if (result < 1) {
				result = 1;
			}
		}

		return result;
	}

	protected String getEncodedText(String text) {
		String result;

		result = UriUtils.encode(text, "UTF-8");

		return result;
	}

	public String getStringFromKey(LinkedHashMap<String, Object> map, String key) {
		String result;

		result = (map.containsKey(key)) ? (String) map.get(key) : UtilityService.CADENA_VACIA;

		return result;
	}

}
