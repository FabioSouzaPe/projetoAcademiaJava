package sistemaAcademico.daoJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mysql.jdbc.Statement;

import sistemaAcademico.classesBasicas.Fone;
import sistemaAcademico.classesBasicas.Pessoa;

public class DaoPessoaJDBC implements DaoPessoaIntJDBC {

	@Override
	public ArrayList<Pessoa> getListaPessoas() throws SQLException {

		ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();
		Pessoa p;

		DaoConexaoIntJDBC c = new DaoConexaoJDBC();

		try {

			Connection cn = c.conectar();

			String selectSQL = "select id, nome, cpf, sexo from `sistema_academico`.`pessoa`";
			PreparedStatement pStmt = cn.prepareStatement(selectSQL);

			ResultSet rs = pStmt.executeQuery(selectSQL);

			while (rs.next()) {

				p = new Pessoa();

				p.setId(Integer.parseInt(rs.getString(1)));
				p.setNome(rs.getString(2));
				p.setCpf(rs.getString(3));
				p.setSexo(rs.getString(4).charAt(0));

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
	public void addPessoa(Pessoa pessoa) throws SQLException {

		DaoConexaoIntJDBC c = new DaoConexaoJDBC();

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
		} finally {
			c.desconectar();
		}

	}

	@Override
	public void removerPessoa(Pessoa p) {

	}

}
