package sistemaAcademico.classesBasicas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

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
			System.out.println("3 - Remover pessoa cadastrada");
			System.out.println("9 - Sair");
			
			opcao = Integer.valueOf(ler.nextInt());

			switch (opcao) {
			case 1:
				cadastrarPessoa();
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
			System.out
					.println("Digite o CPF da pessoa que desenha remover do registro: ");

			RnPessoa.removerPessoa(scan.nextLine());
		} catch (PessoaInexistenteException e) {
			System.out.println(e.getMessage());
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
			System.out.println("Logradouro: " + p.getEndereco().getLogradouro());
			System.out.println("Numero: " + p.getEndereco().getNumero());
			System.out.println("Bairro: " + p.getEndereco().getBairro());
			System.out.println("Cidade: " + p.getEndereco().getCidade());
			System.out.println("Estado: " + p.getEndereco().getUf());
			System.out.println("Telefone: " + p.getFones().get(0).getDdd() + " " + p.getFones().get(0).getFone());
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
				System.out.println("Logradouro: " + p.getEndereco().getLogradouro());
				System.out.println("Numero: " + p.getEndereco().getNumero());
				System.out.println("Bairro: " + p.getEndereco().getBairro());
				System.out.println("Cidade: " + p.getEndereco().getCidade());
				System.out.println("Estado: " + p.getEndereco().getUf());
				System.out.println("Telefone: " + p.getFones().get(0).getDdd() + " " + p.getFones().get(0).getFone());
				System.out.println("------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}

	private static void cadastrarPessoa() {

		try {
			Endereco e1 = new Endereco(0, "Rua 3", "Bairro 3", "N 3", "Cidade 3", "Uf 3");
			Fone f1 = new Fone(0, "(81)", "3464-8400");
			ArrayList<Fone> fl1 = new ArrayList<Fone>();
			fl1.add(f1);
			Pessoa p1 = new Pessoa(RnPessoa.consultarPessoas().size() + 1,
					"Joaquim Petronio dos Santos", "101.285.544-95", 'm', e1,
					Escolaridade.TECNICO, fl1);
			RnPessoa.adicionaPessoa(p1);

/*			Endereco e2 = new Endereco(0, "Rua", "Bairro", "Numero", "Cidade", "Uf");
			Fone f2 = new Fone(0, "(81)", "3479-3074");
			ArrayList<Fone> fl2 = new ArrayList<Fone>();
			fl2.add(f2);
			Pessoa p2 = new Pessoa(RnPessoa.consultarPessoas().size() + 1,
					"Mackson Luiz", "101.285.544-95", 'm', e2,
					Escolaridade.TECNICO, fl2);
			RnPessoa.adicionaPessoa(p2);

			Endereco e3 = new Endereco(0, "Rua 2", "Bairro 2", "N 2", "Cidade 2", "Uf 2");
			Fone f3 = new Fone(0, "(81)", "9146-5367");
			Fone f4 = new Fone(0, "(81)", "8337-0310");
			ArrayList<Fone> fl3 = new ArrayList<Fone>();
			fl3.add(f3);
			fl3.add(f4);
			Pessoa p3 = new Pessoa(RnPessoa.consultarPessoas().size() + 1,
					"Zé bilu", "842.621.514-92", 'm', e3,
					Escolaridade.ENSINO_MEDIO, fl3);
			RnPessoa.adicionaPessoa(p3);*/

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}

}
