package sistemaAcademico.classesBasicas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import sistemAcademico.exceptions.PessoaInexistenteException;
import sistemaAcademico.enuns.Escolaridade;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.regrasDeNegocio.RnPessoa;

public class TelaTeste {

	public static void main(String[] args) throws PessoaInexistenteException {

		int opcao;
		Scanner ler = new Scanner(System.in);

		do {
			System.out.println("Opcões:\n");
			System.out.println("1 - Cadastrar pessoa");
			System.out.println("2 - Consultar pessoas cadastradas");
			System.out.println("3 - Pesquisar pessoa cadastrada");
			System.out.println("4 - Alterar dados de uma pessoa");
			System.out.println("5 - Remover pessoa do banco de dados");
			System.out.println("9 - Sair");
			
			opcao = Integer.valueOf(ler.nextInt());

			switch (opcao) {
			case 1:
				try {
					cadastrarPessoa();
				} catch (MySQLIntegrityConstraintViolationException e) {
					System.out.println("CPF já cadastrado!");
					e.printStackTrace();
				}
				ler = new Scanner(System.in);
				break;
			case 2:
				consultarPessoa();
				ler = new Scanner(System.in);
				break;
			case 3:
				pesquisarPessoa();
				break;
			case 4:
				alterarPessoa();
				break;
			case 5:	
				removerPessoa();
				break;
			default:
				System.out.println("Opcao Inválida");
				break;
			}
			
		} while (opcao != 9);
	}

	private static void removerPessoa() {
		try {

			Scanner scan = new Scanner(System.in);
			System.out.println("Digite o CPF da pessoa que desenha remover do registro: ");

			Pessoa p = RnPessoa.pesquisarPessoa(scan.nextLine());
			
			RnPessoa.removerPessoa(p.getId(), p.getEndereco().getId());

		} catch (PessoaInexistenteException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void alterarPessoa() {
		try {
			
			Scanner scan = new Scanner(System.in);
			System.out.println("Digite o CPF da pessoa que desenha alterar: ");
			
			Pessoa p = RnPessoa.pesquisarPessoa(scan.nextLine());
			
			System.out.println("O que deseja alterar dessa pessoa? 1 - Endereco, 2 - Fone");
			
			int opcao = new Scanner(System.in).nextInt();
			
			if (opcao == 1) {
				
				Endereco en = new Endereco();
				scan = new Scanner(System.in);
				
				System.out.println("Digite o novo Cep: ");
				en.setCep(scan.nextLine());
				
				System.out.println("Digite o novo Logradouro: ");
				en.setLogradouro(scan.nextLine());
				
				System.out.println("Digite o novo Bairro: ");
				en.setBairro(scan.nextLine());
				
				System.out.println("Digite o novo Numero: ");
				en.setNumero(scan.nextLine());
				
				System.out.println("Digite a nova Cidade: ");
				en.setCidade(scan.nextLine());
				
				System.out.println("Digite o novo UF: ");
				en.setUf(scan.nextLine());
				
				en.setId(p.getEndereco().getId());
				
				p.setEndereco(en);
				
				RnPessoa.alterarPessoa(en);
				
				System.out.println("Dados Alterados");
			} 
			
			else if (opcao == 2)  {
				
				System.out.println("Telefones cadastrados: " + p.getFones().size());
				System.out.println("Qual ação deseja? - 1 Cadastrar novo Número, 2 - Alterar um dos números");
				
				opcao =  new Scanner(System.in).nextInt();
				
				Fone f = new Fone();
				
				System.out.println("Digite o DDD: ");
				f.setDdd(new Scanner(System.in).nextLine());
				
				System.out.println("Digite o Fone: ");
				f.setFone(new Scanner(System.in).nextLine());
				
				switch (opcao){
					case 1:
						RnPessoa.adicionaFone(f, p.getId());
						break;
					case 2:
						
						System.out.println("Qual deseja alterar?");
						
						for (int i = 0; i < p.getFones().size(); i++){
							System.out.println("Fone " + (i+1) + ": " + p.getFones().get(i).getDdd() +
									" " + p.getFones().get(i).getFone());
						}
						
						opcao = new Scanner(System.in).nextInt();
						
						f.setId(p.getFones().get(opcao-1).getId());
						
						RnPessoa.alterarPessoa(f);
						break;
				}
				
				System.out.println("Dados Alterados");
				
			} else {
				System.out.println("Cancelado");
			}
			
		} catch (PessoaInexistenteException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void pesquisarPessoa(){		
		
		try {
			Scanner scan = new Scanner(System.in);
			System.out
					.println("Digite o CPF da pessoa que desenha pesquisar o registro: ");

			Pessoa p = RnPessoa.pesquisarPessoa(scan.nextLine());

			System.out.println("------------");
			System.out.println("ID: " + p.getId());
			System.out.println("Nome: " + p.getNome());
			System.out.println("CPF: " + p.getCpf());
			System.out.println("CEP: " + p.getEndereco().getCep());
			System.out.println("Logradouro: " + p.getEndereco().getLogradouro());
			System.out.println("Numero: " + p.getEndereco().getNumero());
			System.out.println("Bairro: " + p.getEndereco().getBairro());
			System.out.println("Cidade: " + p.getEndereco().getCidade());
			System.out.println("Estado: " + p.getEndereco().getUf());
			
			for (Fone fone : p.getFones()) {
				System.out.println("Telefone: " + fone.getDdd() + " " + fone.getFone());
			}
			System.out.println("------------");
			
		} catch (PessoaInexistenteException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void consultarPessoa() {
		
		System.out.println("------------");
		try {
			for (Pessoa p : RnPessoa.consultarPessoas()) {
				System.out.println("ID: " + p.getId());
				System.out.println("Nome: " + p.getNome());
				System.out.println("CPF: " + p.getCpf());
				System.out.println("Sexo: " + p.getSexo());
				System.out.println("CEP: " + p.getEndereco().getCep());
				System.out.println("Logradouro: " + p.getEndereco().getLogradouro());
				System.out.println("Numero: " + p.getEndereco().getNumero());
				System.out.println("Bairro: " + p.getEndereco().getBairro());
				System.out.println("Cidade: " + p.getEndereco().getCidade());
				System.out.println("Estado: " + p.getEndereco().getUf());
				
				for (Fone fone : p.getFones()) {
					System.out.println("Telefone: " + fone.getDdd() + " " + fone.getFone());
				}
				System.out.println("------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (ConexaoException e) {
			e.printStackTrace();
		}
		
	}

	private static void cadastrarPessoa() throws MySQLIntegrityConstraintViolationException {
		int teste;
		try {
			Endereco e1 = new Endereco(0, "54340-430", "Rua 5", "Bairro 5",
					"N 5", "Cidade 5", "Uf 5");
			Fone f1 = new Fone(0, "(81)", "2548-5412");
			Fone f2 = new Fone(0, "(81)", "5152-5122");
			Fone f3 = new Fone(0, "(81)", "2522-5428");
			Pessoa p1 = new Pessoa(0,
					"Larissa Arlequina de Souza", "986.544.263-90", 'F', e1,
					f1, f2, f3);

			if (RnPessoa.cpfValido(p1.getCpf())) {
				teste = RnPessoa.adicionaPessoa(p1);
				if (teste == 0) {
					System.out.println("Pessoa já existe no banco de dados!");
				} else {
					System.out.println("Pessoa cadastrada com sucesso!");
				}
			} else {
				System.out.println("CPF Inválido!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
