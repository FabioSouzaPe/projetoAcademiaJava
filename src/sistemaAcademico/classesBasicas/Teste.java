package sistemaAcademico.classesBasicas;


import java.util.Date;

import sistemaAcademico.dao.DaoProfessor;
import sistemaAcademico.dao.DaoProfessorInt;
import sistemaAcademico.enuns.Titulo;
import sistemaAcademico.regrasDeNegocio.RnProfessor;
import sun.security.provider.VerificationProvider;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	//	System.out.println("selecione a opção:");
		Date data = new Date();
		
		//SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
	
		Professor pro = new Professor(1,data,"POO","Unibratec", Titulo.POSGRADUACAO);
	    DaoProfessorInt dadosprofint = new DaoProfessor();
	      
	    //dadosprof.cadastrarProfessor(pro);
	    
	    
	    
	   Professor prof = new Professor(1,data,"APS","Unibratec",null );
	   
	    dadosprofint.alterar(prof);
	    
	    //Regra negocio
	    
	    RnProfessor rnprof = new RnProfessor();
	    
	    if(rnprof.verificarProfessorJaCadastrado(pro) == false){
	    	
	    	System.out.println("OK,pode cadastrar");
	    	
	    	dadosprofint.cadastrarProfessor(pro);
	    	for (int i = 0; i < dadosprofint.consultarTudo().size(); i++) {
	    	
	    	 	System.out.println(dadosprofint.consultarTudo().get(i).getId());
			}
	   
	    
	    	
	    }else{
	    	System.out.println("ja cadastrado");
	    }
	    
	    
	    
	    
	    
 
	}

}
