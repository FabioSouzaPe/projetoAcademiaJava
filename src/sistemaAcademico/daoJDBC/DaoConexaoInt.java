package sistemaAcademico.daoJDBC;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoConexaoInt {

	
	public Connection conectar() throws SQLException;
	public void desconectar() throws SQLException;
	
	
	
}
