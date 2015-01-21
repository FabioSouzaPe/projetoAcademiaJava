package sistemaAcademico.daojdbc;

import java.util.ArrayList;

import sistemAcademico.exceptions.ErroConexaoException;
import sistemAcademico.exceptions.ProfessorInexistenteException;
import sistemaAcademico.classesBasicas.Professor;

public interface DaoProfessorIntJDBC {
	
	public void cadastrarProfessor(Professor professor) throws ErroConexaoException;	
	
	public void alterar(Professor professor) throws ErroConexaoException;
	
	public void remover(Professor professor) throws ErroConexaoException;
	
	public ArrayList<Professor>consultarTudo();
	
	public Professor pesquisarprofessor(String matricula) throws ProfessorInexistenteException;


}
