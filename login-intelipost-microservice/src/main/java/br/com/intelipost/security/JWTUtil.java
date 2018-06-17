package br.com.intelipost.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.intelipost.domain.LoginHistoryJwt;
import br.com.intelipost.repository.LoginHistoryJwtRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Autowired
	LoginHistoryJwtRepository repositoryLogin;

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String generateToken(String email) {
		String token = Jwts.builder().setSubject(email).setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
		repositoryLogin.save(new LoginHistoryJwt(token, email, this.expiration, new Date()));
		return token;
	}

}
