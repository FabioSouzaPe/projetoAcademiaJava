package sistemaAcademico.classesBasicas;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import sistemaAcademico.daoJDBC.DaoProfessorIntJDBC;
import sistemaAcademico.daoJDBC.DaoProfessorJDBC;
import sistemaAcademico.enuns.Titulo;



public class Teste {
	
	public static void main(String[] args){
		
		DaoProfessorIntJDBC dados = new DaoProfessorJDBC();
		Professor professor = new Professor();
		
		Date data = new Date();
	
		professor.setMatricula("12");
	
		professor.setAdmissao(data);		
		professor.setDepartamento("POO");
		professor.setInstituicao("Nassau");
		professor.setTitulo(Titulo.DOUTORADO);
		
		//dados.remover(professor);
		
		dados.cadastrarProfessor(professor);
		
		
		ArrayList<Professor> retorno = new ArrayList<Professor>();
		retorno = dados.consultarTudo();
		
		Iterator<Professor> it = retorno.iterator();
		
		while(it.hasNext()){
			
			Professor prof = it.next();
			
			System.out.println(prof.getTitulo().name());
		}
		
		//dados.alterar(professor);
		
	}

}
