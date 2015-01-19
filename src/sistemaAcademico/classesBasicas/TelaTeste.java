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
			System.out.println("9 - Sair");
			
			opcao = Integer.valueOf(ler.nextInt());

			switch (opcao) {
			case 1:
				cadastrarPessoa();
				break;
			default:
				System.out.println("Opcao Inválida");
				break;
			}
			
		} while (opcao != 9);
	}

	private static void cadastrarPessoa() {
		Pessoa pessoa = new Pessoa();
		Endereco endereco = new Endereco();
		Fone fone = new Fone();
		Scanner scan = new Scanner(System.in);

		System.out.println("Digite o nome da pessoa");
		pessoa.setNome(scan.next());

		String cpfNumeros;
		String cpf;

		do {
			System.out.println("Digite o CPF da pessoa");
			cpf = scan.next();
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
		
		System.out.println("Digite o logradouro da residência da pessoa: ");
		endereco.setLogradouro(scan.next());

		System.out.println("Digite o número da residência da pessoa: ");
		endereco.setNumero(scan.next());

		System.out.println("Digite o bairro onde a pessoa mora: ");
		endereco.setBairro(scan.next());

		System.out.println("Digite a cidade onde a pessoa mora: ");
		endereco.setCidade(scan.next());

		System.out.println("Digite o estado onde a pessoa mora: ");
		endereco.setUf(scan.next());

		pessoa.setEndereco(endereco);

		System.out.println("Digite o ddd do telefone: ");
		fone.setDdd(scan.next());

		System.out.println("Digite o telefone: ");
		fone.setFone(scan.next());

		pessoa.addFones(fone);

		System.out.println("Deseja adicionar telefones adicionais? 1 - Sim e 2 - NÃO");
		int escolha = scan.nextInt();

		do {
			switch(escolha){
			case 1:
				fone = new Fone();
				System.out.println("Digite o ddd do telefone: ");
				fone.setDdd(scan.next());

				System.out.println("Digite o telefone: ");
				fone.setFone(scan.next());

				pessoa.addFones(fone);
				break;
			default:
				System.out.println("Opcao inválida");
			}
		} while (escolha != 2);
		
		RnPessoa.adicionaPessoa(pessoa);
	}

}
