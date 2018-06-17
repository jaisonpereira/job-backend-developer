package br.com.intelipost.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.intelipost.domain.LoginHistoryJwt;

@Repository
public interface LoginHistoryJwtRepository extends CrudRepository<LoginHistoryJwt, String> {

}
