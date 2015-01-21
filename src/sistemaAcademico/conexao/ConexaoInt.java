package sistemaAcademico.conexao;

import java.sql.Connection;

import sistemaAcademico.exceptions.ConexaoException;

public interface ConexaoInt {
	
	public Connection conectar() throws ConexaoException; 
	public void desconectar() throws ConexaoException;
	  
}
