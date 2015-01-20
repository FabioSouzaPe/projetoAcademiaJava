package sistemaAcademico.daoJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoConexaoJDBC {
	
	private static Connection cn;

	public static Connection conectar(){
		try {
			String url = "jdbc:mysql://localhost:3307/sistemaacademico";
			String user = "root";
			String pass = "12345";
			
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cn;
	}
	
	public static void desconectar(){
		try {
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
