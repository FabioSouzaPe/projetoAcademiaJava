package sistemaAcademico.regrasDeNegocio;

import sistemAcademico.exceptions.ProfessorExistenteException;
import sistemAcademico.exceptions.ProfessorInexistenteException;
import sistemaAcademico.classesBasicas.Professor;
import sistemaAcademico.daoJDBC.DaoProfessorIntJDBC;
import sistemaAcademico.daoJDBC.DaoProfessorJDBC;

public class RnProfessorJDBC {
	 DaoProfessorIntJDBC dados = new DaoProfessorJDBC();
	 
	public void cadastrarProfessor(Professor professor) throws ProfessorExistenteException{
		try {
			if(dados.pesquisarprofessor(professor.getMatricula()) == null){
				dados.cadastrarProfessor(professor);
				
				
			}else{
				throw new ProfessorExistenteException();
			}	
			
		} catch (ProfessorInexistenteException e) {
			dados.cadastrarProfessor(professor);
		} 
		dados.cadastrarProfessor(professor);
		
	}
	
	public void alterar(Professor professor) {
		try {
			if(dados.pesquisarprofessor(professor.getMatricula()) != null){
				dados.alterar(professor);
				
			}
		} catch (ProfessorInexistenteException e) {
			System.out.println(e.getMessage());
			
		}	
		
		
		dados.alterar(professor);
	}
	
	public void remover(Professor professor) {
		try {
			if(dados.pesquisarprofessor(professor.getMatricula()) != null){
				dados.remover(professor);
				
			}
		} catch (ProfessorInexistenteException e) {
			System.out.println(e.getMessage());
			
		}	
		
		
		
	}
	
	

}
