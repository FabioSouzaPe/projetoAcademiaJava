package sistemaAcademico.regrasDeNegocio;



//import sistemAcademico.exceptions.ErroConexaoException;
//import sistemAcademico.exceptions.ProfessorExistenteException;
//import sistemAcademico.exceptions.ProfessorInexistenteException;
import sistemaAcademico.classesBasicas.Professor;
import sistemaAcademico.dao.DaoProfessor;
import sistemaAcademico.dao.DaoProfessorInt;
import sistemaAcademico.daoJDBC.DaoProfessorIntJDBC;
import sistemaAcademico.daoJDBC.DaoProfessorJDBC;
import sistemaAcademico.exceptions.ConexaoException;





public class RnProfessor {
	//DaoProfessorInt dadosint = new DaoProfessor();
	
	DaoProfessorInt dadosint = new DaoProfessor();
		
	//Verificação
	
	public boolean verificarProfessorJaCadastrado(Professor professor){
	
		boolean jacadastrado = false;
		
		for (int i = 0; i < dadosint.consultarTudo().size(); i++) {
			
			if(dadosint.consultarTudo().get(i).getMatricula() == (professor.getMatricula())){
				
				jacadastrado= true;
				
			}
			
		}
		
		
		return jacadastrado;		
	
}
	//Cadastrar
	public void cadastrarProfessor(Professor professor) throws ProfessorExistenteException, ErroConexaoException, ConexaoException{
		try {
			if(dadosint.pesquisarprofessor(professor.getMatricula()) == null){
				dadosint.cadastrarProfessor(professor);
				
				
			}else{
				throw new ProfessorExistenteException();
			}	
			
		} catch (ProfessorInexistenteException e) {
			dadosint.cadastrarProfessor(professor);
		} 
				
		
	}
	
	 
 //Remover
	public void remover(Professor professor) throws ProfessorInexistenteException{
		try {
			if(dadosint.pesquisarprofessor(professor.getMatricula()) != null){
				dadosint.remover(professor);
				
			}
		} catch (ProfessorInexistenteException e) {
			System.out.println(e.getMessage());
			
		}	
		
	}
	//Alterar
	public void alterar(Professor professor) throws ProfessorInexistenteException{
		
		try {
			
			if(dadosint.pesquisarprofessor(professor.getMatricula()) != null){
				dadosint.alterar(professor);
				
			}
			
		} catch (ProfessorInexistenteException e) {
			
			System.out.println(e.getMessage());
		}
		
		
	}
}

