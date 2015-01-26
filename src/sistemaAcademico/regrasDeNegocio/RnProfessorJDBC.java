package sistemaAcademico.regrasDeNegocio;

import java.sql.SQLException;
import java.util.ArrayList;

import sistemAcademico.exceptions.ProfessorExistenteException;
import sistemAcademico.exceptions.ProfessorInexistenteException;
import sistemaAcademico.classesBasicas.Professor;
import sistemaAcademico.daoJDBC.DaoProfessorIntJDBC;
import sistemaAcademico.daoJDBC.DaoProfessorJDBC;
import sistemaAcademico.exceptions.ConexaoException;

public class RnProfessorJDBC {
	 DaoProfessorIntJDBC dados = new DaoProfessorJDBC();
	 
	 //Cadastrar
	public void cadastrarProfessor(Professor professor) throws ProfessorInexistenteException, ProfessorExistenteException, ConexaoException, SQLException{
		try {
//Este trecho de codigo ele verifica se o professor é Inexistente ou não, caso for podera ser cadastro.  
			if(dados.pesquisarprofessor(professor.getMatricula()) != null){
				dados.cadastrarProfessor(professor);	
				
			}else{
				throw new ProfessorExistenteException();
			}	
			
		} catch (ProfessorInexistenteException e) {
			dados.cadastrarProfessor(professor);
		}  
		
	}
	
	//Alterar
	public void alterar(Professor professor) throws Exception,ProfessorInexistenteException {
		try {
//Este trecho de codigo ele verifica se o professor é existente ou não, caso ele seja existente pode ser removido.  
			if(dados.pesquisarprofessor(professor.getMatricula()) != null){
				dados.alterar(professor);
				
			}
		} catch (ProfessorInexistenteException e) {
			throw new ProfessorInexistenteException();
			
		} 			
	}
	
	//Remover
	public void remover(Professor professor)throws ProfessorInexistenteException, SQLException, ConexaoException {
		try {
//Este trecho de codigo ele verifica se o professor é existente ou não, caso ele seja existente pode ser Alterado.  
			if(dados.pesquisarprofessor(professor.getMatricula()) != null){
				dados.remover(professor);
			}
		} catch (ProfessorInexistenteException e) {
			System.out.println(e.getMessage());
			
		}
	}
	
	//Listar
	public ArrayList<Professor>consultarTudo() throws ConexaoException, SQLException{
			return dados.consultarTudo();
	}	      

}
