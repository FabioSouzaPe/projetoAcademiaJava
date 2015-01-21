package sistemaAcademico.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import sistemAcademico.exceptions.ErroConexaoException;

public class Conexao  implements ConexaoInt{
	
	 String url;
	 String user;
	 String pass;
	 Connection con;
	 
	 
	 
	 public Conexao(){
		 url= "jdbc:mysql://localhost:3307/TesteProfessorjdbc";
		 user = "root";
	      pass = "root";
	 }

	@Override
	public Connection conectar() throws ErroConexaoException {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
			
		} catch (ClassNotFoundException e) {
			throw new ErroConexaoException();
		}catch (SQLException e){
		   throw new ErroConexaoException();	
		}
		// 
		return con;
	}

	@Override
	public void desconectar() throws ErroConexaoException {
		try {
			con.close();
			
		} catch (SQLException e) {
			throw new ErroConexaoException();
			
		}catch (NullPointerException e) {
			throw new ErroConexaoException();
		}
		con=null;
	}
	
	

}
