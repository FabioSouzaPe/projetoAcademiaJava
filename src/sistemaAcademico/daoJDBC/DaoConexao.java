package sistemaAcademico.daoJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoConexao implements DaoConexaoInt {

	
	String url;
	String usuario;
	String senha;
	Connection c;
	
	public DaoConexao() {
		
		// TODO Auto-generated constructor stub
		String url = "jdbc:mysql://localhost:3306/sistema_academico";
		String user = "root";
		String pass = "teste";
	
	}
	
	
	@Override
	public Connection conectar() throws SQLException {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection(url,usuario,senha);
		} catch (ClassNotFoundException e) {
			
			throw new SQLException();
			
		} catch (SQLException e) {
			
			
			throw new SQLException();
		}
		
		// TODO Auto-generated method stub
		return c;
	}

	@Override
	public void desconectar() throws SQLException {
		// TODO Auto-generated method stub
		
		try {
			
			c.close();
			
		} catch (SQLException e) {
			
			throw new SQLException();
		} catch (NullPointerException e) {
			
			throw new SQLException();
		}
		
		c = null;
		
	}

	
	
	
	
}
