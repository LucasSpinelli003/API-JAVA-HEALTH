package br.com.healthsolu.model;

public class Dieta {
	
	private int id_dieta_indicada;
	private Usuario usuario;
	private double qtd_carboidratos, qtd_proteinas, qtd_calorias;
	
	
	public Dieta() {}
	
	
	public Dieta(int id_dieta_indicada, double qtd_carboidratos, double qtd_proteinas, double qtd_calorias) {
		super();
		this.id_dieta_indicada = id_dieta_indicada;
		this.qtd_carboidratos = qtd_carboidratos;
		this.qtd_proteinas = qtd_proteinas;
		this.qtd_calorias = qtd_calorias;
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int getId_dieta_indicada() {
		return id_dieta_indicada;
	}
	public void setId_dieta_indicada(int id_dieta_indicada) {
		this.id_dieta_indicada = id_dieta_indicada;
	}
	public double getQtd_carboidratos() {
		return qtd_carboidratos;
	}
	public void setQtd_carboidratos(double qtd_carboidratos) {
		this.qtd_carboidratos = qtd_carboidratos;
	}
	public double getQtd_proteinas() {
		return qtd_proteinas;
	}
	public void setQtd_proteinas(double qtd_proteinas) {
		this.qtd_proteinas = qtd_proteinas;
	}
	public double getQtd_calorias() {
		return qtd_calorias;
	}
	public void setQtd_calorias(double qtd_calorias) {
		this.qtd_calorias = qtd_calorias;
	}
}
