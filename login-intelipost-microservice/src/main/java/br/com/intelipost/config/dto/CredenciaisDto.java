package br.com.intelipost.config.dto;

import java.io.Serializable;

public class CredenciaisDto implements Serializable {

	private static final long serialVersionUID = 6382318576915646128L;

	private String email;

	private String senha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
