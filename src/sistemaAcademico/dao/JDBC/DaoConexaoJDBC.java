package sistemaAcademico.dao.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class DaoConexaoJDBC {
	
	public static Connection cn;
	
	public static Connection abrirConexao() throws ClassNotFoundException, SQLException {
		
		String url = "jdbc:mysql://127.0.0.1:3307/sistemaacademico";
		String user = "root";
		String pass = "12345";
		
		
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, user,pass);
			cn.setAutoCommit(false);
		return cn;
	}
	
	public static void fecharConexao() throws SQLException{
		cn.close();	
	}
	
}
