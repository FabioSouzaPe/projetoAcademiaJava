package sistemaAcademico.classesBasicas;


//import java.util.Date;

//import sistemaAcademico.dao.DaoProfessor;
//import sistemaAcademico.dao.DaoProfessorInt;
import java.util.Date;

import javax.swing.JOptionPane;

import sistemaAcademico.dao.DaoProfessorInt;
import sistemaAcademico.daojdbc.DaoProfessorIntJDBC;
import sistemaAcademico.daojdbc.DaoProfessorJDBC;
import sistemaAcademico.enuns.Titulo;
//import sistemaAcademico.regrasDeNegocio.RnProfessor;
//import sun.security.provider.VerificationProvider;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		
		
		DaoProfessorIntJDBC dados = new DaoProfessorJDBC();
		Professor professor = new Professor();
		
		Date data = new Date();
	/*
		professor.setMatricula("124");
	
		professor.setAdmissao(data);		
		professor.setDepartamento("POO");
		professor.setInstituicao("Nassau");
		professor.setTitulo(Titulo.POSGRADUACAO);
		*/
		//dados.cadastrarProfessor(professor);
		dados.consultarTudo();
		
		//dados.alterar(professor);
		
		//dados.remover(professor);
		
		
		
		
		
		
 
	}

}
