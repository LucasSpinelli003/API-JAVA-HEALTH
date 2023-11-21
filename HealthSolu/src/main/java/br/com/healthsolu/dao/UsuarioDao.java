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
import br.com.healthsolu.model.EnderecoUsuario;
import br.com.healthsolu.model.Tmb;
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
		
		PreparedStatement stm = conn.prepareStatement("INSERT INTO T_SIP_USUARIO (ID_USUARIO,ID_ENDERECO_USUARIO,"
				+ " NM_COMPLETO,IDADE,EMAIL,TELEFONE,NM_USUARIO,SENHA,PESO,ALTURA,GENERO,DATA_NASCIMENTO) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		stm.setInt(1, id);
 		stm.setInt(2, login.getEnderecoUsuario().getId());
		stm.setString(3, login.getNome());
		stm.setInt(4, login.getIdade());
		stm.setString(5, login.getEmail());
		stm.setString(6, login.getTelefone());
		stm.setString(7, login.getNomeUsuario());
		stm.setString(8, login.getSenha());
		stm.setDouble(9, login.getPeso());
		stm.setDouble(10, login.getAltura());
		stm.setString(11, login.getSexo());
		stm.setObject(12, login.getDataNascimento());
		
		stm.executeUpdate();
	}
	
	private Usuario parse(ResultSet result) throws SQLException {
		int id = result.getInt("ID_USUARIO");
		int idEnderecoUsuario = result.getInt("ID_ENDERECO_USUARIO");
		String nome = result.getString("NM_COMPLETO");
		int idade = result.getInt("IDADE");
		String email = result.getString("EMAIL");
		String telefone = result.getString("TELEFONE");
		String nomeUsuario = result.getString("NM_USUARIO");
		String senha = result.getString("SENHA");
		double peso = result.getDouble("PESO");
		double altura = result.getDouble("ALTURA");
		String sexo = result.getString("GENERO");
		LocalDateTime dataNascimento = result.getObject("DATA_NASCIMENTO", LocalDateTime.class);
		
		Usuario usuario = new Usuario(id,nome,email,senha,idade,peso,sexo,altura,telefone,nomeUsuario,dataNascimento);
		
		if (idEnderecoUsuario != 0) {
			EnderecoUsuario enderecoUsuario = new EnderecoUsuario();
			enderecoUsuario.setId(idEnderecoUsuario);
			usuario.setEnderecoUsuario(enderecoUsuario);
		}
		
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
	
	public double calculoTmb(int id) throws SQLException, IdNotFoundException, GenderNotFoundException {
		PreparedStatement stm = conn.prepareStatement("select * from" + " t_sip_usuario where id_usuario = ?");
		
		stm.setInt(1, id);

		ResultSet result = stm.executeQuery();

		if (!result.next()) {
			throw new IdNotFoundException("Endereco usuario não encontrado");
		}
		Usuario usuario = parse(result);
		LocalDateTime dataNascimento = usuario.getDataNascimento();

        LocalDate dataAtual = LocalDate.now();

        int idade = Period.between(dataNascimento.toLocalDate(), dataAtual).getYears();
        
      
        if (usuario.getSexo().equalsIgnoreCase("M")) {
            double tmb = 88.362 + (13.397 * usuario.getPeso()) + (4.799 * usuario.getAltura()) - (5.677 * idade);
            return tmb;
        } else if (usuario.getSexo().equalsIgnoreCase("F")) {
            double tmb = 447.593 + (9.247 * usuario.getPeso()) + (3.098 * usuario.getAltura()) - (4.330 * idade);
            return tmb;
        }else {
        	throw new GenderNotFoundException("O sexo pode ser M ou F");
        }
	}
	public double calculoImc(int id) throws SQLException, IdNotFoundException {
		PreparedStatement stm = conn.prepareStatement("select * from" + " t_sip_usuario where id_usuario = ?");
		
		stm.setInt(1, id);

		ResultSet result = stm.executeQuery();

		if (!result.next()) {
			throw new IdNotFoundException("Usuário não encontrado");
		}
		Usuario usuario = parse(result);
      
        double imc = usuario.getPeso()/(usuario.getAltura()*usuario.getAltura());
        
        return imc;
	}
	public double calculoPercentualGordura(int id) throws SQLException, IdNotFoundException, GenderNotFoundException {
		PreparedStatement stm = conn.prepareStatement("select * from" + " t_sip_usuario where id_usuario = ?");
		
		stm.setInt(1, id);

		ResultSet result = stm.executeQuery();

		if (!result.next()) {
			throw new IdNotFoundException("Usuario não encontrado");
		}
		Usuario usuario = parse(result);
		LocalDateTime dataNascimento = usuario.getDataNascimento();

        LocalDate dataAtual = LocalDate.now();

        double bT = 0;
        
        int idade = Period.between(dataNascimento.toLocalDate(), dataAtual).getYears();
        
        if(usuario.getSexo().equalsIgnoreCase("M")) {
            bT = (1.20 * calculoImc(usuario.getId())) + (0.23 * idade) - (10.8 * 1) - 5.4;
        } else if(usuario.getSexo().equalsIgnoreCase("F")) {
            bT = (1.20 * calculoImc(usuario.getId())) + (0.23 * idade) - (10.8 * 0) - 5.4;
        } else {
            throw new GenderNotFoundException("O sexo só pode ser 'F' ou 'M'");
        }
        return bT;
	}
	
	
	
	
}
