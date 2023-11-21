package br.com.healthsolu.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.healthsolu.dao.PercentualGorduraDao;
import br.com.healthsolu.dao.UsuarioDao;
import br.com.healthsolu.exception.BadInfoException;
import br.com.healthsolu.exception.GenderNotFoundException;
import br.com.healthsolu.exception.IdNotFoundException;
import br.com.healthsolu.factory.ConnectionFactory;
import br.com.healthsolu.model.PercentualGordura;
import br.com.healthsolu.model.Usuario;

public class PercentualGorduraService {
	private UsuarioDao usuarioDao;
	private PercentualGorduraDao percentualGorduraDao;
	
	public PercentualGorduraService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		usuarioDao = new UsuarioDao(conn);
		percentualGorduraDao = new PercentualGorduraDao(conn);
		
		}
	

	public void cadastrar(PercentualGordura percentualGordura) throws ClassNotFoundException, SQLException, BadInfoException, IdNotFoundException, GenderNotFoundException {
		
		double resultPercentualGordura = usuarioDao.calculoTmb(percentualGordura.getUsuario().getId());
		percentualGordura.setResultadoPercentual(resultPercentualGordura);

		
		percentualGorduraDao.cadastrar(percentualGordura);
	}
	
	public List<PercentualGordura> listar() throws ClassNotFoundException, SQLException, IdNotFoundException{
		List<PercentualGordura> listaLogin = percentualGorduraDao.listar();
		
		List<PercentualGordura> newList = new ArrayList<PercentualGordura>();
		
		for (PercentualGordura percentualGordura : listaLogin) {
			if (percentualGordura.getUsuario() != null) {
				Usuario usuario = usuarioDao.pesquisar(percentualGordura.getUsuario().getId());
				percentualGordura.setUsuario(usuario);
			}
			
			newList.add(percentualGordura);
			
		}

		return newList;
	}
	
	public PercentualGordura pesquisar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {
		PercentualGordura percentualGordura = percentualGorduraDao.pesquisar(id);
		
		if (percentualGordura.getUsuario() != null) {
			Usuario usuario = usuarioDao.pesquisar(percentualGordura.getUsuario().getId());
			percentualGordura.setUsuario(usuario);
		}
		
		return percentualGordura;
	}
	
	}
	
