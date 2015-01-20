package sistemaAcademico.conexao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConexaoInt {
	
	public Connection conectar() throws SQLException;
	
	public void desconectar() throws SQLException;
	

}
