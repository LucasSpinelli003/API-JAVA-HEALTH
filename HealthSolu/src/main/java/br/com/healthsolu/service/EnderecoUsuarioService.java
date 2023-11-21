package br.com.healthsolu.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.healthsolu.dao.EnderecoUsuarioDao;
import br.com.healthsolu.exception.BadInfoException;
import br.com.healthsolu.exception.IdNotFoundException;
import br.com.healthsolu.factory.ConnectionFactory;
import br.com.healthsolu.model.EnderecoUsuario;

public class EnderecoUsuarioService {

			private EnderecoUsuarioDao enderecoUsuarioDao;
			
			public EnderecoUsuarioService() throws ClassNotFoundException, SQLException {
				Connection conn = ConnectionFactory.getConnection();
				enderecoUsuarioDao = new EnderecoUsuarioDao(conn);
				}
			

			public void cadastrar(EnderecoUsuario enderecoUsuario) throws ClassNotFoundException, SQLException, BadInfoException, IdNotFoundException {
				enderecoUsuarioDao.cadastrar(enderecoUsuario);
			}
			
			public List<EnderecoUsuario> listar() throws ClassNotFoundException, SQLException, IdNotFoundException{
				return enderecoUsuarioDao.listar();
			}
			
			public EnderecoUsuario pesquisar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException{
				EnderecoUsuario cU = enderecoUsuarioDao.pesquisar(id);
				return cU;
			}

	
}
