package sistemaAcademico.daoJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mysql.jdbc.Statement;

import sistemaAcademico.classesBasicas.Endereco;
import sistemaAcademico.classesBasicas.Fone;
import sistemaAcademico.classesBasicas.Pessoa;
import sistemaAcademico.conexao.Conexao;

public class DaoPessoaJDBC implements DaoPessoaIntJDBC {

	@Override
	public List<Pessoa> getListaPessoas() throws SQLException, ClassNotFoundException {

		List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
		Pessoa p;
		Endereco en;
		Fone f;

		DaoConexaoIntJDBC c = new DaoConexaoJDBC();

		try {

			Connection cn = c.conectar();

			String selectSQL = "SELECT * FROM sistema_academico.busca_pessoa";
			PreparedStatement pStmt = cn.prepareStatement(selectSQL);

			ResultSet rs = pStmt.executeQuery(selectSQL);

			while (rs.next()) {

				p = new Pessoa();
				en = new Endereco();
				f = new Fone();

				p.setId(Integer.parseInt(rs.getString(1)));
				p.setNome(rs.getString(2));
				p.setCpf(rs.getString(3));
				p.setSexo(rs.getString(4).charAt(0));
				
				en.setLogradouro(rs.getString(5));
				en.setBairro(rs.getString(6));
				en.setNumero(rs.getString(7));
				en.setCidade(rs.getString(8));
				en.setUf(rs.getString(9));
				
				p.setEndereco(en);
				
				f.setDdd(rs.getString(10));
				f.setFone(rs.getString(11));
				
				p.addFones(f);
				
				listaPessoas.add(p);
			}

			cn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			c.desconectar();
		}

		return listaPessoas;
	}

	@Override
	public void addPessoa(Pessoa pessoa) throws SQLException, ClassNotFoundException {
		Conexao c = new Conexao();
		try {
			Connection cn = c.conectar();
			int id;
			String insertSQL;
			PreparedStatement pStmt;
			ResultSet rs;
			insertSQL = "insert into `sistema_academico`.`pessoa` (`nome`, `cpf`, `sexo`) VALUES (?,?,?)";
			pStmt = cn.prepareStatement(insertSQL,
					Statement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, pessoa.getNome());
			pStmt.setString(2, pessoa.getCpf());
			pStmt.setString(3, String.valueOf(pessoa.getSexo()));
			pStmt.executeUpdate();
			rs = pStmt.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
			rs.close();
			insertSQL = "insert into `sistema_academico`.`endereco` (`logradouro`, `bairro`, `numero`, `cidade`, "
					+ "`uf`, `id_pessoa` ) VALUES (?,?,?,?,?,?)";

			pStmt = cn.prepareStatement(insertSQL);
			pStmt.setString(1, pessoa.getEndereco().getLogradouro());
			pStmt.setString(2, pessoa.getEndereco().getBairro());
			pStmt.setString(3, pessoa.getEndereco().getNumero());
			pStmt.setString(4, pessoa.getEndereco().getCidade());
			pStmt.setString(5, pessoa.getEndereco().getUf());
			pStmt.setInt(6, id);
			pStmt.executeUpdate();
			Iterator<Fone> it = pessoa.getFones().iterator();
			Fone f;
			insertSQL = "insert into `sistema_academico`.`fone` (`ddd`, `numero`, `id_pessoa`) VALUES (?,?,?)";
			pStmt = cn.prepareStatement(insertSQL);
			while (it.hasNext()) {
				f = it.next();
				pStmt.setString(1, f.getDdd());
				pStmt.setString(2, f.getFone());
				pStmt.setInt(3, id);	
				pStmt.executeUpdate();
			}
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			c.desconectar();
		}
	}

	@Override
	public void removerPessoa(Pessoa p) {

	}

	@Override
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

			Connection cn = c.conectar();

			String selectSQL = "SELECT * FROM busca_pessoa where cpf = '" + cpf + "'";
			PreparedStatement pStmt = cn.prepareStatement(selectSQL);

			ResultSet rs = pStmt.executeQuery(selectSQL);

			while (rs.next()) {

				p.setId(Integer.parseInt(rs.getString(1)));
				p.setNome(rs.getString(2));
				p.setCpf(rs.getString(3));
				p.setSexo(rs.getString(4).charAt(0));
				
				en.setLogradouro(rs.getString(5));
				en.setBairro(rs.getString(6));
				en.setNumero(rs.getString(7));
				en.setCidade(rs.getString(8));
				en.setUf(rs.getString(9));
				
				p.setEndereco(en);
				
				f.setDdd(rs.getString(10));
				f.setFone(rs.getString(11));
				
				p.addFones(f);

			}

			cn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			c.desconectar();
		}

		return p;
	}

}
