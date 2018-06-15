package br.com.intelipost.repository;

import br.com.intelipost.domain.UserIntelipost;

public interface UserIntelipostRepositoryCustom {

	UserIntelipost getUserByEmail(String email);

}
