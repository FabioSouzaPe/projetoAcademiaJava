package sistemaAcademico.classesBasicas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import sistemaAcademico.regrasDeNegocio.RnCurso;

public class CursoMain {
	 public static void main(String[] args) {
		boolean continuar = true;
		RnCurso rn = new RnCurso();
		
		
		while(continuar){
		
			
			Scanner sc1 = new Scanner(System.in);
			System.out.println("\n -----------------------------------------");
			System.out.println("| Para sair do Sistema digite --------> 0 |");
			System.out.println("| Para cadastrar um Curso Digite -----> 1 |");
			System.out.println("| Para Listar Cursos Digite ----------> 2 |");
			System.out.println("| Para excluir um curso Digite -------> 3 |");
			System.out.println("| Para atualizar um curso Digite -----> 4 |");
			System.out.println(" -----------------------------------------\n");
			
			
			try{
				int opcao=sc1.nextInt();
				
				if(opcao==1){
					
					System.out.println("\nPara cadastrar um Curso Digite:\nNome do Curso\n");
					
					sc1.nextLine();
					String nome=sc1.nextLine();
					Date d = new Date();
					List<Turma> t= new ArrayList<Turma>();
					Curso c= new Curso(0,nome, d );
						
					try{
						if(rn.montarScriptCadastrarCurso(c)){
							System.out.println("Curso Cadastrado com sucesso");
						}else{
						System.out.println("Curso Já cadastrado");
						}
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
						
				}
				
				else if(opcao==2){
					
					ArrayList<Curso> lista=rn.montarScriptListarCursos();
					if(lista.size()!=0){
						
						SimpleDateFormat currentYear = new SimpleDateFormat("dd/mm/yyyy");
											
						for(int i=0; i<lista.size();i++){
							System.out.println("____________________________________");
							System.out.println("ID: "+lista.get(i).getId());
							System.out.println("Curso: "+lista.get(i).getNome());
							System.out.println("Data: "+currentYear.format(lista.get(i).getData()));
							System.out.println("____________________________________");
						}
					}else{
						System.out.println("Nenhum Curso Cadastrado momento");
					}
				}else if(opcao==0){
					continuar=false;
					System.out.println("Até logo");
					sc1.close();
				
				}
				else if(opcao==3){
					
					System.out.println("Digite o ID do curso a ser excluido:");
					int id=sc1.nextInt();
					
					try{
						Curso c= new Curso();
						c.setId(id);
						if(rn.montarScriptExcluirCurso(c)){
							System.out.println("Curso excluido com sucesso");
						}else{
							System.out.println("Curso nao exite");
						}
						
						
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
					
				}
				else if(opcao==4){
					
					System.out.println("Entre com o ID do curso");
					int id=sc1.nextInt();
					System.out.println("Entre com o nome Novo");
					sc1.nextLine();
					String nomeNew=sc1.nextLine();
					Curso curso = new Curso();
					curso.setNome(nomeNew);
					curso.setId(id);
						try{
							
							if(rn.montarScriptAlterarCurso(curso)){
								System.out.println("Curso Alterado com sucesso");
							}else{
								System.out.println("Curso a ser alterado nao existe");
							}
						}catch(Exception e){
							System.out.println(e.getMessage());
						}
						
					
					
				}
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			
			
		}
		
	}
}
