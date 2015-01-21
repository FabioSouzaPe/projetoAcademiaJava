package sistemaAcademico.dao;

import java.util.ArrayList;



import sistemAcademico.exceptions.ProfessorInexistenteException;
import sistemaAcademico.classesBasicas.Professor;

public class DaoProfessor implements DaoProfessorInt {
	
	
 ArrayList< Professor> listaprof = new ArrayList<Professor>();
		
	
 // Cadatrar
	public void cadastrarProfessor(Professor professor){
		
		
	listaprof.add(professor);
		
		
	}	
		
		
	
 // Alterar
	public void alterar(Professor professor){
		
	for (int i = 0; i < listaprof.size()-1; i++) {
		if(listaprof.get(i).getMatricula().equals(professor.getMatricula())){
		
			listaprof.set(i, professor);
		}
		
	}	
		
		
	}
	
	//Remover
	
	public void remover(Professor professor){
	
		for (int i = 0; i < listaprof.size(); i++) {
			
			if(listaprof.get(i).getMatricula().equals(professor.getMatricula())){
				
			listaprof.remove(professor);	
				
			}
			
		}
		 
		
		
		
		
}
	
	public Professor pesquisarprofessor(String matricula) throws ProfessorInexistenteException{
		if(listaprof.size() != 0 ){
			for (int i = 0; i < listaprof.size(); i++) {
			
				if(listaprof.get(i).getMatricula().equals(matricula) ){
				
					return listaprof.get(i);
				
				}
			
			}
			throw new ProfessorInexistenteException();
		}
		 return null;
	}




	@Override
	public ArrayList<Professor> consultarTudo() {
		// TODO Auto-generated method stub
		return listaprof;
	}
	
	
	
}


