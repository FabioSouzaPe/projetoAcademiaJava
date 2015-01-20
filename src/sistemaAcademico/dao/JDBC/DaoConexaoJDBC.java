package sistemaAcademico.dao.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoConexaoJDBC {
	
	public static Connection cn;
	
	public static Connection abrirConexao() throws ClassNotFoundException, SQLException {
		
		String url = "jdbc:mysql://127.0.0.1:3307/sistemaAcademico";
		String user = "root";
		String pass = "12345";
		
		
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, user,pass);
		
	
		return cn;
	}
	
	public static void fecharConexao() throws SQLException{
		cn.close();	
	}
	
}
