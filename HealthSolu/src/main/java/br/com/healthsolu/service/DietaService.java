package br.com.healthsolu.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.healthsolu.dao.DietaDao;
import br.com.healthsolu.dao.TmbDao;
import br.com.healthsolu.dao.UsuarioDao;
import br.com.healthsolu.exception.BadInfoException;
import br.com.healthsolu.exception.ClassificationNotFoundException;
import br.com.healthsolu.exception.GenderNotFoundException;
import br.com.healthsolu.exception.IdNotFoundException;
import br.com.healthsolu.factory.ConnectionFactory;
import br.com.healthsolu.model.Dieta;
import br.com.healthsolu.model.Tmb;
import br.com.healthsolu.model.Usuario;

public class DietaService {
	private UsuarioDao usuarioDao;
	private DietaDao dietaDao;
	
	public DietaService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		usuarioDao = new UsuarioDao(conn);
		dietaDao = new DietaDao(conn);
		
		}
	

	public void cadastrar(Dieta dieta) throws ClassNotFoundException, SQLException, BadInfoException, IdNotFoundException, GenderNotFoundException, ClassificationNotFoundException {
		
		double resultTmb = usuarioDao.calculoTmb(dieta.getUsuario().getId());
		dieta.setResultadoTmb(resultTmb);

		
		dietaDao.cadastrar(dieta);
	}
	
	public List<Dieta> listar() throws ClassNotFoundException, SQLException, IdNotFoundException{
		List<Dieta> listaDieta = dietaDao.listar();
		
		List<Dieta> newList = new ArrayList<Dieta>();
		
		for (Dieta dieta : listaDieta) {
			if (dieta.getUsuario() != null) {
				Usuario usuario = usuarioDao.pesquisar(dieta.getUsuario().getId());
				dieta.setUsuario(usuario);
			}
			
			newList.add(dieta);
			
		}
		
		
		
		return newList;
	}
	
	public Dieta pesquisar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {
		Dieta dieta = dietaDao.pesquisar(id);
		
		if (dieta.getUsuario() != null) {
			Usuario usuario = usuarioDao.pesquisar(dieta.getUsuario().getId());
			dieta.setUsuario(usuario);
		}
		
		return dieta;
	}
	
	}
	
