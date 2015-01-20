package sistemaAcademico.daoJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import sistemaAcademico.classesBasicas.Pessoa;

public class DaoPessoaJDBC implements DaoPessoaIntJDBC {

	@Override
	public ArrayList<Pessoa> getListaPessoas() {
		
		return null;
	}

	@Override
	public void addPessoa(Pessoa pessoa) {
		
		Connection cn = DaoConexaoJDBC.conectar();
		
		try {
			String insert = "insert into `sistemaacademico`.`pessoa` (`nome`, `cpf`, `sexo`) VALUES (?,?,?)";
			PreparedStatement pStmt = cn.prepareStatement(insert);
			
			pStmt.setString(1,pessoa.getNome());
			pStmt.setString(2,pessoa.getCpf());
			pStmt.setLong(3,pessoa.getSexo());
			
			pStmt.executeUpdate();
			cn.commit();
			
			cn.close();
			DaoConexaoJDBC.desconectar();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removerPessoa(Pessoa p) {
		
	}

}
