package br.com.intelipost.resources;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginResource {

	@GetMapping("/login")
	@ResponseBody
	public String getMessage(HttpServletRequest request) {
		return "Login by: " + request.getServerName() + ":" + request.getServerPort();
	}
}
