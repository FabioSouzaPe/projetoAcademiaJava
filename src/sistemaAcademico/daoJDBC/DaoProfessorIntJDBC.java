package sistemaAcademico.daoJDBC;

import java.util.ArrayList;

import sistemAcademico.exceptions.ErroConexaoException;
import sistemAcademico.exceptions.ProfessorInexistenteException;
import sistemaAcademico.classesBasicas.Professor;

public interface DaoProfessorIntJDBC {
	
	public void cadastrarProfessor(Professor professor) ;	
	
	public void alterar(Professor professor);
	
	public void remover(Professor professor);
	
	public ArrayList<Professor>consultarTudo();
	
	public Professor pesquisarprofessor(String matricula) throws ProfessorInexistenteException;


}