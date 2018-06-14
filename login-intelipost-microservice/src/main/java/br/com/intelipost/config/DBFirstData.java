package br.com.intelipost.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.intelipost.domain.UserIntelipost;
import br.com.intelipost.repository.UserIntelipostRepository;

/**
 * @author jpereira Classe apenas para facilitar o dev, simulando uma carga
 *         inicial
 */
@Configuration
public class DBFirstData {

	@Autowired
	UserIntelipostRepository repository;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Bean
	public boolean cargaInicial() {
		if (repository.count() < 1) {
			UserIntelipost user = new UserIntelipost(pe.encode("admin"), "Administrador", "admin@admin");
			repository.save(user);
		}
		return true;
	}

}
