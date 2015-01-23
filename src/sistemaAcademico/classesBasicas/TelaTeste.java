package sistemaAcademico.classesBasicas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import sistemAcademico.exceptions.PessoaInexistenteException;
import sistemaAcademico.enuns.Escolaridade;
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
			default:
				System.out.println("Opcao Inválida");
				break;
			}
			
		} while (opcao != 9);
	}

	private static void alterarPessoa() {
		try {
			
			Scanner scan = new Scanner(System.in);
			System.out.println("Digite o CPF da pessoa que desenha remover do registro: ");
			
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
				
				p.setEndereco(en);
				
				RnPessoa.alterarPessoa(p, en);
				
				System.out.println("Dados Alterados");
			} 
			
			else if (opcao == 2)  {
				
				Fone f = new Fone();
				scan = new Scanner(System.in);
				
				System.out.println("Digite o novo DDD: ");
				f.setDdd(new Scanner(System.in).nextLine());
				
				System.out.println("Digite o novo Fone: ");
				f.setFone(new Scanner(System.in).nextLine());
				
				RnPessoa.alterarPessoa(p, f);
				
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
		}
	}

	private static void consultarPessoa() {
		
		System.out.println("------------");
		try {
			for (Pessoa p : RnPessoa.consultarPessoas()) {
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}

	private static void cadastrarPessoa() throws MySQLIntegrityConstraintViolationException {
		int teste;
		try {
			Endereco e1 = new Endereco(0, "54340-795", "Rua 1", "Bairro 1",
					"N 1", "Cidade 1", "Uf 1");
			Fone f1 = new Fone(0, "(81)", "3464-8400");
			Fone f2 = new Fone(0, "(81)", "4002-8922");
			ArrayList<Fone> fl1 = new ArrayList<Fone>();
			fl1.add(f1);
			fl1.add(f2);
			Pessoa p1 = new Pessoa(RnPessoa.consultarPessoas().size() + 1,
					"Mackson Luiz Izário de Lima", "194.570.102-13", 'm', e1,
					fl1);

			if (RnPessoa.cpfValido(p1.getCpf().replaceAll("[^0-9]", ""))) {
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
		}
	}

}
