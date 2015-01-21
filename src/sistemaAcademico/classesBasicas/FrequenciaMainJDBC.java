package sistemaAcademico.classesBasicas;

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
				
				 HashMap<Integer,Boolean> presenca = new HashMap<Integer,Boolean>();
				 presenca.put(1, true);//Edson
				 presenca.put(2, true);//Michel
				 presenca.put(3, false);//Alan
				 presenca.put(4, true);//Rafael
				 presenca.put(5, false);//Falvio
			
				 
				 HashMap<Integer,String> avaliacao = new HashMap<Integer,String>();
				 avaliacao.put(1, "Bom");//Edson
				 avaliacao.put(2, "Bom");//Michel
				 avaliacao.put(3, "Ruim");//Fred
				 avaliacao.put(4, "Bom");//Rafael
				 avaliacao.put(5, "Bom");//Falvio
				
				Frequencia frequencia= new Frequencia(0, new Date(), new Turma(), presenca, avaliacao);
				
				
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
