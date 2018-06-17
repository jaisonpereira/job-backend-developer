package br.com.intelipost.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * @author jpereira Armazena historico de logins realizados no sistema ,
 *         disponibilizando acesso a outros modulos
 */
@RedisHash("LoginHistoryJwt")
public class LoginHistoryJwt implements Serializable {

	private static final long serialVersionUID = -718310653955686096L;

	@Id
	private String id;

	private String jwt;

	private String email;

	private Long expirationTime;

	private Date generationDate;

	public LoginHistoryJwt(String jwt, String email, Long expirationTime, Date generationDate) {
		this.jwt = jwt;
		this.email = email;
		this.expirationTime = expirationTime;
		this.generationDate = generationDate;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getGenerationDate() {
		return generationDate;
	}

	public void setGenerationDate(Date generationDate) {
		this.generationDate = generationDate;
	}

	public Long getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Long expirationTime) {
		this.expirationTime = expirationTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}