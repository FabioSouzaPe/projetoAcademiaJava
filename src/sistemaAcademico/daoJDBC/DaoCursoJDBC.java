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
import sistemaAcademico.conexao.ConexaoInt;
import sistemaAcademico.exceptions.ConexaoException;

public class DaoCursoJDBC implements DaoCursoJDBCInt{

	
	
	@Override
	public ResultSet consultar(String select) throws ClassNotFoundException, SQLException {
		 ConexaoInt conexao = new Conexao();
		
		PreparedStatement pStmt;
		try {
			pStmt = conexao.conectar().prepareStatement(select);
		
		ResultSet rs = pStmt.executeQuery();
		
		return rs;
	
		} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	

		
	}

	@Override
	public List<Turma> consultarTurmas() {
		
		return null;
	}

	@Override
	public boolean cadastrar(Curso curso) throws ClassNotFoundException, SQLException {
		 ConexaoInt conexao = new Conexao();
			boolean sucesso=false;
			
			java.util.Date dataUtil = curso.getData(); 
			java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
			
			
			PreparedStatement pStmt;
			try {
				pStmt = conexao.conectar().prepareStatement("INSERT INTO CURSO ( NOME,DATA) VALUES (?,?)");
			
			pStmt.setString(1,curso.getNome());
			pStmt.setDate(2,dataSql);
			
			int rows = pStmt.executeUpdate();
		
			conexao.desconectar();;
			if(rows==1){
				sucesso=true;
			}
			} catch (ConexaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return sucesso;
	}

	@Override
	public boolean excluir(int id) throws ClassNotFoundException, SQLException {
		ConexaoInt conexao = new Conexao();
		boolean sucesso=false;

		try {
		PreparedStatement pStmt = conexao.conectar().prepareStatement("DELETE FROM CURSO WHERE ID=?");
		pStmt.setInt(1,id);
		
		int rows = pStmt.executeUpdate();
		
		conexao.desconectar();
		if(rows==1){
			sucesso=true;
		}} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return sucesso;
	}

	@Override
	public boolean alterar(int id, Curso c) throws ClassNotFoundException, SQLException {
		ConexaoInt conexao = new Conexao();
		boolean sucesso=false;
		
		try {
		PreparedStatement pStmt = conexao.conectar().prepareStatement("UPDATE  CURSO SET NOME=? WHERE ID=?");
		pStmt.setString(1,c.getNome());
		pStmt.setInt(2,id);
		
		int rows = pStmt.executeUpdate();
		
		conexao.desconectar();
		if(rows==1){
			sucesso=true;
		}} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	return sucesso;
		
	}

}
