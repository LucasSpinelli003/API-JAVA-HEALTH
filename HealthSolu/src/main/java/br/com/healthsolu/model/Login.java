package br.com.healthsolu.model;

public class Login {

	private String email, senha;
	
	public Login() {}
	
	public Login(String usuario, String senha) {
		super();
		this.email = usuario;
		this.senha = senha;
	}

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
