package br.com.healthsolu.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.healthsolu.dao.EnderecoUsuarioDao;
import br.com.healthsolu.dao.UsuarioDao;
import br.com.healthsolu.exception.BadInfoException;
import br.com.healthsolu.exception.IdNotFoundException;
import br.com.healthsolu.factory.ConnectionFactory;
import br.com.healthsolu.model.EnderecoUsuario;
import br.com.healthsolu.model.Usuario;

public class UsuarioService {
	private EnderecoUsuarioDao enderecoUsuarioDao;
	private UsuarioDao loginDao;
	
	public UsuarioService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		loginDao = new UsuarioDao(conn);
		enderecoUsuarioDao = new EnderecoUsuarioDao(conn);
		
		}
	

	public void cadastrar(Usuario login) throws ClassNotFoundException, SQLException, BadInfoException, IdNotFoundException {
		loginDao.cadastrar(login);
	}
	
	public List<Usuario> listar() throws ClassNotFoundException, SQLException, IdNotFoundException{
		List<Usuario> listaLogin = loginDao.listar();
		
		List<Usuario> newList = new ArrayList<Usuario>();
		
		for (Usuario login : listaLogin) {
			if (login.getEnderecoUsuario() != null) {
				EnderecoUsuario enderecoUsuario = enderecoUsuarioDao.pesquisar(login.getEnderecoUsuario().getId());
				login.setEnderecoUsuario(enderecoUsuario);
			}
			
			newList.add(login);
			
		}
		
		
		
		return newList;
	}
	
	public Usuario pesquisar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {
		Usuario lg = loginDao.pesquisar(id);
		
		if (lg.getEnderecoUsuario() != null) {
			EnderecoUsuario enderecoUsuario = enderecoUsuarioDao.pesquisar(lg.getEnderecoUsuario().getId());
			lg.setEnderecoUsuario(enderecoUsuario);
		}
		
		return lg;
	}
	
	}
	
