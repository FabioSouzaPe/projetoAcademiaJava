package sistemaAcademico.dao.JDBC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistemaAcademico.classesBasicas.Curso;
import sistemaAcademico.classesBasicas.Turma;

public class DaoCursoJDBC implements DaoCursoJDBCInt{

	
	
	@Override
	public ArrayList<Curso> consultarTudo() {
		
		return null;
	}

	@Override
	public List<Turma> consultarTurmas() {
		
		return null;
	}

	@Override
	public boolean cadastrar(Curso curso) throws ClassNotFoundException, SQLException {
		
			boolean sucesso=false;
			Connection conexao =  DaoConexaoJDBC.abrirConexao();
			
			PreparedStatement pStmt = conexao.prepareStatement("INSERT INTO CURSO ( NOME,DATA,TURMA) VALUES (?,?,?)") ;
			pStmt.setString(1,curso.getNome());
			pStmt.setDate(2,(Date) curso.getData());
			pStmt.setInt(3,0);
			
			int rows = pStmt.executeUpdate();
			conexao.commit();
			DaoConexaoJDBC.fecharConexao();
			if(rows==1){
				sucesso=true;
			}
			
		return sucesso;
	}

	@Override
	public boolean excluir() {
		
		return false;
	}

	@Override
	public boolean alterar() {

		return false;
		
	}

}
