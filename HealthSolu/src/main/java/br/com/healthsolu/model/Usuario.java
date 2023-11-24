package br.com.healthsolu.model;

import java.time.LocalDateTime;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuario {
	
	private int id;
	private String nome;
	private String email;
	private String senha;
	private double peso;
	private String sexo;
	private double altura;
	private String telefone;
	private LocalDateTime dataNascimento;
	private String objetivo;
	private String fatorAtividade;

	
	public Usuario() {};

	public Usuario(int id, String nome, String email, String senha, double peso, String sexo, double altura,
			String telefone, LocalDateTime dataNascimento, String objetivo, String fatorAtividade) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.peso = peso;
		this.sexo = sexo;
		this.altura = altura;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.objetivo = objetivo;
		this.fatorAtividade = fatorAtividade;
	}


	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getFatorAtividade() {
		return fatorAtividade;
	}
	public void setFatorAtividade(String fatorAtividade) {
		this.fatorAtividade = fatorAtividade;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public LocalDateTime getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDateTime dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
