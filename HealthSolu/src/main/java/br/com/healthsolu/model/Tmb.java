package br.com.healthsolu.model;


public class Tmb {
	
	private int id;
	private Usuario usuario;
	private double resultadoTmb;
	

	
	public Tmb() {}
	
	
	public Tmb(int id, double resultadoTmb) {
		super();
		this.id = id;
		this.resultadoTmb = resultadoTmb;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public double getResultadoTmb() {
		return resultadoTmb;
	}
	public void setResultadoTmb(double resultadoTmb) {
		this.resultadoTmb = resultadoTmb;
	}

	
	

}
