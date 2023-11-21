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
import br.com.healthsolu.model.Tmb;
import br.com.healthsolu.model.Usuario;


	public class TmbDao {
	
		private Connection conn;
		private UsuarioDao usuarioDao;
		
		
		public TmbDao(Connection conn) {
			super();
			this.conn = conn;
		}

				public void cadastrar(Tmb tmb) throws ClassNotFoundException, SQLException, BadInfoException, IdNotFoundException, GenderNotFoundException {
					PreparedStatement stmm = conn.prepareStatement("select * from t_sip_tmb");

					ResultSet resultGet = stmm.executeQuery();

					List<Tmb> lista = new ArrayList<Tmb>();

					int id = 0;
				
					while (resultGet.next()) {
						Tmb tmbGet = parse(resultGet);
						lista.add(tmbGet);
					}
					if(lista.size() == 0) {
						 id = 1;
					}
					id = lista.size() + 1;
					
					
					
					PreparedStatement stm = conn.prepareStatement("INSERT INTO t_sip_tmb (id_tmb, id_usuario,"
							+ "resultado_tmb,doencas_relacionadas,prevencao_doencas) "
							+ "values (?, ?, ?, ?, ?)");

					stm.setInt(1, id);
					stm.setInt(2, tmb.getUsuario().getId());
					stm.setDouble(3, tmb.getResultadoTmb());
					stm.setString(4, tmb.getDoencasRelacionadas());
					stm.setString(5, tmb.getPrevencaoDeDoencas());
					
					stm.executeUpdate();
				}
				
				private Tmb parse(ResultSet result) throws SQLException {
					int id = result.getInt("id_tmb");
					int id_usuario = result.getInt("id_usuario");
					double resultadoTmb = result.getDouble("resultado_tmb");
					String doencasRelacionadas = result.getString("doencas_relacionadas");
					String prevencaoDeDoencas = result.getString("prevencao_doencas");
					
					Tmb tmb = new Tmb(id,resultadoTmb,doencasRelacionadas,prevencaoDeDoencas);
					
					if (id_usuario != 0) {
						Usuario usuario = new Usuario();
						usuario.setId(id_usuario);
						tmb.setUsuario(usuario);
					}
					
					return tmb;
				}
				
				public List<Tmb> listar() throws ClassNotFoundException, SQLException {

					PreparedStatement stm = conn.prepareStatement("select * from t_sip_tmb");

					ResultSet result = stm.executeQuery();

					List<Tmb> lista = new ArrayList<Tmb>();

					while (result.next()) {
						Tmb tmb = parse(result);
						lista.add(tmb);
					}
					return lista;
				}
				public Tmb pesquisar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

					PreparedStatement stm = conn.prepareStatement("select * from" + " t_sip_tmb where id_tmb = ?");

					stm.setInt(1, id);

					ResultSet result = stm.executeQuery();

					if (!result.next()) {
						throw new IdNotFoundException("Endereco usuario não encontrado");
					}
					Tmb tmb = parse(result);
					return tmb;
				}
				
				
		
	}	

