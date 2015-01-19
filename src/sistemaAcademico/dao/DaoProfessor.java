package sistemaAcademico.dao;

import java.util.ArrayList;


import sistemaAcademico.classesBasicas.Professor;

public class DaoProfessor implements DaoProfessorInt {
	
	
 ArrayList< Professor> listaprof = new ArrayList<Professor>();
		
	
	public void cadastrarProfessor(Professor professor){
	listaprof.add(professor);
		
		
	}	
		
		
	

	public void alterar(Professor professor){
		
	for (int i = 0; i < listaprof.size()-1; i++) {
		if(listaprof.get(i).getId() == professor.getId()){
		
			listaprof.set(i, professor);
		}
		
	}	
		
		
	}
	
	public void remover(Professor professor){
	
		for (int i = 0; i < listaprof.size(); i++) {
			
			if(listaprof.get(i).getId()==professor.getId()){
				
			listaprof.remove(professor);	
				
			}
			
		}
		 
		
		
		
		
}




	@Override
	public ArrayList<Professor> consultarTudo() {
		// TODO Auto-generated method stub
		return listaprof;
	}
	
	
	
}


