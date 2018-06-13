package br.com.intelipost.resources;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginResource {
	private static final Logger logger = LoggerFactory.getLogger(LoginResource.class);

	@GetMapping("/login")
	@ResponseBody
	public String getMessage(HttpServletRequest request) {
		logger.trace("Request by: " + request.getServerName() + ":" + request.getServerPort());

		return "login";
	}
}
