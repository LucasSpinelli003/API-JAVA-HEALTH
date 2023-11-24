package br.com.healthsolu.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.healthsolu.dao.PercentualGorduraDao;
import br.com.healthsolu.dao.UsuarioDao;
import br.com.healthsolu.dao.ImcDao;
import br.com.healthsolu.exception.BadInfoException;
import br.com.healthsolu.exception.GenderNotFoundException;
import br.com.healthsolu.exception.IdNotFoundException;
import br.com.healthsolu.factory.ConnectionFactory;
import br.com.healthsolu.model.PercentualGordura;
import br.com.healthsolu.model.Imc;
import br.com.healthsolu.model.Usuario;


public class PercentualGorduraService {
	private PercentualGorduraDao percentualGorduraDao;
	private ImcDao imcDao;
	private UsuarioDao usuarioDao;
	
	public PercentualGorduraService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		percentualGorduraDao = new PercentualGorduraDao(conn);
		imcDao = new ImcDao(conn);
		usuarioDao = new UsuarioDao(conn);
		
		}
	

	public void cadastrar(PercentualGordura percentualGordura) throws ClassNotFoundException, SQLException, BadInfoException, IdNotFoundException, GenderNotFoundException {
		double resultPercentualGordura = imcDao.calculoPercentualGordura(percentualGordura.getImc().getId());
		percentualGordura.setResultadoPercentual(resultPercentualGordura);

		
		percentualGorduraDao.cadastrar(percentualGordura);
	}
	
	public List<PercentualGordura> listar() throws ClassNotFoundException, SQLException, IdNotFoundException{
		List<PercentualGordura> listaPercentual = percentualGorduraDao.listar();
		
		List<PercentualGordura> newList = new ArrayList<PercentualGordura>();
		
		for (PercentualGordura percentualGordura : listaPercentual) {
			if (percentualGordura.getImc() != null) {
				Imc imc = imcDao.pesquisar(percentualGordura.getImc().getId());
				percentualGordura.setImc(imc);
			}
			if(percentualGordura.getImc().getUsuario() != null) {
				Usuario usuario = usuarioDao.pesquisar(percentualGordura.getImc().getUsuario().getId());
				percentualGordura.getImc().setUsuario(usuario);
			}
			
			newList.add(percentualGordura);
			
		}

		return newList;
	}
	
	public PercentualGordura pesquisar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {
		PercentualGordura percentualGordura = percentualGorduraDao.pesquisar(id);
		
		if (percentualGordura.getImc() != null) {
			Imc imc = imcDao.pesquisar(percentualGordura.getImc().getId());
			percentualGordura.setImc(imc);
		}
		
		return percentualGordura;
	}
	
	}
	
