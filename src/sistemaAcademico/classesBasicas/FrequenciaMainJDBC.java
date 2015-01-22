package sistemaAcademico.classesBasicas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import sistemaAcademico.regrasDeNegocio.RnFrequencia;


public class FrequenciaMainJDBC {
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
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
				Frequencia frequencia1= new Frequencia();
				Frequencia frequencia2= new Frequencia();
				Frequencia frequencia3= new Frequencia();
				
				//ALUNO 1
				Aluno a1= new Aluno("2015.1", null, new Date());
				Turma t1 = new Turma();
				t1.setId(117);
				frequencia1.setData(new Date());
				frequencia1.setAluno(a1);
				frequencia1.setTurma(t1);
				frequencia1.setPresenca(true);
				frequencia1.setAvaliacao("Bom");
				
				//ALUNO 2
				Aluno a2= new Aluno("2015.2", null, new Date());
				Turma t2 = new Turma();
				t2.setId(117);
				frequencia2.setData(new Date());
				frequencia2.setAluno(a2);
				frequencia2.setTurma(t2);
				frequencia2.setPresenca(false);
				frequencia2.setAvaliacao("Bom");
				
				//ALUNO 3
				Aluno a3= new Aluno("2015.3", null, new Date());
				Turma t3 = new Turma();
				t3.setId(117);
				frequencia3.setData(new Date());
				frequencia3.setAluno(a3);
				frequencia3.setTurma(t3);
				frequencia3.setPresenca(true);
				frequencia3.setAvaliacao("ruim");
				
				f.add(frequencia1);
				f.add(frequencia2);
				f.add(frequencia3);
				
				if(rn.verificarCadastrarFrequencia(f)){
					System.out.println("frequencia realizada com sucesso");
				}else{
					System.out.println("frequencia da data informada ja foi realizada");
				}
				
				break;
			case 2:
				ArrayList<Frequencia> f1 =rn.verificarListarFrequencia(new Date());
				
				if(!f1.isEmpty()){
					for(int i = 0; i<f1.size(); i++){
						System.out.println("___________________________________________");
						System.out.println("Aluno: "+f1.get(i).getAluno().getMatricula());
						System.out.println("Presença: "+f1.get(i).getPresenca());
						System.out.println("Avaliação: "+f1.get(i).getAvaliacao());
						System.out.println("___________________________________________");
					}
				}else{
					System.out.println("Ainda não foi realaizada a ferquencia para esta data");
				}
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
