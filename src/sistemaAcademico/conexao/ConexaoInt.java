package sistemaAcademico.conexao;

import java.sql.Connection;

import sistemAcademico.exceptions.ConexaoException;

public interface ConexaoInt {
	
	public Connection conectar() throws ConexaoException; 
	public void desconectar() throws ConexaoException;
	  
}
