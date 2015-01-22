package sistemaAcademico.daoJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import sistemaAcademico.classesBasicas.Curso;
import sistemaAcademico.classesBasicas.Turma;

public class DaoCursoJDBC implements DaoCursoJDBCInt{

	
	
	@Override
	public ResultSet consultar(String select) throws ClassNotFoundException, SQLException {
		
		DaoConexaoJDBC daoCon = new DaoConexaoJDBC();
		Connection conexao =  daoCon .conectar();
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
			DaoConexaoIntJDBC daoCon = new DaoConexaoJDBC();
			Connection conexao =  daoCon.conectar();
			java.util.Date dataUtil = curso.getData(); 
			java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
			
			
			PreparedStatement pStmt = conexao.prepareStatement("INSERT INTO CURSO ( NOME,DATA) VALUES (?,?)") ;
			pStmt.setString(1,curso.getNome());
			pStmt.setDate(2,dataSql);
			
			int rows = pStmt.executeUpdate();
			conexao.commit();
			daoCon.desconectar();
			if(rows==1){
				sucesso=true;
			}
			
		return sucesso;
	}

	@Override
	public boolean excluir(int id) throws ClassNotFoundException, SQLException {
		
		boolean sucesso=false;
		DaoConexaoIntJDBC daoCon = new DaoConexaoJDBC();
		Connection conexao =  daoCon.conectar();
		
		PreparedStatement pStmt = conexao.prepareStatement("DELETE FROM CURSO WHERE IDCURSO=?");
		pStmt.setInt(1,id);
		
		int rows = pStmt.executeUpdate();
		conexao.commit();
		daoCon.desconectar();
		if(rows==1){
			sucesso=true;
		}
		
	return sucesso;
	}

	@Override
	public boolean alterar(int id, Curso c) throws ClassNotFoundException, SQLException {

		boolean sucesso=false;
		DaoConexaoIntJDBC daoCon = new DaoConexaoJDBC();
		Connection conexao =  daoCon.conectar();
		
		PreparedStatement pStmt = conexao.prepareStatement("UPDATE  CURSO SET NOME=? WHERE IDCURSO=?");
		pStmt.setString(1,c.getNome());
		pStmt.setInt(2,id);
		
		int rows = pStmt.executeUpdate();
		conexao.commit();
		daoCon.desconectar();
		if(rows==1){
			sucesso=true;
		}
		
	return sucesso;
		
	}

}
