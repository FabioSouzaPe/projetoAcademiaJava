package sistemaAcademico.daoJDBC;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoConexaoIntJDBC {
	public Connection conectar() throws SQLException;
	public void desconectar() throws SQLException;
}
