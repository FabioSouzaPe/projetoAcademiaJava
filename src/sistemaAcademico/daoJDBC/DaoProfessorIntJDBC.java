package sistemaAcademico.daoJDBC;


import java.sql.SQLException;
import java.util.ArrayList;

import sistemaAcademico.exceptions.ProfessorInexistenteException;
import sistemaAcademico.classesBasicas.Professor;
import sistemaAcademico.exceptions.ConexaoException;

public interface DaoProfessorIntJDBC {
	
	public void cadastrarProfessor(Professor professor) throws ConexaoException, SQLException ;	
	
	public void alterar(Professor professor) throws ConexaoException;
	
	public void remover(Professor professor) throws ConexaoException;
	
	public ArrayList<Professor>consultarTudo() throws ConexaoException, SQLException;
	
	public Professor pesquisarprofessor(String matricula) throws ProfessorInexistenteException, ConexaoException;


}
