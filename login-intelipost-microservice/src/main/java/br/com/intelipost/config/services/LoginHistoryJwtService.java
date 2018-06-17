package br.com.intelipost.config.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.intelipost.domain.LoginHistoryJwt;
import br.com.intelipost.repository.LoginHistoryJwtRepository;

@Service
public class LoginHistoryJwtService {

	@Autowired
	private LoginHistoryJwtRepository repository;

	public List<LoginHistoryJwt> listAll() {
		List<LoginHistoryJwt> histories = new ArrayList<>();
		repository.findAll().forEach(histories::add);
		return histories;
	}

}
