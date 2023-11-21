package br.com.healthsolu.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.healthsolu.exception.BadInfoException;
import br.com.healthsolu.exception.IdNotFoundException;
import br.com.healthsolu.model.EnderecoUsuario;

public class EnderecoUsuarioDao {
			private Connection conn;
			public EnderecoUsuarioDao(Connection conn) {
				super();
				this.conn = conn;
			}

			public void cadastrar(EnderecoUsuario enderecoUsuario) throws ClassNotFoundException, SQLException, BadInfoException {
				PreparedStatement stmm = conn.prepareStatement("select * from t_sip_endereco_usuario");

				ResultSet resultGet = stmm.executeQuery();

				List<EnderecoUsuario> lista = new ArrayList<EnderecoUsuario>();

				int id = 0;
			
				while (resultGet.next()) {
					EnderecoUsuario enderecoUsuarioGet = parse(resultGet);
					lista.add(enderecoUsuarioGet);
				}
				if(lista.size() == 0) {
					 id = 1;
				}
				id = lista.size() + 1;
				
				PreparedStatement stm = conn.prepareStatement("INSERT INTO t_sip_endereco_usuario (ID_endereco_usuario, cep,"
						+ "logradouro,numero,bairro,sg_estado,nm_cidade) "
						+ "values (?, ?, ?, ?, ?, ?, ?)");

				stm.setInt(1, id);
				stm.setString(2, enderecoUsuario.getCep());
				stm.setString(3, enderecoUsuario.getLogradouro());
				stm.setInt(4, enderecoUsuario.getNumero());
				stm.setString(5, enderecoUsuario.getBairro());
				stm.setString(6, enderecoUsuario.getSiglaEstado());
				stm.setString(7, enderecoUsuario.getNomeCidade());
				
				stm.executeUpdate();
			}
			
			private EnderecoUsuario parse(ResultSet result) throws SQLException {
				int id = result.getInt("ID_endereco_usuario");
				String cep = result.getString("cep");
				String logradouro = result.getString("logradouro");
				int numero = result.getInt("numero");
				String bairro = result.getString("bairro");
				String siglaEstado = result.getString("sg_estado");
				String nomeCidade = result.getString("nm_cidade");
				
				EnderecoUsuario enderecoUsuario = new EnderecoUsuario(id,numero,cep,logradouro,bairro,siglaEstado,nomeCidade);
				return enderecoUsuario;
			}
			
			public List<EnderecoUsuario> listar() throws ClassNotFoundException, SQLException {

				PreparedStatement stm = conn.prepareStatement("select * from t_sip_endereco_usuario");

				ResultSet result = stm.executeQuery();

				List<EnderecoUsuario> lista = new ArrayList<EnderecoUsuario>();

				while (result.next()) {
					EnderecoUsuario enderecoUsuario = parse(result);
					lista.add(enderecoUsuario);
				}
				return lista;
			}
			public EnderecoUsuario pesquisar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

				PreparedStatement stm = conn.prepareStatement("select * from" + " t_sip_endereco_usuario where id_endereco_usuario = ?");

				stm.setInt(1, id);

				ResultSet result = stm.executeQuery();

				if (!result.next()) {
					throw new IdNotFoundException("Endereco usuario não encontrado");
				}
				EnderecoUsuario enderecoUsuario = parse(result);
				return enderecoUsuario;
			}
	
}
