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

	
	/* M�todo que busca no banco as pessoas cadastradas 
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
			 * La�o que adiciona o resultado da busca no banco num objeto pessoa
			 */
			
			while (resultSet.next()) {
				/*
				 * Aloca espa�o de mem�ria para pessoa e endereco
				 * cada vez que come�a o la�o
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
				 * Essa parte conta quantos telefones est�o cadastrados por pessoa
				 */
				
				preparedStatement = conexao.conectar().prepareStatement(countSQL);
				preparedStatement.setInt(1, pessoa.getId());

				ResultSet resultSetLinhas = preparedStatement.executeQuery();
				
				/*
				 * Aqui adiciona a quantidade de n�meros encontrados e 
				 * atribui a uma vari�vel
				 */
				
				resultSetLinhas.next();
				quantidadeDeTelefones = resultSetLinhas.getInt(1);
				resultSetLinhas.close();
				
				/*
				 * Outro la�o que adiciona os n�meros de telefone
				 * na mesma pessoa, para que n�o fique uma pessoa
				 * diferente para cada n�mero de telefone cadastrado
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
	 * M�todo que recebe uma pessoa como par�metro, e 
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
			 * Verifica se o endereco da pessoa j� est� cadastrada no banco
			 * Se j� estiver, retorna o ID do endere�o para ser colocado na
			 * chave estrangeira na tabela "pessoa", se n�o estiver ent�o ele
			 * entra no IF e execulta os comandos para inserir o novo
			 * endere�o na tabela 
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
			 * o ID do endere�o que foi cadastrado ou que j� existia
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
			 * Inserindo os fones que est�o na lista de fones no objeto
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
	 * M�todo que altera dados de uma pessoa cadastrada, mais 
	 * especificamente o endere�o, que � passado como par�metro
	 * (non-Javadoc)
	 * @see sistemaAcademico.daoJDBC.DaoPessoaIntJDBC#alterarPessoa(sistemaAcademico.classesBasicas.Endereco)
	 */
	
	public void alterarPessoa(Endereco endereco) throws ClassNotFoundException, SQLException, ConexaoException {

		ConexaoInt conexao = new Conexao();
		
		try {
			
			/*
			 * Apenas executa o comando UPDATE no banco de dados substituindo
			 * dados antigos pelos novos, filtrando pelo ID do endere�o
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
	 * M�todo que altera dados de uma pessoa cadastrada, mais 
	 * especificamente o fone, que � passado como par�metro
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

	/* M�todo que recebe como par�metro uma String contendo
	 * o n�mero de CPF, e busca por esse valor na view busca_pessoa
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
			 * La�o que adiciona o resultado da busca no banco num objeto pessoa
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
				 * Essa parte conta quantos telefones est�o cadastrados por pessoa
				 */
				
				preparedStatement = conexao.conectar().prepareStatement(countSQL);
				preparedStatement.setInt(1, pessoa.getId());

				ResultSet resultSetLinhas = preparedStatement.executeQuery();

				/*
				 * Aqui adiciona a quantidade de n�meros encontrados e 
				 * atribui a uma vari�vel
				 */
				
				resultSetLinhas.next();
				quantidadeDeLinhas = resultSetLinhas.getInt(1);
				resultSetLinhas.close();
				
				/*
				 * Outro la�o que adiciona os n�meros de telefone
				 * na mesma pessoa, para que n�o fique uma pessoa
				 * diferente para cada n�mero de telefone cadastrado
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
	 * M�todo que busca se o endere�o j� existe no banco, � partir
	 * do n�mero do CEP
	 */
	@SuppressWarnings("finally")
	public int buscaEndereco(String cep, Connection connection){
		
		int id = 0;
		
		try {
			
			String selectSQL = "SELECT * FROM endereco where Cep = '" + cep + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet resultSet = preparedStatement.executeQuery(selectSQL);
			
			/*
			 * Atribui o ID do que foi encontrado, a uma vari�vel, 
			 * se n�o foi encontrado nenhum, retorna 0 
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
	 * M�todo que adiciona um n�mero de telefone a uma pessoa que
	 * j� est� no banco de dados, recebendo como par�metro o objeto
	 * fone, e o ID da pessoa que o telefone ser� atribu�do.
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
	 * M�todo que remove o registro de uma pessoa do banco, 
	 * recebendo como par�metro o Id da pessoa, e o Id do endere�o
	 * que est� atribu�do a ela.
	 * (non-Javadoc)
	 * @see sistemaAcademico.daoJDBC.DaoPessoaIntJDBC#removerPessoa(int, int)
	 */
	@Override
	public void removerPessoa(int idPessoa, int idEndereco) throws ClassNotFoundException, SQLException, ConexaoException {
		
		ConexaoInt conexao = new Conexao();

		try {
			
			/*
			 * Abaixo deleta primeiramente o fone usando o ID da pessoa, que � a chave estrangeira.
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
			 * Por ultimo, deleta o endere�o, os registros devem ser
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
