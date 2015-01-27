package sistemaAcademico.classesBasicas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.FrequenciaException;
import sistemaAcademico.regrasDeNegocio.RnFrequencia;


public class FrequenciaMain {
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ConexaoException {
		
		 RnFrequencia rn = new RnFrequencia ();
		 boolean run=true;
		 while(run){
			 
				System.out.println("\n -----------------------------------------");
				System.out.println("| Para sair do Sistema digite --------> 0 |");
				System.out.println("| Para Registrar Frequencia ----------> 1 |");
				System.out.println("| Para Listar Frequencia Digite ------> 2 |");
				System.out.println("| Para atualizar Frequencia Digite ---> 3 |");
				System.out.println(" -----------------------------------------\n");
				Scanner scanner = new Scanner(System.in);
				int opcao= scanner.nextInt();
				
			switch(opcao){
			case 1:
				
				Scanner sc1 = new Scanner(System.in);
				
				
				//ALUNO 
				System.out.println("Digite a Matrícula do aluno");
				String matricula = sc1.next();
				System.out.println("Digite o ID da turma");
				int tur= sc1.nextInt();
				System.out.println("Digite o ID da disciplina");
				int disciplina= sc1.nextInt();
				System.out.println("Aluno Presente?");
				boolean presenca= sc1.nextBoolean();
				System.out.println("Atribua uma avaliação");
				String avaliacao = sc1.next();
				
				Aluno a1= new Aluno(matricula,null, null, new Date(),null);
				Turma t1 = new Turma();
				t1.setId(tur);
				Disciplina d= new Disciplina();
				d.setId(disciplina);
				
				Frequencia frequencia= new Frequencia(0, new Date(), t1, a1,  presenca,  avaliacao,  d);
				
				try{
					if(rn.montarScriptCadastrarFrequencia(frequencia)){
						System.out.println("frequencia realizada com sucesso");
					}
				}catch(FrequenciaException e){
					System.out.println(e.getMensagem());
				}catch(SQLException e){
					System.out.println(e.getMessage());
				}
				
				break;
				
			case 2:
				Scanner sc2 = new Scanner(System.in);
				Frequencia frequencia2= new Frequencia();
				Turma tur2= new Turma();
				Disciplina disciplina2=new Disciplina();
				
				System.out.println("Digite o ID da tumra que deseja consultar afrequencia");
				tur2.setId(sc2.nextInt());
				System.out.println("Agora digite o ID da disciplina que deseja consultar afrequencia");
				int idDisc=sc2.nextInt();
				if(idDisc!=0){
					disciplina2.setId(idDisc);
				}else{
					disciplina2=null;
				}
				frequencia2.setTurma(tur2);
				frequencia2.setDisciplina(disciplina2);
				ArrayList<Frequencia> f1 =rn.montarScriptListarFrequencia(frequencia2,new Date());
				
				if(!f1.isEmpty()){
					for(int i = 0; i<f1.size(); i++){
						System.out.println("___________________________________________");
						System.out.println("Aluno: "+f1.get(i).getAluno().getPessoa().getNome());
						System.out.println("Disciplina: "+f1.get(i).getDisciplina().getNome());
						System.out.println("Presença: "+f1.get(i).getPresenca());
						System.out.println("Avaliação: "+f1.get(i).getAvaliacao());
						System.out.println("___________________________________________");
					}
				}else{
					System.out.println("Ainda não foi realaizada a ferquencia para esta data nesta turma");
				}
				break;
				
			case 3:
				Scanner sc3 = new Scanner(System.in);
				
				System.out.println("Digite a Matrícula do aluno");
				String matricula3 = sc3.nextLine();
				System.out.println("Digite o ID da turma");
				int tur3= sc3.nextInt();
				System.out.println("Digite o ID da disciplina");
				int disciplina3= sc3.nextInt();
				System.out.println("Aluno Presente?");
				boolean presenca3= sc3.nextBoolean();
				System.out.println("Atribua uma avaliação");
				String avaliacao3 = sc3.next();
				
				Aluno a3= new Aluno(matricula3, null,null, new Date(),null);
				Turma t3 = new Turma();
				t3.setId(tur3);
				Disciplina d3= new Disciplina();
				d3.setId(disciplina3);
				
				Frequencia frequencia3 = new Frequencia(0, new Date(), t3, a3,  presenca3,  avaliacao3,  d3);
				try{
					if(rn.montarScriptAtualizarFrequencia(frequencia3)){
						System.out.println("Presença alterada com sucesso");
					}
				}catch(FrequenciaException e){
					System.out.println(e.getMensagem());
				}catch(SQLException e){
					System.out.println(e.getMessage());
				}
				
				
				break;
				
			case 0:
				scanner.close();
				run=false;
				break;
				
			default:
				System.out.println("Opição inválida");
				break;
			}
		 }
		 
		 
		
		
		
	}
	
	
}
