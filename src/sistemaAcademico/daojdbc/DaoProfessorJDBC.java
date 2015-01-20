package sistemaAcademico.daojdbc;

import java.util.ArrayList;

import sistemAcademico.exceptions.ProfessorInexistenteException;
import sistemaAcademico.classesBasicas.Professor;
import sistemaAcademico.conexao.Conexao;
import sistemaAcademico.dao.DaoProfessorInt;

public class DaoProfessorJDBC implements DaoProfessorInt {
	
	Conexao conexao;

	@Override
	public void cadastrarProfessor(Professor professor) {
		
		
	}

	@Override
	public void alterar(Professor professor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(Professor professor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Professor> consultarTudo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Professor pesquisarprofessor(String matricula)
			throws ProfessorInexistenteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
