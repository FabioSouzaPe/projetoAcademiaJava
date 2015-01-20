package sistemaAcademico.classesBasicas;

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
			System.out.println("------------");
			
		} catch (PessoaInexistenteException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void consultarPessoa() {
		
		System.out.println("------------");
		for (Pessoa p : RnPessoa.consultarPessoas()) {
			System.out.println("ID: " + p.getId());
			System.out.println("Nome: " + p.getNome());
			System.out.println("CPF: " + p.getCpf());
			System.out.println("------------");
		}
		
	}

	private static void cadastrarPessoa() {
		
		Endereco e1 = new Endereco(0, "a", "b", "c", "d", "e");
		Fone f1 = new Fone(0, "(81)", "3479-3074");
		ArrayList<Fone> fl1 = new ArrayList<Fone>();
		fl1.add(f1);
		Pessoa p1 = new Pessoa(RnPessoa.consultarPessoas().size()+1, "Mackson Luiz", "101.285.544-95",
				'm', e1, Escolaridade.TECNICO, fl1);
		RnPessoa.adicionaPessoa(p1);
		
		
		
		Endereco e2 = new Endereco(0, "a", "b", "c", "d", "e");
		Fone f2 = new Fone(0, "(81)", "3479-3074");
		ArrayList<Fone> fl2 = new ArrayList<Fone>();
		fl2.add(f2);
		Pessoa p2 = new Pessoa(RnPessoa.consultarPessoas().size()+1, "Mackson Luiz", "101.285.544-95",
				'm', e2, Escolaridade.TECNICO, fl2);
		RnPessoa.adicionaPessoa(p2);
		
		
		Endereco e3 = new Endereco(0, "a", "b", "c", "d", "e");
		Fone f3 = new Fone(0, "(81)", "3479-3074");
		ArrayList<Fone> fl3 = new ArrayList<Fone>();
		fl2.add(f3);
		Pessoa p3 = new Pessoa(RnPessoa.consultarPessoas().size()+1, "Zé bilu", "842.621.514-92",
				'm', e3, Escolaridade.ENSINO_MEDIO, fl3);
		RnPessoa.adicionaPessoa(p3);
	}

}
