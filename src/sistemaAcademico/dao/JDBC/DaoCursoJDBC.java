package sistemaAcademico.dao.JDBC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import sistemaAcademico.classesBasicas.Curso;
import sistemaAcademico.classesBasicas.Turma;

public class DaoCursoJDBC implements DaoCursoJDBCInt{

	
	
	@Override
	public ResultSet consultar(String select) throws ClassNotFoundException, SQLException {
		
		Connection conexao =  DaoConexaoJDBC.abrirConexao();
		PreparedStatement pStmt = conexao.prepareStatement(select) ;
		ResultSet rs = pStmt.executeQuery();
		conexao.commit();
		return rs;
	}

	@Override
	public List<Turma> consultarTurmas() {
		
		return null;
	}

	@Override
	public boolean cadastrar(Curso curso) throws ClassNotFoundException, SQLException {
		
			boolean sucesso=false;
			Connection conexao =  DaoConexaoJDBC.abrirConexao();
			java.util.Date dataUtil = curso.getData(); 
			java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
			
			
			PreparedStatement pStmt = conexao.prepareStatement("INSERT INTO CURSO ( NOME,DATA,TURMA) VALUES (?,?,?)") ;
			pStmt.setString(1,curso.getNome());
			pStmt.setDate(2,dataSql);
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
	public boolean excluir(String cursoNome) throws ClassNotFoundException, SQLException {
		
		boolean sucesso=false;
		Connection conexao =  DaoConexaoJDBC.abrirConexao();
		
		PreparedStatement pStmt = conexao.prepareStatement("DELETE FROM CURSO WHERE NOME=?");
		pStmt.setString(1,cursoNome);
		
		int rows = pStmt.executeUpdate();
		conexao.commit();
		DaoConexaoJDBC.fecharConexao();
		if(rows==1){
			sucesso=true;
		}
		
	return sucesso;
	}

	@Override
	public boolean alterar(String cursoNomeOld, String cursoNomeNew) throws ClassNotFoundException, SQLException {

		boolean sucesso=false;
		Connection conexao =  DaoConexaoJDBC.abrirConexao();
		
		PreparedStatement pStmt = conexao.prepareStatement("UPDATE  CURSO SET NOME=? WHERE NOME=?");
		pStmt.setString(1,cursoNomeNew);
		pStmt.setString(2,cursoNomeOld);
		
		int rows = pStmt.executeUpdate();
		conexao.commit();
		DaoConexaoJDBC.fecharConexao();
		if(rows==1){
			sucesso=true;
		}
		
	return sucesso;
		
	}

}
