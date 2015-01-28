package sistemaAcademico.exceptions;

import java.sql.SQLException;

public class ErroSQLException extends Exception{

	
	public ErroSQLException(){
		super("Erro na instrucao SQL passada");
	}

	
	
}
