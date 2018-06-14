package br.com.intelipost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.intelipost.domain.UserIntelipost;

@Repository
public interface UserIntelipostRepository extends JpaRepository<UserIntelipost, Long> {

	UserIntelipost findByEmail(String email);

}
