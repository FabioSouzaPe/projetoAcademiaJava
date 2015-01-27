package sistemaAcademico.regrasDeNegocio;

import java.sql.SQLException;
import java.util.ArrayList;

import sistemaAcademico.exceptions.ProfessorExistenteException;
import sistemaAcademico.exceptions.ProfessorInexistenteException;
import sistemaAcademico.classesBasicas.Professor;
import sistemaAcademico.daoJDBC.DaoProfessorIntJDBC;
import sistemaAcademico.daoJDBC.DaoProfessorJDBC;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.PessoaInexistenteException;

public class RnProfessorJDBC {
	 DaoProfessorIntJDBC dados = new DaoProfessorJDBC();
	 
	 //Cadastrar
	public void cadastrarProfessor(Professor professor) throws PessoaInexistenteException, ProfessorExistenteException, ConexaoException, SQLException, ProfessorInexistenteException{
		//Este trecho de codigo ele verifica se o professor é Inexistente ou não, caso for podera ser cadastro.  
		if(dados.pesquisarprofessor(professor.getMatricula()) != null){
			dados.cadastrarProfessor(professor);	
			
		}else{
			throw new sistemaAcademico.exceptions.ProfessorInexistenteException();
		}  
		
	}
	
	//Alterar
	public void alterar(Professor professor) throws Exception,PessoaInexistenteException {
		try {
//Este trecho de codigo ele verifica se o professor é existente ou não, caso ele seja existente pode ser removido.  
			if(dados.pesquisarprofessor(professor.getMatricula()) != null){
				dados.alterar(professor);
				
			}
		} catch (sistemaAcademico.exceptions.ProfessorInexistenteException e) {
			throw new PessoaInexistenteException();
			
		} 			
	}
	
	//Remover
	public void remover(Professor professor)throws sistemaAcademico.exceptions.ProfessorInexistenteException, SQLException, ConexaoException {
		try {
//Este trecho de codigo ele verifica se o professor é existente ou não, caso ele seja existente pode ser Alterado.  
			if(dados.pesquisarprofessor(professor.getMatricula()) != null){
				dados.remover(professor);
			}
		} catch (sistemaAcademico.exceptions.ProfessorInexistenteException e) {
			System.out.println(e.getMessage());
			
		}
	}
	
	//Listar
	public ArrayList<Professor>consultarTudo() throws ConexaoException, SQLException{
			return dados.consultarTudo();
	}	      

}
