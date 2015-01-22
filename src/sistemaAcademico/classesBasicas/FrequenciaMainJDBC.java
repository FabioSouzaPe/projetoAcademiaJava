package sistemaAcademico.classesBasicas;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import sistemaAcademico.regrasDeNegocio.RnFrequencia;


public class FrequenciaMainJDBC {
	
	
	public static void main(String[] args) {
		
		 RnFrequencia rn = new RnFrequencia ();
		 boolean run=true;
		 while(run){
			 
				System.out.println("\n -----------------------------------------");
				System.out.println("| Para sair do Sistema digite --------> 0 |");
				System.out.println("| Para Registrar Frequencia ----------> 1 |");
				System.out.println("| Para Listar Frequencia Digite ------> 2 |");
				System.out.println("| Para atualizar um curso Digite -----> 3 |");
				System.out.println(" -----------------------------------------\n");
				Scanner sc1 = new Scanner(System.in);
				int opcao= sc1.nextInt();
				
			switch(opcao){
			case 1:
				ArrayList<Frequencia> f = new ArrayList<Frequencia>();
				Frequencia frequencia= new Frequencia();
				
				
				//rn.registarFrequencia(frequencia);
				
				break;
			case 2:
				break;
			case 3:
				break;
			case 0:
				run=false;
				break;
			default:
				System.out.println("Opição inválida");
				break;
			}
		 }
		 
		 
		
		
		
	}
	
	
}
