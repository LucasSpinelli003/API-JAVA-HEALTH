package br.com.healthsolu.model;

public class Relatorio {

	
	private int id;
	private Usuario usuario;
	private Imc imc;
	private Tmb tmb;
	
	public Relatorio() {}
	
	public Relatorio(int id) {
		super();
		this.id = id;
	}

	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Imc getImc() {
		return imc;
	}

	public void setImc(Imc imc) {
		this.imc = imc;
	}

	public Tmb getTmb() {
		return tmb;
	}

	public void setTmb(Tmb tmb) {
		this.tmb = tmb;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
