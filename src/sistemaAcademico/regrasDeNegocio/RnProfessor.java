package sistemaAcademico.regrasDeNegocio;

import sistemaAcademico.classesBasicas.Professor;
import sistemaAcademico.dao.DaoProfessor;
import sistemaAcademico.dao.DaoProfessorInt;
import sistemaAcademico.enuns.Titulo;




public class RnProfessor {
	
	public boolean verificarProfessorJaCadastrado(Professor professor){
	
		boolean jacadastrado = false;
		DaoProfessorInt dadosint = new DaoProfessor();
		for (int i = 0; i < dadosint.consultarTudo().size(); i++) {
			
			if(dadosint.consultarTudo().get(i).getId() == (professor.getId())){
				
				jacadastrado= true;
				
			}
			
		}
		
		
		return jacadastrado;
		
		
	
}
}
