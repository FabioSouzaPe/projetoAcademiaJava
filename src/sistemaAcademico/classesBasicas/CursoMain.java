package sistemaAcademico.classesBasicas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import sistemAcademico.exceptions.CursoExistenteException;
import sistemAcademico.exceptions.CursoInexistenteException;
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
			System.out.println("| Para atualizar um curso Digite -----> 4 |");
			System.out.println("| Para consultar Qtd de Turmas Digite-> 5 |");
			System.out.println(" -----------------------------------------\n");
			
			
			try{
				int opcao=sc1.nextInt();
				
				if(opcao==1){
					
					System.out.println("\nPara cadastrar um Curso Digite:\nID da Curso\nNome do Curso\nData de criação do Curso\nTurmas do Curso\n");
					
					int id= sc1.nextInt();
					sc1.nextLine();
					String nome=sc1.nextLine();
					Date d = new Date();
					List<Turma> t= new ArrayList<Turma>();
					Curso c= new Curso(id, nome, d,t );
						
					try{
						if(rn.verificacaoCadastrarCurso(c)){
							System.out.println("Curso Cadastrado com sucesso");
						}else{
						System.out.println("Outro problema ao cadastrar curso");
						}
					}catch(CursoExistenteException e){
						System.out.println(e.getMessage());
					}
					
						
				}else if(opcao==2){
					
					if(dao.consultarTudo().size()!=0){
						
						SimpleDateFormat currentYear = new SimpleDateFormat("dd/mm/yyyy");
											
						for(int i=0; i<dao.consultarTudo().size();i++){
							System.out.println("____________________________________");
							System.out.println("ID: "+dao.consultarTudo().get(i).getId());
							System.out.println("Curso: "+dao.consultarTudo().get(i).getNome());
							System.out.println("Data: "+currentYear.format(dao.consultarTudo().get(i).getData()));
							System.out.println("Turmas: "+dao.consultarTudo().get(i).getTurma());
							System.out.println("____________________________________");
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
					sc1.nextLine();
					String nome=sc1.nextLine();
					
					try{
						
						if(rn.verificacaoExcluirCurso(nome)){
							System.out.println("Curso excluido com sucesso");
						}else{
							System.out.println("Outro problema ao excluir curso");
						}
						
						
					}catch(CursoInexistenteException e){
						System.out.println(e.getMessage());
					}
					
					
						
					
				}else if(opcao==4){
					
					System.out.println("Entre com o nome antigo");
					sc1.nextLine();
					String nomeOld=sc1.nextLine();
					System.out.println("Entre com o nome Novo");
					String nomeNew=sc1.nextLine();
					
						try{
							
							if(rn.verificacaoAlterarCurso(nomeOld,nomeNew)){
								System.out.println("Curso Alterado com sucesso");
							}else{
								System.out.println("Outro problema ao alterar curso");
							}
						}catch(CursoInexistenteException e){
							System.out.println(e.getMessage());
						}
						
					
					
				}else if(opcao==5){
					System.out.println("Entre com o nome do curso para verificar a quantidade de turmas");
					sc1.nextLine();
					String nomeCurso=sc1.nextLine();
					
					try{
						
						int qtd=rn.quantidadeDeTurmas(nomeCurso);
						System.out.println("Quantidade de Turmas do curso de "+nomeCurso+": "+qtd);
						
					}catch(CursoInexistenteException e){
						System.out.println(e.getMessage());
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
