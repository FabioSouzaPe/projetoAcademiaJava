package sistemaAcademico.daoJDBC;


import java.sql.SQLException;
import java.util.ArrayList;

import sistemaAcademico.exceptions.ErroSQLException;
import sistemaAcademico.exceptions.ProfessorInexistenteException;
import sistemaAcademico.classesBasicas.Professor;
import sistemaAcademico.exceptions.ConexaoException;

public interface DaoProfessorIntJDBC {
	
	public void cadastrarProfessor(Professor professor, int chave) throws ConexaoException, ErroSQLException ;	
	
	public void alterar(Professor professor) throws ConexaoException, ErroSQLException;
	
	public void remover(Professor professor) throws ConexaoException, ErroSQLException;
	
	public ArrayList<Professor>consultarTudo() throws ConexaoException, SQLException, ErroSQLException;
	
	public Professor pesquisarprofessor(String matricula) throws ProfessorInexistenteException, ConexaoException, ErroSQLException;


}
