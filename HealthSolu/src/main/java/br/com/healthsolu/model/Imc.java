package br.com.healthsolu.model;

import br.com.healthsolu.exception.GrauNotFoundException;

public class Imc {
	
	private int id;
	private Usuario usuario;
	private double resultadoImc;
	private String doencasRelacionadas;
	private String prevencaoDoencas;
	private String classificacao;
	private double estimativaPercGordura;
	
	
	
	//Acima de 40,0. Obesidade. grau III. ...
	//Entre 35,0 e 39,9. Obesidade. grau II. ...
	//Entre 30,0 e 34,9. Obesidade grau I. Sinal de alerta! ...
	//Entre 25,0 e 29,9. Sobrepeso. ...
	//Entre 18,6 e 24,9. Normal. ...
	//18,5 ou menos. Abaixo do normal.
	
	public void calculaDoencas() throws GrauNotFoundException {
		if(classificacao.equalsIgnoreCase("Abaixo do normal")) {
			doencasRelacionadas = "diabetes e hipertireoidismo, entre outros males, assim como pode acarretar distúrbios que chegam até mesmo colocar a vida em risco";
			prevencaoDoencas = "Ganho de massa muscular atráves de uma dieta balanciada, uma rotina de treino, e claro, com um acompanhamento de um médico.";
		}else if(classificacao.equalsIgnoreCase("Normal")){
			doencasRelacionadas = "Saudável";
			prevencaoDoencas = "Saudável";
		}else if(classificacao.equalsIgnoreCase("Sobrepeso")) {
			doencasRelacionadas = "Hipertensão arterial, Aumento do colesterol e triglicérides, Diabetes, Apneia do sono, Acúmulo de gordura no fígado ,Infarto do miocárdio, Acidente vascular cerebral";
			prevencaoDoencas = "Dieta e treino regrados, com enfase na perda de massa gorda, e claro, com um acompanhamento de um médico.";
		}else if(classificacao.equalsIgnoreCase("Obesidade grau 1")) {
			doencasRelacionadas = "Hipertensão, Diabetes. O diabetes é uma doença caracterizada pelo aumento dos níveis de glicose no sangue, Colesterol Hipertrofia Ventricular, Doenças respiratórias. arterial, Aumento do colesterol e triglicérides, Diabetes, Apneia do sono, Acúmulo de gordura no fígado ,Infarto do miocárdio, Acidente vascular cerebral";
			prevencaoDoencas = "Procurar um médico, e tentar manter uma alimentação adequada e a realização de atividades físicas";
		}else if(classificacao.equalsIgnoreCase("Obesidade grau 2")) {
			doencasRelacionadas = "Hipertensão, Diabetes. O diabetes é uma doença caracterizada pelo aumento dos níveis de glicose no sangue, Colesterol Hipertrofia Ventricular, Doenças respiratórias. arterial, Aumento do colesterol e triglicérides, Diabetes, Apneia do sono, Acúmulo de gordura no fígado ,Infarto do miocárdio, Acidente vascular cerebral";
			prevencaoDoencas = "Procurar um médico, e tentar manter uma alimentação adequada e a realização de atividades físicas";
		}else if(classificacao.equalsIgnoreCase("Obesidade grau 2")) {
			doencasRelacionadas = "Hipertensão, Diabetes. O diabetes é uma doença caracterizada pelo aumento dos níveis de glicose no sangue, Colesterol Hipertrofia Ventricular, Doenças respiratórias. arterial, Aumento do colesterol e triglicérides, Diabetes, Apneia do sono, Acúmulo de gordura no fígado ,Infarto do miocárdio, Acidente vascular cerebral";
			prevencaoDoencas = "Procure um médico urgente, é um quadro grave!";
		}else {
			throw new GrauNotFoundException("Grau não encontrado!");
		}
	}
	
	
	public void calculaGrau() throws GrauNotFoundException {
		if(resultadoImc<= 18.5) {
			classificacao = "Abaixo do normal";
		}else if(resultadoImc >= 18.6 && resultadoImc<= 24.9 ) {
			classificacao = "Normal";
		}else if(resultadoImc >= 25.0  && resultadoImc<= 29.9) {
			classificacao = "Sobrepeso";
		}else if(resultadoImc >= 30.0  && resultadoImc<= 34.9) {
			classificacao = "Obesidade grau 1";
		}else if(resultadoImc >= 35.0  && resultadoImc<= 39.9) {
			classificacao = "Obesidade grau 2";
		}else if(resultadoImc>=40) {
			classificacao = "Obesidade grau 3";
		}else{
			throw new GrauNotFoundException("Calculo do Grau incorreto");
		}
	}
	public Imc() {}

	public Imc(int id, double resultadoImc, String doencasRelacionadas, String prevencaoDoencas, String classificacao,
			double estimativaPercGordura) {
		super();
		this.id = id;
		this.resultadoImc = resultadoImc;
		this.doencasRelacionadas = doencasRelacionadas;
		this.prevencaoDoencas = prevencaoDoencas;
		this.classificacao = classificacao;
		this.estimativaPercGordura = estimativaPercGordura;
	}

	public double getEstimativaPercGordura() {
		return estimativaPercGordura;
	}


	public void setEstimativaPercGordura(double estimativaPercGordura) {
		this.estimativaPercGordura = estimativaPercGordura;
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
