package sistemaAcademico.classesBasicas;

import java.util.Date;
import java.util.Scanner;
import sistemaAcademico.dao.DaoCurso;
import sistemaAcademico.dao.DaoCursoInt;
import sistemaAcademico.regrasDeNegocio.RnCurso;

public class CursoMain {
	 public static void main(String[] args) {
		boolean continuar = true;
		DaoCursoInt dao=new DaoCurso ();
		RnCurso rn = new RnCurso();
		
		
		while(continuar){
			Scanner sc1 = new Scanner(System.in);
			System.out.println("\n -----------------------------------------");
			System.out.println("| Para sair do Sistema digite --------> 0 |");
			System.out.println("| Para cadastrar um Curso Digite -----> 1 |");
			System.out.println("| Para consultar Curso Digite --------> 2 |");
			System.out.println("| Para excluir um curso Digite -------> 3 |");
			System.out.println(" -----------------------------------------\n");
			
			
			try{
				int opcao=sc1.nextInt();
				if(opcao==1){
					System.out.println("\nPara cadastrar um Curso Digite:\nID da Curso\nNome do Curso\nData de criação do Curso\nTurmas do Curso\n");
					
					int id= sc1.nextInt();
					sc1.nextLine();
					String nome=sc1.nextLine();
					Date d = new Date();
					Curso c= new Curso(id, nome, d, null);
					
					if(rn.cursoJaCadastrado(nome)==false){
						
						if(dao.cadastrar(c)){
							System.out.println("Curso Cadastrado com sucesso");
						}else{
							System.out.println("O Curso não foi cadastrado");
						}
						
					}else{
						System.out.println("Este Curso Já foi cadastrado");
					}
					
					
					
				}else if(opcao==2){
					
					if(dao.consultarTudo().size()!=0){
						
						for(int i=0; i<dao.consultarTudo().size();i++){
							System.out.println("------------");
							System.out.println(dao.consultarTudo().get(i).getId());
							System.out.println(dao.consultarTudo().get(i).getNome());
							System.out.println(dao.consultarTudo().get(i).getData());
							System.out.println(dao.consultarTudo().get(i).getTurma());
							System.out.println("------------");
						}
					}else{
						System.out.println("Nenhum Curso Cadastrado momento");
					}
				}else if(opcao==0){
					continuar=false;
					System.out.println("Até logo");
					sc1.close();
				}else if(opcao==3){
					System.out.println("Digite o nome do curso a ser excluido:");
					String nome=sc1.next();
					
					if(rn.semCurso()==false){
						if(dao.excluirPorNome(nome)){
							System.out.println("O Curso: "+nome+" Foi excluido com sucesso");
						}else{
							System.out.println("O Curso: "+nome+" NÃO foi excluido, pois pois esse curso não foi cadastrado");
						}
						
					}else{
						System.out.println("Nenhum curso cadastrado ainda");
					}
				}else{
					System.out.println("Opção Inválida!");
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			
			
		}
		
	}
}
