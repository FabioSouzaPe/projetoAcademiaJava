package sistemaAcademico.classesBasicas;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import sistemAcademico.exceptions.ProfessorExistenteException;
import sistemAcademico.exceptions.ProfessorInexistenteException;
import sistemaAcademico.daoJDBC.DaoProfessorIntJDBC;
import sistemaAcademico.daoJDBC.DaoProfessorJDBC;
import sistemaAcademico.enuns.Titulo;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.regrasDeNegocio.RnProfessorJDBC;



public class Teste {
	
	public static void main(String[] args){
		
		DaoProfessorIntJDBC dados = new DaoProfessorJDBC();
		Professor professor = new Professor();
		RnProfessorJDBC regranegocio = new RnProfessorJDBC();
		
		Date data = new Date();
	
		professor.setMatricula("12");
	
		professor.setAdmissao(data);		
		professor.setDepartamento("QQ");
		professor.setInstituicao("Unibratec");
		professor.setTitulo(Titulo.DOUTORADO);
		
		
		
		//Teste Alterar OK
		/*
		try {
			dados.alterar(professor);
		} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		*/
		
		
		 // Teste Cadastrar OK
	/*	
	try {
		regranegocio.cadastrarProfessor(professor);;
	} catch (ProfessorInexistenteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ProfessorExistenteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ConexaoException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		*/
		
		//Teste Remover OK
		/*try {
			regranegocio.remover(professor);
		} catch (ProfessorInexistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	
		//ArrayList<Professor> retorno = new ArrayList<Professor>();
		//retorno = dados.consultarTudo();
		
		//Iterator<Professor> it = retorno.iterator();
		
		//while(it.hasNext()){
			
			//Professor prof = it.next();
			
		//	System.out.println(prof.getTitulo().name());
		//}
		
		
		
		//Teste do Listar OK
		/*try {
			System.out.println(regranegocio.consultarTudo().size());
			for (int i = 0; i < regranegocio.consultarTudo().size(); i++) {
				System.out.println(regranegocio.consultarTudo().get(i).getTitulo());
				System.out.println(regranegocio.consultarTudo().get(i).getDepartamento());
				System.out.println(regranegocio.consultarTudo().get(i).getMatricula());
				System.out.println(regranegocio.consultarTudo().get(i).getAdmissao());
				System.out.println(regranegocio.consultarTudo().get(i).getInstituicao());
				System.out.println(regranegocio.consultarTudo().get(i).getPessoa().getNome());
			}
		} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		*/
		
		//Teste Pesquisarprofessor OK
		/*
		try {
			Professor p = dados.pesquisarprofessor(professor.getMatricula());
			System.out.println(p.getInstituicao());
		} catch (ProfessorInexistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
	}

}
