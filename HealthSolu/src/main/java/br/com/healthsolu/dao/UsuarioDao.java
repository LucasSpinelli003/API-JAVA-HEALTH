package br.com.healthsolu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import br.com.healthsolu.exception.BadInfoException;
import br.com.healthsolu.exception.GenderNotFoundException;
import br.com.healthsolu.exception.IdNotFoundException;
import br.com.healthsolu.model.Usuario;

public class UsuarioDao {
	
	private Connection conn;
	
	public UsuarioDao(Connection conn) {
		super();
		this.conn = conn;
	}

	public void cadastrar(Usuario login) throws ClassNotFoundException, SQLException, BadInfoException {
		PreparedStatement stmm = conn.prepareStatement("select * from T_SIP_USUARIO");

		ResultSet resultGet = stmm.executeQuery();

		List<Usuario> lista = new ArrayList<Usuario>();

		int id = 0;
	
		while (resultGet.next()) {
			Usuario loginGet = parse(resultGet);
			lista.add(loginGet);
		}
		if(lista.size() == 0) {
			 id = 1;
		}
		id = lista.size() + 1;
		
		PreparedStatement stm = conn.prepareStatement("INSERT INTO T_SIP_USUARIO (ID_USUARIO,"
				+ " NM_COMPLETO,EMAIL,TELEFONE,SENHA,PESO,ALTURA,GENERO,DATA_NASCIMENTO,OBJETIVO,FATOR_ATIVIDADE)"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		stm.setInt(1, id);
		stm.setString(2, login.getNome());
		stm.setString(3, login.getEmail());
		stm.setString(4, login.getTelefone());
		stm.setString(5, login.getSenha());
		stm.setDouble(6, login.getPeso());
		stm.setDouble(7, login.getAltura());
		stm.setString(8, login.getSexo());
		stm.setObject(9, login.getDataNascimento());
		stm.setString(10, login.getObjetivo());
		stm.setString(11, login.getFatorAtividade());
		
		stm.executeUpdate();
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
	
	public List<Usuario> listar() throws ClassNotFoundException, SQLException {

		PreparedStatement stm = conn.prepareStatement("select * from T_SIP_USUARIO");

		ResultSet result = stm.executeQuery();

		List<Usuario> lista = new ArrayList<Usuario>();

		while (result.next()) {
			Usuario login = parse(result);
			lista.add(login);
		}
		return lista;
	}
	
	public Usuario pesquisar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		PreparedStatement stm = conn.prepareStatement("select * from" + " t_sip_usuario where id_usuario = ?");

		stm.setInt(1, id);

		ResultSet result = stm.executeQuery();

		if (!result.next()) {
			throw new IdNotFoundException("Usuario não encontrado");
		}
		Usuario login = parse(result);
		return login;
	}
	
	public void atualizar(Usuario usuario) throws ClassNotFoundException, SQLException, IdNotFoundException {

		PreparedStatement stm = conn.prepareStatement("update t_sip_usuario set nm_completo = ?, email = ?, telefone = ?, peso= ?, altura = ?  where id_usuario = ?");
		
		stm.setString(1, usuario.getNome());
		stm.setString(2, usuario.getEmail());
		stm.setString(3, usuario.getTelefone());
		stm.setDouble(4, usuario.getPeso());
		stm.setDouble(5, usuario.getAltura());
		stm.setInt(6,usuario.getId());
		
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("Usuario não encontrado para atualizar");
	}
	
	
	public double calculoTmb(int id) throws SQLException, IdNotFoundException, GenderNotFoundException {
		PreparedStatement stm = conn.prepareStatement("select * from" + " t_sip_usuario where id_usuario = ?");
		
		stm.setInt(1, id);

		ResultSet result = stm.executeQuery();

		if (!result.next()) {
			throw new IdNotFoundException("Usuario não encontrado");
		}
		Usuario usuario = parse(result);
		LocalDateTime dataNascimento = usuario.getDataNascimento();

        LocalDate dataAtual = LocalDate.now();

        int idade = Period.between(dataNascimento.toLocalDate(), dataAtual).getYears();
        
      
        if (usuario.getSexo().equalsIgnoreCase("Masculino")) {
            double tmb = 88.362 + (13.397 * usuario.getPeso()) + (4.799 * usuario.getAltura()) - (5.677 * idade);
            return tmb;
        } else if (usuario.getSexo().equalsIgnoreCase("Feminino")) {
            double tmb = 447.593 + (9.247 * usuario.getPeso()) + (3.098 * usuario.getAltura()) - (4.330 * idade);
            return tmb;
        }else {
        	throw new GenderNotFoundException("O sexo pode ser Masculino ou Feminino");
        }
	}
	public double calculoImc(int id) throws SQLException, IdNotFoundException {
		PreparedStatement stm = conn.prepareStatement("select * from" + " t_sip_usuario where id_usuario = ?");
		
		stm.setInt(1, id);

		ResultSet result = stm.executeQuery();

		if (!result.next()) {
			throw new IdNotFoundException("Usuario não encontrado");
		}
		Usuario usuario = parse(result);
      
        double imc = usuario.getPeso()/(usuario.getAltura()*usuario.getAltura());
        
        return imc;
	}
	
	public boolean autenticaLogin(Usuario usuarioCheck) throws SQLException, IdNotFoundException {
		PreparedStatement stm = conn.prepareStatement("select * from" + " t_sip_usuario where email = ?");
		
		stm.setString(1, usuarioCheck.getEmail());

		ResultSet result = stm.executeQuery();

		if (!result.next()) {
			throw new IdNotFoundException("Usuario não encontrado");
		}
		Usuario usuario = parse(result);
		
		if(usuarioCheck.getSenha().equals(usuario.getSenha())) {
			return true;
		}
		return false;
	}

	
	
	
	
}
