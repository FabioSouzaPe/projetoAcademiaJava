package sistemaAcademico.daoJDBC;


import java.util.ArrayList;

import sistemAcademico.exceptions.ProfessorInexistenteException;
import sistemaAcademico.classesBasicas.Professor;
import sistemaAcademico.exceptions.ConexaoException;

public interface DaoProfessorIntJDBC {
	
	public void cadastrarProfessor(Professor professor) throws ConexaoException ;	
	
	public void alterar(Professor professor) throws ConexaoException;
	
	public void remover(Professor professor) throws ConexaoException;
	
	public ArrayList<Professor>consultarTudo() throws ConexaoException;
	
	public Professor pesquisarprofessor(String matricula) throws ProfessorInexistenteException, ConexaoException;


}
