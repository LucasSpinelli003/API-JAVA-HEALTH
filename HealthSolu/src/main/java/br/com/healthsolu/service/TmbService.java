package br.com.healthsolu.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.healthsolu.dao.TmbDao;
import br.com.healthsolu.dao.UsuarioDao;
import br.com.healthsolu.exception.BadInfoException;
import br.com.healthsolu.exception.GenderNotFoundException;
import br.com.healthsolu.exception.IdNotFoundException;
import br.com.healthsolu.factory.ConnectionFactory;
import br.com.healthsolu.model.Tmb;
import br.com.healthsolu.model.Usuario;

public class TmbService {
	private UsuarioDao usuarioDao;
	private TmbDao tmbDao;
	
	public TmbService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		usuarioDao = new UsuarioDao(conn);
		tmbDao = new TmbDao(conn);
		
		}
	

	public void cadastrar(Tmb tmb) throws ClassNotFoundException, SQLException, BadInfoException, IdNotFoundException, GenderNotFoundException {
		
		double resultTmb = usuarioDao.calculoTmb(tmb.getUsuario().getId());
		tmb.setResultadoTmb(resultTmb);

		
		tmbDao.cadastrar(tmb);
	}
	
	public List<Tmb> listar() throws ClassNotFoundException, SQLException, IdNotFoundException{
		List<Tmb> listaLogin = tmbDao.listar();
		
		List<Tmb> newList = new ArrayList<Tmb>();
		
		for (Tmb tmb : listaLogin) {
			if (tmb.getUsuario() != null) {
				Usuario usuario = usuarioDao.pesquisar(tmb.getUsuario().getId());
				tmb.setUsuario(usuario);
			}
			
			newList.add(tmb);
			
		}
		
		
		
		return newList;
	}
	
	public Tmb pesquisar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {
		Tmb tmb = tmbDao.pesquisar(id);
		
		if (tmb.getUsuario() != null) {
			Usuario usuario = usuarioDao.pesquisar(tmb.getUsuario().getId());
			tmb.setUsuario(usuario);
		}
		
		return tmb;
	}
	
	}
	