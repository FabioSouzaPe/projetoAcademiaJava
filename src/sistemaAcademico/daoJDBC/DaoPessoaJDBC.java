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

	
	/* Método que busca no banco as pessoas cadastradas 
	 * e adiciona o que encontrar numa lista
	 * (non-Javadoc)
	 * @see sistemaAcademico.daoJDBC.DaoPessoaIntJDBC#getListaPessoas()
	 */
	
	@Override
	public List<Pessoa> getListaPessoas() throws SQLException, ClassNotFoundException, ConexaoException {
		
		
		List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
		Pessoa pessoa;
		Endereco endereco;
		Fone fone;
		int quantidadeDeTelefones;
		
		ConexaoInt conexao = new Conexao();

		try {
			/*
			 * Strings com os comandos SQL
			 */
			String selectSQL = "SELECT * FROM busca_pessoa";
			String countSQL = "select count(idpessoa) from fone where IdPessoa = ?";
			
			/*
			 * Conectando no banco
			 */
			
			PreparedStatement preparedStatement = conexao.conectar().prepareStatement(selectSQL);

			ResultSet resultSet = preparedStatement.executeQuery(selectSQL);

			/*
			 * Laço que adiciona o resultado da busca no banco num objeto pessoa
			 */
			
			while (resultSet.next()) {
				/*
				 * Aloca espaço de memória para pessoa e endereco
				 * cada vez que começa o laço
				 */
				
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

				
				/*
				 * Essa parte conta quantos telefones estão cadastrados por pessoa
				 */
				
				preparedStatement = conexao.conectar().prepareStatement(countSQL);
				preparedStatement.setInt(1, pessoa.getId());

				ResultSet resultSetLinhas = preparedStatement.executeQuery();
				
				/*
				 * Aqui adiciona a quantidade de números encontrados e 
				 * atribui a uma variável
				 */
				
				resultSetLinhas.next();
				quantidadeDeTelefones = resultSetLinhas.getInt(1);
				resultSetLinhas.close();
				
				/*
				 * Outro laço que adiciona os números de telefone
				 * na mesma pessoa, para que não fique uma pessoa
				 * diferente para cada número de telefone cadastrado
				 */
				
				for (int i = 0; i < quantidadeDeTelefones; i++) {
					fone = new Fone();

					fone.setDdd(resultSet.getString(11));
					fone.setFone(resultSet.getString(12));
					fone.setId(resultSet.getInt(14));
					pessoa.addFones(fone);
					
					if(1 < quantidadeDeTelefones - 1) {
						resultSet.next();
					}
				}
				
				/*
				 * Por fim adiciona essa pessoa a lista
				 */
				
				listaPessoas.add(pessoa);

			}
			
			preparedStatement.close();
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConexaoException e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}

		return listaPessoas;
	}

	/*
	 * Método que recebe uma pessoa como parâmetro, e 
	 * adiciona essa pessoa no banco
	 * (non-Javadoc)
	 * @see sistemaAcademico.daoJDBC.DaoPessoaIntJDBC#addPessoa(sistemaAcademico.classesBasicas.Pessoa)
	 */
	
	
	public int addPessoa(Pessoa pessoa) throws SQLException, ClassNotFoundException, MySQLIntegrityConstraintViolationException, ConexaoException {

		ConexaoInt conexao = new Conexao();

		int id = 0;
		
		String insertSQL;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		
		try {
			
			/*
			 * Verifica se o endereco da pessoa já está cadastrada no banco
			 * Se já estiver, retorna o ID do endereço para ser colocado na
			 * chave estrangeira na tabela "pessoa", se não estiver então ele
			 * entra no IF e execulta os comandos para inserir o novo
			 * endereço na tabela 
			 */
			
			id = buscaEndereco(pessoa.getEndereco().getCep(), conexao.conectar());
			
			if (id == 0) {

				insertSQL = "insert into endereco (`Cep`, `Logradouro`, `Bairro`, `Numero`, `Cidade`, `UF`) VALUES (?,?,?,?,?,?)";

				preparedStatement = conexao.conectar().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);

				preparedStatement.setString(1, pessoa.getEndereco().getCep().replaceAll("[^0-9]", ""));
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
			
			/*
			 * Em seguida cadastra a pessoa no banco, inserindo
			 * o ID do endereço que foi cadastrado ou que já existia
			 */

			insertSQL = "insert into pessoa (`Nome`, `Sexo`, `CPF`, `IdEndereco`) VALUES (?,?,?,?)";

			preparedStatement = conexao.conectar().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, pessoa.getNome());
			preparedStatement.setString(2, String.valueOf(pessoa.getSexo()));
			preparedStatement.setString(3, pessoa.getCpf().replaceAll("[^0-9]", ""));
			preparedStatement.setInt(4, id);

			preparedStatement.executeUpdate();

			resultSet = preparedStatement.getGeneratedKeys();
			
			resultSet.next();
			id = resultSet.getInt(1);
			resultSet.close();
			
			/*
			 * Inserindo os fones que estão na lista de fones no objeto
			 * pessoa, criando um iterator e percorrendo a lista enquanto
			 * adiciona os elementos da lista no banco.
			 */

			Iterator<Fone> iterator = pessoa.getFones().iterator();
			
			Fone fone;
			
			insertSQL = "INSERT INTO fone (`DDD`, `Fone`, `IdPessoa`) VALUES (?,?,?)";

			preparedStatement = conexao.conectar().prepareStatement(insertSQL);

			while (iterator.hasNext()) {
				
				fone = iterator.next();

				preparedStatement.setString(1, fone.getDdd().replaceAll("[^0-9]", ""));
				preparedStatement.setString(2, fone.getFone().replaceAll("[^0-9]", ""));
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
	
	/*	
	 * Método que altera dados de uma pessoa cadastrada, mais 
	 * especificamente o endereço, que é passado como parâmetro
	 * (non-Javadoc)
	 * @see sistemaAcademico.daoJDBC.DaoPessoaIntJDBC#alterarPessoa(sistemaAcademico.classesBasicas.Endereco)
	 */
	
	public void alterarPessoa(Endereco endereco) throws ClassNotFoundException, SQLException, ConexaoException {

		ConexaoInt conexao = new Conexao();
		
		try {
			
			/*
			 * Apenas executa o comando UPDATE no banco de dados substituindo
			 * dados antigos pelos novos, filtrando pelo ID do endereço
			 */
			
			String updateSQL = "UPDATE endereco SET Cep = ?, Logradouro = ?, Bairro = ?, Numero = ?, Cidade = ?, UF = ? WHERE IdEndereco = ?";
			
			PreparedStatement preparedStatement = conexao.conectar().prepareStatement(updateSQL);

			preparedStatement.setString(1, endereco.getCep().replaceAll("[^0-9]", ""));
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
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}

	}
	
	/*	
	 * Método que altera dados de uma pessoa cadastrada, mais 
	 * especificamente o fone, que é passado como parâmetro
	 * @see sistemaAcademico.daoJDBC.DaoPessoaIntJDBC#alterarPessoa(sistemaAcademico.classesBasicas.Fone)
	 */
	public void alterarPessoa(Fone fone) throws ClassNotFoundException, SQLException, ConexaoException{
		
		ConexaoInt conexao = new Conexao();
		
		String updateSQL = "UPDATE fone SET DDD = ?, Fone = ? WHERE IdFone = ?";
		
		try {
			
			/*
			 * Apenas executa o comando UPDATE no banco de dados substituindo
			 * dados antigos pelos novos, filtrando pelo ID do fone
			 */

			PreparedStatement preparedStatement = conexao.conectar().prepareStatement(updateSQL);

			preparedStatement.setString(1, fone.getDdd().replaceAll("[^0-9]", ""));
			preparedStatement.setString(2, fone.getFone().replaceAll("[^0-9]", ""));
			preparedStatement.setInt(3, fone.getId());
			
			preparedStatement.executeUpdate();
			
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConexaoException e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
	}

	/* Método que recebe como parâmetro uma String contendo
	 * o número de CPF, e busca por esse valor na view busca_pessoa
	 * (non-Javadoc)
	 * @see sistemaAcademico.daoJDBC.DaoPessoaIntJDBC#buscaPorCpf(java.lang.String)
	 */
	
	@Override
	public Pessoa buscaPorCpf(String cpf) throws ClassNotFoundException, SQLException, ConexaoException {
		
		ConexaoInt conexao = new Conexao();
		Pessoa pessoa = new Pessoa();
		Endereco endereco = new Endereco();
		Fone fone;
		int quantidadeDeLinhas;

		try {

			String selectSQL = "SELECT * FROM busca_pessoa where cpf = '" + cpf/*.replaceAll("[^0-9]", "")*/ + "'";
			
			String countSQL = "select count(idpessoa) from fone where IdPessoa = ?";
			
			PreparedStatement preparedStatement = conexao.conectar().prepareStatement(selectSQL);

			ResultSet resultSet = preparedStatement.executeQuery(selectSQL);
			
			/*
			 * Laço que adiciona o resultado da busca no banco num objeto pessoa
			 */

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
				
				/*
				 * Essa parte conta quantos telefones estão cadastrados por pessoa
				 */
				
				preparedStatement = conexao.conectar().prepareStatement(countSQL);
				preparedStatement.setInt(1, pessoa.getId());

				ResultSet resultSetLinhas = preparedStatement.executeQuery();

				/*
				 * Aqui adiciona a quantidade de números encontrados e 
				 * atribui a uma variável
				 */
				
				resultSetLinhas.next();
				quantidadeDeLinhas = resultSetLinhas.getInt(1);
				resultSetLinhas.close();
				
				/*
				 * Outro laço que adiciona os números de telefone
				 * na mesma pessoa, para que não fique uma pessoa
				 * diferente para cada número de telefone cadastrado
				 */
				
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
			}
			
			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConexaoException e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}

		return pessoa;
	}
	
	
	/*
	 * Método que busca se o endereço já existe no banco, à partir
	 * do número do CEP
	 */
	@SuppressWarnings("finally")
	public int buscaEndereco(String cep, Connection connection){
		
		int id = 0;
		
		try {
			
			String selectSQL = "SELECT * FROM endereco where Cep = '" + cep + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet resultSet = preparedStatement.executeQuery(selectSQL);
			
			/*
			 * Atribui o ID do que foi encontrado, a uma variável, 
			 * se não foi encontrado nenhum, retorna 0 
			 */
			
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
	
	/*
	 * Método que adiciona um número de telefone a uma pessoa que
	 * já está no banco de dados, recebendo como parâmetro o objeto
	 * fone, e o ID da pessoa que o telefone será atribuído.
	 * (non-Javadoc)
	 * @see sistemaAcademico.daoJDBC.DaoPessoaIntJDBC#adicionaFone(sistemaAcademico.classesBasicas.Fone, int)
	 */
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
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}

	}

	
	/*
	 * Método que remove o registro de uma pessoa do banco, 
	 * recebendo como parâmetro o Id da pessoa, e o Id do endereço
	 * que está atribuído a ela.
	 * (non-Javadoc)
	 * @see sistemaAcademico.daoJDBC.DaoPessoaIntJDBC#removerPessoa(int, int)
	 */
	@Override
	public void removerPessoa(int idPessoa, int idEndereco) throws ClassNotFoundException, SQLException, ConexaoException {
		
		ConexaoInt conexao = new Conexao();

		try {
			
			/*
			 * Abaixo deleta primeiramente o fone usando o ID da pessoa, que é a chave estrangeira.
			 */
			String deleteSQL = "DELETE FROM fone WHERE IdPessoa = " + idPessoa;
			PreparedStatement preparedStatement = conexao.conectar().prepareStatement(deleteSQL);

			preparedStatement.executeUpdate();
			
			/*
			 * Depois deleta a pessoa dos registros pelo ID
			 */
			
			deleteSQL = "DELETE FROM pessoa WHERE IdPessoa = " + idPessoa;
			preparedStatement = conexao.conectar().prepareStatement(deleteSQL);

			preparedStatement.executeUpdate();
			
			/*
			 * Por ultimo, deleta o endereço, os registros devem ser
			 * excluidos nessa ordem por conta das chaves estrangeiras.
			 */
			
			deleteSQL = "DELETE FROM endereco WHERE IdEndereco = " + idEndereco;
			preparedStatement = conexao.conectar().prepareStatement(deleteSQL);

			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConexaoException e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}

	}

}
