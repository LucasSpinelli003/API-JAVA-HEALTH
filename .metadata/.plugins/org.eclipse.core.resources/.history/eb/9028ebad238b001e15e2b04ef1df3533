package br.com.healthsolu.service;

import java.sql.Connection;
import java.sql.SQLException;
import br.com.healthsolu.dao.LoginDao;
import br.com.healthsolu.exception.BadInfoException;
import br.com.healthsolu.exception.GenderNotFoundException;
import br.com.healthsolu.exception.GrauNotFoundException;
import br.com.healthsolu.exception.IdNotFoundException;
import br.com.healthsolu.factory.ConnectionFactory;
import br.com.healthsolu.model.Usuario;

public class LoginService {
	private LoginDao loginDao;
	
	public LoginService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		loginDao = new LoginDao(conn);
		
		}
	

	public Usuario verificar(String email, String senha) throws ClassNotFoundException, SQLException, BadInfoException, IdNotFoundException, GenderNotFoundException, GrauNotFoundException {
		return loginDao.autenticaLogin(email, senha);
	}
	

	

	
	}
	
