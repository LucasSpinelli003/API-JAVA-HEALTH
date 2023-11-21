package br.com.healthsolu.model;

public class Imc {
	
	private int id;
	private Usuario usuario;
	private double resultadoImc;
	private String doencasRelacionadas;
	private String prevencaoDoencas;
	
	public Imc() {}
	
	public Imc(int id, double resultadoImc, String doencasRelacionadas, String prevencaoDoencas) {
		super();
		this.id = id;
		this.resultadoImc = resultadoImc;
		this.doencasRelacionadas = doencasRelacionadas;
		this.prevencaoDoencas = prevencaoDoencas;
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
	public double getResultadoImc() {
		return resultadoImc;
	}
	public void setResultadoImc(double resultadoImc) {
		this.resultadoImc = resultadoImc;
	}
	public String getDoencasRelacionadas() {
		return doencasRelacionadas;
	}
	public void setDoencasRelacionadas(String doencasRelacionadas) {
		this.doencasRelacionadas = doencasRelacionadas;
	}
	public String getPrevencaoDoencas() {
		return prevencaoDoencas;
	}
	public void setPrevencaoDoencas(String prevencaoDoencas) {
		this.prevencaoDoencas = prevencaoDoencas;
	}
	
	
	
}
