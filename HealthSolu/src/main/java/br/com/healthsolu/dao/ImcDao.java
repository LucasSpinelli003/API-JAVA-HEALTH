package br.com.healthsolu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.healthsolu.exception.BadInfoException;
import br.com.healthsolu.exception.GenderNotFoundException;
import br.com.healthsolu.exception.IdNotFoundException;
import br.com.healthsolu.model.Imc;
import br.com.healthsolu.model.Usuario;


	public class ImcDao {
	
		private Connection conn;
		
		
		public ImcDao(Connection conn) {
			super();
			this.conn = conn;
		}

				public void cadastrar(Imc imc) throws ClassNotFoundException, SQLException, BadInfoException, IdNotFoundException, GenderNotFoundException {
					PreparedStatement stmm = conn.prepareStatement("select * from t_sip_imc");

					ResultSet resultGet = stmm.executeQuery();

					List<Imc> lista = new ArrayList<Imc>();

					int id = 0;
				
					while (resultGet.next()) {
						Imc imcGet = parse(resultGet);
						lista.add(imcGet);
					}
					if(lista.size() == 0) {
						 id = 1;
					}
					id = lista.size() + 1;
					
					
					
					PreparedStatement stm = conn.prepareStatement("INSERT INTO t_sip_imc (id_imc, id_usuario,"
							+ "resultado_imc,doencas_relacionadas,prevencao_doencas) "
							+ "values (?, ?, ?, ?, ?)");

					stm.setInt(1, id);
					stm.setInt(2, imc.getUsuario().getId());
					stm.setDouble(3, imc.getResultadoImc());
					stm.setString(4, imc.getDoencasRelacionadas());
					stm.setString(5, imc.getPrevencaoDoencas());
					
					stm.executeUpdate();
				}
				
				private Imc parse(ResultSet result) throws SQLException {
					int id = result.getInt("id_imc");
					int id_usuario = result.getInt("id_usuario");
					double resultadoImc = result.getDouble("resultado_imc");
					String doencasRelacionadas = result.getString("doencas_relacionadas");
					String prevencaoDeDoencas = result.getString("prevencao_doencas");
					
					Imc imc = new Imc(id,resultadoImc,doencasRelacionadas,prevencaoDeDoencas);
					
					if (id_usuario != 0) {
						Usuario usuario = new Usuario();
						usuario.setId(id_usuario);
						imc.setUsuario(usuario);
					}
					
					return imc;
				}
				
				public List<Imc> listar() throws ClassNotFoundException, SQLException {

					PreparedStatement stm = conn.prepareStatement("select * from t_sip_imc");

					ResultSet result = stm.executeQuery();

					List<Imc> lista = new ArrayList<Imc>();

					while (result.next()) {
						Imc imc = parse(result);
						lista.add(imc);
					}
					return lista;
				}
				public Imc pesquisar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

					PreparedStatement stm = conn.prepareStatement("select * from" + " t_sip_imc where id_imc = ?");

					stm.setInt(1, id);

					ResultSet result = stm.executeQuery();

					if (!result.next()) {
						throw new IdNotFoundException("Imc não encontrado");
					}
					Imc imc = parse(result);
					return imc;
				}
				
				
		
	}	

