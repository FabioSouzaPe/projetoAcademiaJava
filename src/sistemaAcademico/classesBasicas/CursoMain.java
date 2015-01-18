package sistemaAcademico.classesBasicas;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.DataBindingException;

public class CursoMain {
	 public static void main(String[] args) {
		boolean continuar = true;
		
		
		
		while(continuar){
			Scanner sc1 = new Scanner(System.in);
			System.out.println("\nPara cadastrar um Curso Digite 1");
			System.out.println("Para consultar Curso Digite 2");
			System.out.println("Para excluir um curso Digite 3");
			System.out.println("Para sair do Sistema digite 0\n");
			
			try{
				int opcao=sc1.nextInt();
				if(opcao==1){
					System.out.println("\nPara cadastrar um Curso Digite:\nID da Curso\nNome do Curso\nData de criação do Curso\nTurmas do Curso\n");
					
					int id= sc1.nextInt();
					String nome=sc1.next();
					Date d = new Date();
					Curso c= new Curso(id, nome, d, null);
					CursoCadastrar cc= new CursoCadastrar();
					if(cc.cadastrar(c)){
						System.out.println("Curso Cadastrado com sucesso");
					}else{
						System.out.println("Este Curso Já foi cadastrado");
					}
					
					
					
				}else if(opcao==2){
					CursoConsultar cc= new CursoConsultar();
					if(cc.consultar().size()!=0){
						
						for(int i=0; i<cc.consultar().size();i++){
							System.out.println("------------");
							System.out.println(cc.consultar().get(i).getId());
							System.out.println(cc.consultar().get(i).getNome());
							System.out.println(cc.consultar().get(i).getData());
							System.out.println(cc.consultar().get(i).getTurma());
							System.out.println("------------");
						}
					}else{
						System.out.println("Nenhum Curso Cadastrado momento");
					}
				}else if(opcao==0){
					continuar=false;
					System.out.println("Até logo");
				}else if(opcao==3){
					System.out.println("Digite o nome do curso a ser excluido:");
					String nome=sc1.next();
					CursoExcluir ce= new CursoExcluir();
					if(ce.excluir(nome)){
						System.out.println("O Curso: "+nome+" Foi excluido com sucesso");
					}else{
						System.out.println("Nenhum curso cadastrado com esse nome");
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
