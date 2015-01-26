package sistemaAcademico.daoJDBC;

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
import sistemaAcademico.conexao.Conexao;
import sistemaAcademico.exceptions.ConexaoException;

public class DaoCursoJDBC implements DaoCursoJDBCInt{

	Conexao conexao = new Conexao();
	PreparedStatement pStmt;
	@Override
	public ResultSet consultar(String select) throws ClassNotFoundException, SQLException {
		
	
		ResultSet rs = null;
		try {
			pStmt = conexao.conectar().prepareStatement(select);
			rs = pStmt.executeQuery();
		} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return rs;
	}

	@Override
	public List<Turma> consultarTurmas() {
		
		return null;
	}

	@Override
	public boolean cadastrar(Curso curso) throws ClassNotFoundException, SQLException {
		
			boolean sucesso=false;
			pStmt = conexao.conectar().prepareStatement(select);
			java.util.Date dataUtil = curso.getData(); 
			java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
			
			
			PreparedStatement pStmt = conexao.prepareStatement("INSERT INTO CURSO ( NOME,DATA) VALUES (?,?)") ;
			pStmt.setString(1,curso.getNome());
			pStmt.setDate(2,dataSql);
			
			int rows = pStmt.executeUpdate();
			DaoConexaoJDBC.fecharConexao();
			if(rows==1){
				sucesso=true;
			}
			
		return sucesso;
	}

	@Override
	public boolean excluir(int id) throws ClassNotFoundException, SQLException {
		
		boolean sucesso=false;
		Connection conexao =  DaoConexaoJDBC.abrirConexao();
		
		PreparedStatement pStmt = conexao.prepareStatement("DELETE FROM CURSO WHERE ID=?");
		pStmt.setInt(1,id);
		
		int rows = pStmt.executeUpdate();
		conexao.commit();
		DaoConexaoJDBC.fecharConexao();
		if(rows==1){
			sucesso=true;
		}
		
	return sucesso;
	}

	@Override
	public boolean alterar(int id, Curso c) throws ClassNotFoundException, SQLException {

		boolean sucesso=false;
		Connection conexao =  DaoConexaoJDBC.abrirConexao();
		
		PreparedStatement pStmt = conexao.prepareStatement("UPDATE  CURSO SET NOME=? WHERE ID=?");
		pStmt.setString(1,c.getNome());
		pStmt.setInt(2,id);
		
		int rows = pStmt.executeUpdate();
		conexao.commit();
		DaoConexaoJDBC.fecharConexao();
		if(rows==1){
			sucesso=true;
		}
		
	return sucesso;
		
	}

}
