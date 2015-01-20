package sistemaAcademico.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao  implements ConexaoInt{
	
	 String url;
	 String user;
	 String pass;
	 Connection con;
	 
	 
	 
	 public Conexao(){
		 url= "jdbc:mysql://localhost:3307/teste";
		 user = "root";
	      pass = "root";
	 }

	@Override
	public Connection conectar() throws SQLException {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
			
		} catch (ClassNotFoundException e) {
			throw new SQLException();
		}catch (SQLException e){
		   throw new SQLException();	
		}
		// 
		return con;
	}

	@Override
	public void desconectar() throws SQLException {
		try {
			con.close();
			
		} catch (SQLException e) {
			throw new SQLException();
			
		}catch (NullPointerException e) {
			throw new SQLException();
		}
		con=null;
	}
	
	

}
