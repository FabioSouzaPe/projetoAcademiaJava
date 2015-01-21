package sistemaAcademico.conexao;

import java.sql.Connection;
import java.sql.SQLException;

import sistemAcademico.exceptions.ErroConexaoException;

public interface ConexaoInt {
	
	public Connection conectar() throws  ErroConexaoException;
	
	public void desconectar() throws  ErroConexaoException;
	

}
