package sistemaAcademico.classesBasicas;


//import java.util.Date;

//import sistemaAcademico.dao.DaoProfessor;
//import sistemaAcademico.dao.DaoProfessorInt;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JOptionPane;

import sistemaAcademico.dao.DaoProfessorInt;
import sistemaAcademico.daoJDBC.DaoProfessorIntJDBC;
import sistemaAcademico.daoJDBC.DaoProfessorJDBC;
import sistemaAcademico.enuns.Titulo;
//import sistemaAcademico.regrasDeNegocio.RnProfessor;
//import sun.security.provider.VerificationProvider;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		
		
		DaoProfessorIntJDBC dados = new DaoProfessorJDBC();
		Professor professor = new Professor();
		
		Date data = new Date();
	
		professor.setMatricula("12");
	
		professor.setAdmissao(data);		
		professor.setDepartamento("POO");
		professor.setInstituicao("Nassau");
		professor.setTitulo(Titulo.DOUTORADO);
		
		dados.remover(professor);
		
		//dados.cadastrarProfessor(professor);
		
		
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
