package br.com.healthsolu.model;

import br.com.healthsolu.exception.ClassificationNotFoundException;

public class Tmb {
	
	private int id;
	private Usuario usuario;
	private double resultadoTmb;
	private String doencasRelacionadas;
	private String prevencaoDeDoencas;
	private String classificacao;
	
	
	
	
	public void calculaClassificacao() throws ClassificationNotFoundException {
		if(resultadoTmb <= 1374 ) {
			classificacao = "Sedentário";
		}else if(resultadoTmb >=1375 && resultadoTmb <=1549) {
			classificacao = "Levemente ativo";
		}else if(resultadoTmb >=1550 && resultadoTmb <=1724) {
			classificacao = "Moderadamente ativo";
		}else if(resultadoTmb >=1725 && resultadoTmb <=1899) {
			classificacao = "Muito ativo";
		}else if(resultadoTmb > 1900) {
			classificacao = "Extremamente ativo";
		}else {
			throw new ClassificationNotFoundException("Classificação falhou");
		}
	}
	
	public Tmb() {}
	
	
	public Tmb(int id, double resultadoTmb, String doencasRelacionadas, String prevencaoDeDoencas,
			String classificacao) {
		super();
		this.id = id;
		this.resultadoTmb = resultadoTmb;
		this.doencasRelacionadas = doencasRelacionadas;
		this.prevencaoDeDoencas = prevencaoDeDoencas;
		this.classificacao = classificacao;
	}

	public String getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
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
	public String getDoencasRelacionadas() {
		return doencasRelacionadas;
	}
	public void setDoencasRelacionadas(String doencasRelacionadas) {
		this.doencasRelacionadas = doencasRelacionadas;
	}
	public String getPrevencaoDeDoencas() {
		return prevencaoDeDoencas;
	}
	public void setPrevencaoDeDoencas(String prevencaoDeDoencas) {
		this.prevencaoDeDoencas = prevencaoDeDoencas;
	}
	
	
	

}
