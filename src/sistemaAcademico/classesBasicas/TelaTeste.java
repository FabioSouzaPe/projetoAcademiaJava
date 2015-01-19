package sistemaAcademico.classesBasicas;

import java.util.Scanner;

import sistemaAcademico.regrasDeNegocio.RnPessoa;

public class TelaTeste {

	public static void main(String[] args) {

		int opcao;
		Scanner ler = new Scanner(System.in);

		do {
			System.out.println("Opcões:\n");
			System.out.println("1 - Cadastrar pessoa");
			System.out.println("2 - Consultar pessoas cadastradas");
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
			default:
				System.out.println("Opcao Inválida");
				break;
			}
			
		} while (opcao != 9);
	}

	private static void consultarPessoa() {
		
		for (Pessoa p : RnPessoa.consultarPessoas()) {
			System.out.println(p.getNome());
			System.out.println(p.getCpf());
			System.out.println("------------");
		}
		
	}

	private static void cadastrarPessoa() {
		
		Pessoa pessoa = new Pessoa();
		Endereco endereco = new Endereco();
		Fone fone = new Fone();
		Scanner scan = new Scanner(System.in);

		System.out.println("Digite o nome da pessoa");
		pessoa.setNome(scan.nextLine());

		String cpfNumeros;
		String cpf;

		do {
			System.out.println("Digite o CPF da pessoa");
			cpf = scan.nextLine();
			cpfNumeros = cpf.replaceAll("[^0-9]", "");

			if (RnPessoa.cpfValido(cpfNumeros)) {
				pessoa.setCpf(cpf);
			} else {
				System.out.println("CPF inválido!");
			}
		} while (!RnPessoa.cpfValido(cpfNumeros));

		System.out.println("Escolha a o sexo da pessoa: 1 - Masculino e 2 - Feminino");
		
		switch (scan.nextInt()) {
		case 1:
			pessoa.setSexo('m');
			break;
		case 2:
			pessoa.setSexo('f');
			break;
		default:
			System.out.println("Opcao Invalida");
		}
		
		scan = new Scanner(System.in);
		
		System.out.println("Digite o logradouro da residência da pessoa: ");
		endereco.setLogradouro(scan.nextLine());

		System.out.println("Digite o número da residência da pessoa: ");
		endereco.setNumero(scan.nextLine());

		System.out.println("Digite o bairro onde a pessoa mora: ");
		endereco.setBairro(scan.nextLine());

		System.out.println("Digite a cidade onde a pessoa mora: ");
		endereco.setCidade(scan.nextLine());

		System.out.println("Digite o estado onde a pessoa mora: ");
		endereco.setUf(scan.nextLine());

		pessoa.setEndereco(endereco);

		System.out.println("Digite o ddd do telefone: ");
		fone.setDdd(scan.nextLine());

		System.out.println("Digite o telefone: ");
		fone.setFone(scan.nextLine());

		pessoa.addFones(fone);

		System.out.println("Deseja adicionar telefones adicionais? 1 - Sim");
		int escolha = scan.nextInt();
		
		scan = new Scanner(System.in);

		do {
			switch(escolha){
			case 1:
				fone = new Fone();
				System.out.println("Digite o ddd do telefone: ");
				fone.setDdd(scan.nextLine());

				System.out.println("Digite o telefone: ");
				fone.setFone(scan.nextLine());

				pessoa.addFones(fone);
				
				System.out.println("Deseja adicionar telefones adicionais? 1 - Sim e 2 - NÃO");
				escolha = scan.nextInt();
				
				break;
			default:
				System.out.println("Encerrado");
			}
		} while (escolha == 1);
		
		RnPessoa.adicionaPessoa(pessoa);
		
		scan = new Scanner(System.in);
	}

}
