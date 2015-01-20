package sistemaAcademico.regrasDeNegocio;

import java.util.ArrayList;

import sistemAcademico.exceptions.AlunoExistenteException;
import sistemAcademico.exceptions.PessoaInexistenteException;
import sistemaAcademico.classesBasicas.Pessoa;
import sistemaAcademico.dao.DaoPessoa;
import sistemaAcademico.dao.DaoPessoaInt;

public class RnPessoa {
	
	static DaoPessoaInt daoPessoa = new DaoPessoa();

	public RnPessoa() {}

	
	// Metodo para validar o CPF
	public static boolean cpfValido(String strCpf) {
		int d1, d2;
		int digito1, digito2, resto;
		int digitoCPF;
		String nDigResult;

		d1 = d2 = 0;
		digito1 = digito2 = resto = 0;

		for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
			digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount))
					.intValue();

			d1 = d1 + (11 - nCount) * digitoCPF;

			d2 = d2 + (12 - nCount) * digitoCPF;
		}

		resto = (d1 % 11);

		if (resto < 2) {
			digito1 = 0;
		} else {
			digito1 = 11 - resto;
		}

		d2 += 2 * digito1;

		resto = (d2 % 11);

		if (resto < 2) {
			digito2 = 0;
		} else {
			digito2 = 11 - resto;
		}

		String nDigVerific = strCpf.substring(strCpf.length() - 2,
				strCpf.length());

		nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

		return nDigVerific.equals(nDigResult);
	}

	public static boolean sePessoaExiste(String strCpf) {

		boolean verificacao = false;
		
		//verifica registro por registro na lista de pessoas se existe um CPF igual
		for (Pessoa p : daoPessoa.getListaPessoas()) {

			if (p.getCpf().equals(strCpf)) {
				verificacao = true;
			}
		}

		return verificacao;
	}
	
	public static void adicionaPessoa(Pessoa pessoa){

		//Nega se a pessoa existe para adicionar na lista
		if (!sePessoaExiste(pessoa.getCpf())){
			daoPessoa.addPessoa(pessoa);
			System.out.println("Pessoa cadastrada com sucesso");
		} 
		
		//Se ja existe exite uma mensagem de aviso
		else {
			System.out.println("A pessoa ja existe no banco de dados!");
		} 

	}

	public static ArrayList<Pessoa> consultarPessoas() {
		
		//Retorna a lista de pessoas cadastradas
		return daoPessoa.getListaPessoas();
	}
	
	public static Pessoa pesquisarPessoa(String cpf)/* throws PessoaInexistenteException*/{
		
		return daoPessoa.pesquisarPessoa(cpf);
	}


	public static void removerPessoa(String cpf) {
		
		Pessoa p = daoPessoa.pesquisarPessoa(cpf);
		
		daoPessoa.removerPessoa(p);
	}
}
