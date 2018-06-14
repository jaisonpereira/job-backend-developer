package br.com.intelipost.config.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.intelipost.domain.UserIntelipost;
import br.com.intelipost.repository.UserIntelipostRepository;
import br.com.intelipost.security.UserSpringSecurity;

@Service
public class UserSpringSecurityServiceImpl implements UserDetailsService {

	@Autowired
	UserIntelipostRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserIntelipost user = repository.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSpringSecurity(user.getId(), user.getEmail(), user.getPassword());
	}

}
