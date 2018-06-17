package br.com.intelipost.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.intelipost.config.services.LoginHistoryJwtService;
import br.com.intelipost.domain.LoginHistoryJwt;

@RestController
public class LoginHistoryJwtResource {

	@Autowired
	LoginHistoryJwtService service;

	@GetMapping("/history")
	@ResponseBody
	public List<LoginHistoryJwt> getMessage() {
		return service.listAll();
	}
}
