package sistemaAcademico.daoJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemaAcademico.conexao.Conexao;
import sistemaAcademico.conexao.ConexaoInt;
import sistemaAcademico.exceptions.ConexaoException;

public class DaoGenerico implements IDaoGenerico{
		
		public static ConexaoInt daoConDQL= new Conexao();
	
	//método genérico usado pela DQL do Sql (SELECT)
	public ResultSet dql(String select) throws ClassNotFoundException, SQLException, ConexaoException {
	
		Connection conexao =  daoConDQL.conectar();
		PreparedStatement pStmt = conexao.prepareStatement(select) ;
		ResultSet rs = pStmt.executeQuery();
		return rs;
	}
	//método genérico usado para pela DML do Sql (INSERT UPDATE DELETE)
	public boolean dml(String sql) throws ClassNotFoundException, SQLException, ConexaoException {
		boolean sucesso=false;
		ConexaoInt daoCon= new Conexao();
		Connection conexao =  daoCon.conectar();
		PreparedStatement pStmt = conexao.prepareStatement(sql);
		int rows = pStmt.executeUpdate();
		daoCon.desconectar();
		
		if(rows==1)
			sucesso=true;
		
		return sucesso;
	}

}
