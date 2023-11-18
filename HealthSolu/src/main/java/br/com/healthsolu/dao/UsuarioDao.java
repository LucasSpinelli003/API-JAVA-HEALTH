package br.com.healthsolu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.helthsolu.exception.BadInfoException;
import br.com.healthsolu.model.UsuarioModel;

public class UsuarioDao {
	
	private Connection conn;
	
	public UsuarioDao(Connection conn) {
		super();
		this.conn = conn;
	}

	public void cadastrar(UsuarioModel usuario) throws ClassNotFoundException, SQLException, BadInfoException {
		PreparedStatement stmm = conn.prepareStatement("select * from t_sip_chamado");

		ResultSet resultGet = stmm.executeQuery();

		List<UsuarioModel> lista = new ArrayList<UsuarioModel>();

		while (resultGet.next()) {
			UsuarioModel chamadoGet = parse(resultGet);
			lista.add(chamadoGet);
		}
		
		int id = lista.size() + 1;
		
		PreparedStatement stm = conn.prepareStatement("INSERT INTO" + " T_SIP_CHAMADO (id_chamado, id_segurado,"
				+ "id_endereco_chamado, descricao, data_chamado ) "
				+ "values (?, ?, ?, ?, ?)");

		stm.setInt(1, id);
		stm.setInt(2, chamado.getSegurado().getId());
		stm.setInt(3, chamado.getEnderecoChamado().getId());
		stm.setString(4, chamado.getDescricao());
		stm.setObject(5, chamado.getDataCadastro());
		
		stm.executeUpdate();
	}
	
	private UsuarioModel parse(ResultSet result) throws SQLException {
		int id = result.getInt("id_chamado");
		int idSegurado = result.getInt("id_segurado");
		int idEnderecoChamado = result.getInt("id_endereco_chamado");
		String descricao = result.getString("descricao");
		LocalDateTime dataCadastro = result.getObject("data_chamado", LocalDateTime.class);
		
		UsuarioModel usuario = new UsuarioModel(id,descricao,dataCadastro);
		
		return usuario;
	}
	
	
	
}
