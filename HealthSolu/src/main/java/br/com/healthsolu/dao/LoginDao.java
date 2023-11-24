package br.com.healthsolu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import br.com.healthsolu.exception.BadInfoException;
import br.com.healthsolu.exception.IdNotFoundException;
import br.com.healthsolu.model.Usuario;

public class LoginDao {

	private Connection conn;
	
	public LoginDao(Connection conn) {
		super();
		this.conn = conn;
	}
	
	private Usuario parse(ResultSet result) throws SQLException {
		int id = result.getInt("ID_USUARIO");
		String nome = result.getString("NM_COMPLETO");
		String email = result.getString("EMAIL");
		String telefone = result.getString("TELEFONE");
		String senha = result.getString("SENHA");
		double peso = result.getDouble("PESO");
		double altura = result.getDouble("ALTURA");
		String sexo = result.getString("GENERO");
		LocalDateTime dataNascimento = result.getObject("DATA_NASCIMENTO", LocalDateTime.class);
		String objetivo = result.getString("OBJETIVO");
		String fatorAtividade = result.getString("FATOR_ATIVIDADE");
		
		Usuario usuario = new Usuario(id,nome,email,senha,peso,sexo,altura,telefone,dataNascimento,objetivo,fatorAtividade);
		
		
		return usuario;
	}
	
	public Usuario autenticaLogin(String email, String senha) throws SQLException, IdNotFoundException, BadInfoException {
		PreparedStatement stm = conn.prepareStatement("select * from" + " t_sip_usuario where email = ?");
		
		stm.setString(1, email);

		ResultSet result = stm.executeQuery();

		if (!result.next()) {
			throw new IdNotFoundException("Usuario não encontrado");
		}
		Usuario usuario = parse(result);
		
		if(senha.equals(usuario.getSenha())) {
			return usuario;
		}else {
			throw new BadInfoException("Usuario não encontrado");
		}
	}
	
}