package sistemaAcademico.daoJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sistemaAcademico.classesBasicas.Endereco;
import sistemaAcademico.classesBasicas.Fone;
import sistemaAcademico.classesBasicas.Pessoa;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class DaoPessoaJDBC implements DaoPessoaIntJDBC {

	@Override
	public List<Pessoa> getListaPessoas() throws SQLException, ClassNotFoundException {

		List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
		Pessoa p;
		Endereco en;
		Fone f;

		DaoConexaoIntJDBC c = new DaoConexaoJDBC();

		try {

			String selectSQL = "SELECT * FROM busca_pessoa";
			PreparedStatement pStmt = c.conectar().prepareStatement(selectSQL);

			ResultSet rs = pStmt.executeQuery(selectSQL);

			while (rs.next()) {

				p = new Pessoa();
				en = new Endereco();
				f = new Fone();

				p.setId(Integer.parseInt(rs.getString(1)));
				p.setNome(rs.getString(2));
				p.setCpf(rs.getString(3));
				p.setSexo(rs.getString(4).charAt(0));

				en.setCep(rs.getString(5));
				en.setLogradouro(rs.getString(6));
				en.setBairro(rs.getString(7));
				en.setNumero(rs.getString(8));
				en.setCidade(rs.getString(9));
				en.setUf(rs.getString(10));
				
				f.setDdd(rs.getString(11));
				f.setFone(rs.getString(12));
				
				en.setId(rs.getInt(13));
				f.setId(rs.getInt(14));
				
				p.setEndereco(en);
				p.addFones(f);
				
				listaPessoas.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			c.desconectar();
		}

		return listaPessoas;
	}

	@SuppressWarnings("finally")
	@Override
	public int addPessoa(Pessoa pessoa) throws SQLException, ClassNotFoundException, MySQLIntegrityConstraintViolationException {

		DaoConexaoIntJDBC c = new DaoConexaoJDBC();

		int id = 0;
		
		String insertSQL;
		PreparedStatement pStmt;
		ResultSet rs;
		
		try {
			
			id = buscaEndereco(pessoa.getEndereco().getCep(), c.conectar());
			
			if (id == 0) {

				insertSQL = "insert into endereco (`Cep`, `Logradouro`, `Bairro`, `Numero`, `Cidade`, `UF`) VALUES (?,?,?,?,?,?)";

				pStmt = c.conectar().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);

				pStmt.setString(1, pessoa.getEndereco().getCep());
				pStmt.setString(2, pessoa.getEndereco().getLogradouro());
				pStmt.setString(3, pessoa.getEndereco().getBairro());
				pStmt.setString(4, pessoa.getEndereco().getNumero());
				pStmt.setString(5, pessoa.getEndereco().getCidade());
				pStmt.setString(6, pessoa.getEndereco().getUf());

				pStmt.executeUpdate();

				rs = pStmt.getGeneratedKeys();

				rs.next();
				id = rs.getInt(1);
				rs.close();
				
			}

			insertSQL = "insert into pessoa (`Nome`, `Sexo`, `CPF`, `IdEndereco`) VALUES (?,?,?,?)";

			pStmt = c.conectar().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);

			pStmt.setString(1, pessoa.getNome());
			pStmt.setString(2, String.valueOf(pessoa.getSexo()));
			pStmt.setString(3, pessoa.getCpf());
			pStmt.setInt(4, id);

			pStmt.executeUpdate();

			rs = pStmt.getGeneratedKeys();
			
			rs.next();
			id = rs.getInt(1);
			rs.close();

			Iterator<Fone> it = pessoa.getFones().iterator();
			
			Fone f;
			
			insertSQL = "INSERT INTO fone (`DDD`, `Fone`, `IdPessoa`) VALUES (?,?,?)";

			pStmt = c.conectar().prepareStatement(insertSQL);

			while (it.hasNext()) {
				
				f = it.next();

				pStmt.setString(1, f.getDdd());
				pStmt.setString(2, f.getFone());
				pStmt.setInt(3, id);
				
				pStmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			c.desconectar();
			return id;
		}
	}

	public void alterarEnderecoPessoa(Pessoa p, Endereco en) throws ClassNotFoundException, SQLException {

		DaoConexaoIntJDBC c = new DaoConexaoJDBC();
		
		try {
			
			String updateSQL = "UPDATE endereco SET Cep = ?, Logradouro = ?, Bairro = ?, Numero = ?, Cidade = ?, UF = ? WHERE IdEndereco = ?";
			
			PreparedStatement pStmt = c.conectar().prepareStatement(updateSQL);

			pStmt.setString(1, en.getCep());
			pStmt.setString(2, en.getLogradouro());
			pStmt.setString(3, en.getBairro());
			pStmt.setString(4, en.getNumero());
			pStmt.setString(5, en.getCidade());
			pStmt.setString(6, en.getUf());
			pStmt.setInt(7, p.getEndereco().getId());
			
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			c.desconectar();
		}

	}

	public void alterarFonePessoa(Pessoa p, Fone f) throws ClassNotFoundException, SQLException{
		
		DaoConexaoIntJDBC c = new DaoConexaoJDBC();
		
		String updateSQL = "UPDATE fone SET DDD = ?, Fone = ? WHERE IdFone = ?";
		
		try {

			PreparedStatement pStmt = c.conectar().prepareStatement(updateSQL);

			pStmt.setString(1, f.getDdd());
			pStmt.setString(2, f.getFone());
			pStmt.setInt(3, p.getFones().get(0).getId());
			
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			c.desconectar();
		}
	}
	
	public boolean verificaSeCadastrado(String cpf) throws ClassNotFoundException, SQLException {
		
		DaoConexaoIntJDBC c = new DaoConexaoJDBC();
		boolean verifica = false;
		
		try {

			Connection cn = c.conectar();

			String selectSQL = "SELECT * FROM pessoa where cpf = '" + cpf + "'";
			PreparedStatement pStmt = cn.prepareStatement(selectSQL);
			
			ResultSet rs = pStmt.executeQuery(selectSQL);
			
			if(rs.next()){
				verifica = rs.getBoolean(1);
			}
			
			cn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			c.desconectar();
		}
		
		return verifica;
	}

	@Override
	public Pessoa buscaPorCpf(String cpf) throws ClassNotFoundException, SQLException {
		
		DaoConexaoIntJDBC c = new DaoConexaoJDBC();
		Pessoa p = new Pessoa();
		Endereco en = new Endereco();
		Fone f = new Fone();

		try {

			String selectSQL = "SELECT * FROM busca_pessoa where cpf = '" + cpf + "'";
			PreparedStatement pStmt = c.conectar().prepareStatement(selectSQL);

			ResultSet rs = pStmt.executeQuery(selectSQL);

			while (rs.next()) {

				p.setId(Integer.parseInt(rs.getString(1)));
				p.setNome(rs.getString(2));
				p.setCpf(rs.getString(3));
				p.setSexo(rs.getString(4).charAt(0));

				en.setCep(rs.getString(5));
				en.setLogradouro(rs.getString(6));
				en.setBairro(rs.getString(7));
				en.setNumero(rs.getString(8));
				en.setCidade(rs.getString(9));
				en.setUf(rs.getString(10));
				
				f.setDdd(rs.getString(11));
				f.setFone(rs.getString(12));
				
				en.setId(rs.getInt(13));
				f.setId(rs.getInt(14));
				
				p.setEndereco(en);
				p.addFones(f);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			c.desconectar();
		}

		return p;
	}
	
	@SuppressWarnings("finally")
	public int buscaEndereco(String cep, Connection cn){
		
		int id = 0;
		
		try {
			
			String selectSQL = "SELECT * FROM endereco where Cep = '" + cep + "'";
			PreparedStatement pStmt = cn.prepareStatement(selectSQL);
			
			ResultSet rs = pStmt.executeQuery(selectSQL);
			
			if(rs.next()){
				id = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return id;
		}
	}

}
