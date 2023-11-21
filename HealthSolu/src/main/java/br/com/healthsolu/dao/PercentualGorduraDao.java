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
import br.com.healthsolu.model.PercentualGordura;
import br.com.healthsolu.model.Usuario;


	public class PercentualGorduraDao {
	
		private Connection conn;
		
		
		public PercentualGorduraDao(Connection conn) {
			super();
			this.conn = conn;
		}

				public void cadastrar(PercentualGordura percentualGordura) throws ClassNotFoundException, SQLException, BadInfoException, IdNotFoundException, GenderNotFoundException {
					PreparedStatement stmm = conn.prepareStatement("select * from t_sip_imc");

					ResultSet resultGet = stmm.executeQuery();

					List<PercentualGordura> lista = new ArrayList<PercentualGordura>();

					int id = 0;
				
					while (resultGet.next()) {
						PercentualGordura percentualGorduraGet = parse(resultGet);
						lista.add(percentualGorduraGet);
					}
					if(lista.size() == 0) {
						 id = 1;
					}
					id = lista.size() + 1;
					
					
					
					PreparedStatement stm = conn.prepareStatement("INSERT INTO t_sip_imc (id_imc, id_usuario,"
							+ "resultado_imc,doencas_relacionadas,prevencao_doencas) "
							+ "values (?, ?, ?, ?, ?)");

					stm.setInt(1, id);
					stm.setInt(2, percentualGordura.getUsuario().getId());
					stm.setDouble(3, percentualGordura.getResultadoPercentual());
					stm.setString(4, percentualGordura.getDoencasRelacionadas());
					stm.setString(5, percentualGordura.getPrevencaoDoencas());
					
					stm.executeUpdate();
				}
				
				private PercentualGordura parse(ResultSet result) throws SQLException {
					int id = result.getInt("id_percentual_gordura");
					int id_usuario = result.getInt("id_usuario");
					int id_imc = result.getInt("id_imc");
					double resultadoPercentual = result.getDouble("resultado_perc_gordura");
					String doencasRelacionadas = result.getString("doencas_relacionadas");
					String prevencaoDoencas = result.getString("prevencaoDoencas");
					
					PercentualGordura percentualGordura = new PercentualGordura(id,resultadoPercentual,doencasRelacionadas,prevencaoDoencas);
					
					if (id_usuario != 0) {
						Usuario usuario = new Usuario();
						usuario.setId(id_usuario);
						percentualGordura.setUsuario(usuario);
					}
					if (id_imc != 0) {
						Imc imc = new Imc();
						imc.setId(id_imc);
						percentualGordura.setImc(imc);
					}
					
					return percentualGordura;
				}
				
				public List<PercentualGordura> listar() throws ClassNotFoundException, SQLException {

					PreparedStatement stm = conn.prepareStatement("select * from t_sip_percentual_gordura");

					ResultSet result = stm.executeQuery();

					List<PercentualGordura> lista = new ArrayList<PercentualGordura>();

					while (result.next()) {
						PercentualGordura percentualGordura = parse(result);
						lista.add(percentualGordura);
					}
					return lista;
				}
				public PercentualGordura pesquisar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

					PreparedStatement stm = conn.prepareStatement("select * from" + " t_sip_percentual_gordura where id_percentual_gordura = ?");

					stm.setInt(1, id);

					ResultSet result = stm.executeQuery();

					if (!result.next()) {
						throw new IdNotFoundException("Imc não encontrado");
					}
					PercentualGordura percentualGordura = parse(result);
					return percentualGordura;
				}
				
				
		
	}	

