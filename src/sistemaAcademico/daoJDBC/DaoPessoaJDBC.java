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
import sistemaAcademico.conexao.Conexao;
import sistemaAcademico.conexao.ConexaoInt;
import sistemaAcademico.exceptions.ConexaoException;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class DaoPessoaJDBC implements DaoPessoaIntJDBC {

	@Override
	public List<Pessoa> getListaPessoas() throws SQLException, ClassNotFoundException, ConexaoException {

		List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
		Pessoa pessoa;
		Endereco endereco;
		Fone fone;
		int quantidadeDeLinhas;
		
		ConexaoInt conexao = new Conexao();

		try {

			String selectSQL = "SELECT * FROM busca_pessoa";
			String countSQL = "select count(idpessoa) from fone where IdPessoa = ?";
			
			PreparedStatement preparedStatement = conexao.conectar().prepareStatement(selectSQL);

			ResultSet resultSet = preparedStatement.executeQuery(selectSQL);

			while (resultSet.next()) {
				pessoa = new Pessoa();
				endereco = new Endereco();

				pessoa.setId(Integer.parseInt(resultSet.getString(1)));
				pessoa.setNome(resultSet.getString(2));
				pessoa.setCpf(resultSet.getString(3));
				pessoa.setSexo(resultSet.getString(4).charAt(0));

				endereco.setCep(resultSet.getString(5));
				endereco.setLogradouro(resultSet.getString(6));
				endereco.setBairro(resultSet.getString(7));
				endereco.setNumero(resultSet.getString(8));
				endereco.setCidade(resultSet.getString(9));
				endereco.setUf(resultSet.getString(10));
				endereco.setId(resultSet.getInt(13));
				pessoa.setEndereco(endereco);

				
				preparedStatement = conexao.conectar().prepareStatement(countSQL);
				preparedStatement.setInt(1, pessoa.getId());

				ResultSet resultSetLinhas = preparedStatement.executeQuery();

				resultSetLinhas.next();
				quantidadeDeLinhas = resultSetLinhas.getInt(1);
				resultSetLinhas.close();
				
				for (int i = 0; i < quantidadeDeLinhas; i++) {
					fone = new Fone();

					fone.setDdd(resultSet.getString(11));
					fone.setFone(resultSet.getString(12));
					fone.setId(resultSet.getInt(14));
					pessoa.addFones(fone);
					
					if(1 < quantidadeDeLinhas - 1) {
						resultSet.next();
					}
				}
				
				listaPessoas.add(pessoa);

			}
			
			preparedStatement.close();
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}

		return listaPessoas;
	}

	@SuppressWarnings("finally")
	@Override
	public int addPessoa(Pessoa pessoa) throws SQLException, ClassNotFoundException, MySQLIntegrityConstraintViolationException, ConexaoException {

		ConexaoInt conexao = new Conexao();

		int id = 0;
		
		String insertSQL;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		
		try {
			
			id = buscaEndereco(pessoa.getEndereco().getCep(), conexao.conectar());
			
			if (id == 0) {

				insertSQL = "insert into endereco (`Cep`, `Logradouro`, `Bairro`, `Numero`, `Cidade`, `UF`) VALUES (?,?,?,?,?,?)";

				preparedStatement = conexao.conectar().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);

				preparedStatement.setString(1, pessoa.getEndereco().getCep());
				preparedStatement.setString(2, pessoa.getEndereco().getLogradouro());
				preparedStatement.setString(3, pessoa.getEndereco().getBairro());
				preparedStatement.setString(4, pessoa.getEndereco().getNumero());
				preparedStatement.setString(5, pessoa.getEndereco().getCidade());
				preparedStatement.setString(6, pessoa.getEndereco().getUf());

				preparedStatement.executeUpdate();

				resultSet = preparedStatement.getGeneratedKeys();

				resultSet.next();
				id = resultSet.getInt(1);
				resultSet.close();
				
			}

			insertSQL = "insert into pessoa (`Nome`, `Sexo`, `CPF`, `IdEndereco`) VALUES (?,?,?,?)";

			preparedStatement = conexao.conectar().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, pessoa.getNome());
			preparedStatement.setString(2, String.valueOf(pessoa.getSexo()));
			preparedStatement.setString(3, pessoa.getCpf());
			preparedStatement.setInt(4, id);

			preparedStatement.executeUpdate();

			resultSet = preparedStatement.getGeneratedKeys();
			
			resultSet.next();
			id = resultSet.getInt(1);
			resultSet.close();

			Iterator<Fone> iterator = pessoa.getFones().iterator();
			
			Fone fone;
			
			insertSQL = "INSERT INTO fone (`DDD`, `Fone`, `IdPessoa`) VALUES (?,?,?)";

			preparedStatement = conexao.conectar().prepareStatement(insertSQL);

			while (iterator.hasNext()) {
				
				fone = iterator.next();

				preparedStatement.setString(1, fone.getDdd());
				preparedStatement.setString(2, fone.getFone());
				preparedStatement.setInt(3, id);
				
				preparedStatement.executeUpdate();
			}
			
			preparedStatement.close();
			resultSet.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
			return id;
		}
	}

	public void alterarPessoa(Endereco endereco) throws ClassNotFoundException, SQLException, ConexaoException {

		ConexaoInt conexao = new Conexao();
		
		try {
			
			String updateSQL = "UPDATE endereco SET Cep = ?, Logradouro = ?, Bairro = ?, Numero = ?, Cidade = ?, UF = ? WHERE IdEndereco = ?";
			
			PreparedStatement preparedStatement = conexao.conectar().prepareStatement(updateSQL);

			preparedStatement.setString(1, endereco.getCep());
			preparedStatement.setString(2, endereco.getLogradouro());
			preparedStatement.setString(3, endereco.getBairro());
			preparedStatement.setString(4, endereco.getNumero());
			preparedStatement.setString(5, endereco.getCidade());
			preparedStatement.setString(6, endereco.getUf());
			preparedStatement.setInt(7, endereco.getId());
			
			preparedStatement.executeUpdate();
			
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}

	}

	public void alterarPessoa(Fone fone) throws ClassNotFoundException, SQLException, ConexaoException{
		
		ConexaoInt conexao = new Conexao();
		
		String updateSQL = "UPDATE fone SET DDD = ?, Fone = ? WHERE IdFone = ?";
		
		try {

			PreparedStatement preparedStatement = conexao.conectar().prepareStatement(updateSQL);

			preparedStatement.setString(1, fone.getDdd());
			preparedStatement.setString(2, fone.getFone());
			preparedStatement.setInt(3, fone.getId());
			
			preparedStatement.executeUpdate();
			
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
	}

	@Override
	public Pessoa buscaPorCpf(String cpf) throws ClassNotFoundException, SQLException, ConexaoException {
		
		ConexaoInt conexao = new Conexao();
		Pessoa pessoa = new Pessoa();
		Endereco endereco = new Endereco();
		Fone fone;
		int quantidadeDeLinhas;

		try {

			String selectSQL = "SELECT * FROM busca_pessoa where cpf = '" + cpf + "'";
			
			String countSQL = "select count(idpessoa) from fone where IdPessoa = ?";
			
			PreparedStatement preparedStatement = conexao.conectar().prepareStatement(selectSQL);

			ResultSet resultSet = preparedStatement.executeQuery(selectSQL);

			while (resultSet.next()) {

				pessoa.setId(Integer.parseInt(resultSet.getString(1)));
				pessoa.setNome(resultSet.getString(2));
				pessoa.setCpf(resultSet.getString(3));
				pessoa.setSexo(resultSet.getString(4).charAt(0));

				endereco.setCep(resultSet.getString(5));
				endereco.setLogradouro(resultSet.getString(6));
				endereco.setBairro(resultSet.getString(7));
				endereco.setNumero(resultSet.getString(8));
				endereco.setCidade(resultSet.getString(9));
				endereco.setUf(resultSet.getString(10));
				endereco.setId(resultSet.getInt(13));
				pessoa.setEndereco(endereco);

				
				preparedStatement = conexao.conectar().prepareStatement(countSQL);
				preparedStatement.setInt(1, pessoa.getId());

				ResultSet resultSetLinhas = preparedStatement.executeQuery();

				resultSetLinhas.next();
				quantidadeDeLinhas = resultSetLinhas.getInt(1);
				resultSetLinhas.close();
				
				for (int i = 0; i < quantidadeDeLinhas; i++) {
					fone = new Fone();

					fone.setDdd(resultSet.getString(11));
					fone.setFone(resultSet.getString(12));
					fone.setId(resultSet.getInt(14));
					pessoa.addFones(fone);
					
					resultSet.next();
				}
				
				resultSet.close();
				resultSetLinhas.close();
				preparedStatement.close();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}

		return pessoa;
	}
	
	@SuppressWarnings("finally")
	public int buscaEndereco(String cep, Connection connection){
		
		int id = 0;
		
		try {
			
			String selectSQL = "SELECT * FROM endereco where Cep = '" + cep + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet resultSet = preparedStatement.executeQuery(selectSQL);
			
			if(resultSet.next()){
				id = resultSet.getInt(1);
			}

			resultSet.close();
			preparedStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return id;
		}
	}

	@Override
	public void adicionaFone(Fone fone, int idPessoa) throws ClassNotFoundException,
			SQLException, ConexaoException {

		ConexaoInt conexao = new Conexao();

		try {
			String insertSQL = "INSERT INTO fone (`DDD`, `Fone`, `IdPessoa`) VALUES (?,?,?)";
			PreparedStatement preparedStatement = conexao.conectar()
					.prepareStatement(insertSQL);

			preparedStatement.setString(1, fone.getDdd());
			preparedStatement.setString(2, fone.getFone());
			preparedStatement.setInt(3, idPessoa);

			preparedStatement.executeUpdate();
			
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}

	}

	
	@Override
	public void removerPessoa(int idPessoa, int idEndereco) throws ClassNotFoundException, SQLException, ConexaoException {
		
		ConexaoInt conexao = new Conexao();

		try {
			String deleteSQL = "DELETE FROM fone WHERE IdPessoa = " + idPessoa;
			PreparedStatement preparedStatement = conexao.conectar().prepareStatement(deleteSQL);

			preparedStatement.executeUpdate();
			
			
			deleteSQL = "DELETE FROM pessoa WHERE IdPessoa = " + idPessoa;
			preparedStatement = conexao.conectar().prepareStatement(deleteSQL);


			preparedStatement.executeUpdate();
			
			deleteSQL = "DELETE FROM endereco WHERE IdEndereco = " + idEndereco;
			preparedStatement = conexao.conectar().prepareStatement(deleteSQL);


			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}

	}

}
