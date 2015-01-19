package sistemaAcademico.dao;

import java.util.ArrayList;

import sistemaAcademico.classesBasicas.Professor;

public interface DaoProfessorInt {
	
	public void cadastrarProfessor(Professor professor);
	
	public void alterar(Professor professor);
	
	public void remover(Professor professor);
	
	public ArrayList<Professor>consultarTudo();

}
