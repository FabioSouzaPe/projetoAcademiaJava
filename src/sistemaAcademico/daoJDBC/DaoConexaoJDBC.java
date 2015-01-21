package sistemaAcademico.daoJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoConexaoJDBC implements DaoConexaoIntJDBC {

	String url;
	String user;
	String pass;
	
	Connection c;

	public DaoConexaoJDBC() {
		url = "jdbc:mysql://localhost:3307/sistema_academico";
		user = "root";
		pass = "12345";
	}

	public Connection conectar() throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException();
		} catch (SQLException e) {
			throw new SQLException();
		}
		return c;
	}

	public void desconectar() throws SQLException, ClassNotFoundException {
		try {
			c.close();
		} catch (SQLException e) {
			throw new ClassNotFoundException();
		} catch (NullPointerException e) {
			throw new SQLException();
		}
		
		c = null;
	}

}