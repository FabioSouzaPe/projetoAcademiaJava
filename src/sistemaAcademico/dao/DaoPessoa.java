package sistemaAcademico.dao;

import java.util.ArrayList;
import java.util.Scanner;

import sistemAcademico.exceptions.PessoaInexistenteException;
import sistemaAcademico.classesBasicas.Pessoa;

public class DaoPessoa implements DaoPessoaInt {
	
	private ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();

	public ArrayList<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void addPessoa(Pessoa pessoa) {
		this.listaPessoas.add(pessoa);
	}
	
	public Pessoa pesquisarPessoa(String cpf){
		
		Pessoa pessoaAchada = null;
		
		for (int i = 0; i < getListaPessoas().size(); i++) {
			if(getListaPessoas().get(i).getCpf().equals(cpf)){
				pessoaAchada = getListaPessoas().get(i);
				break;
			}
		}
		
		if (pessoaAchada == null){
			throw new PessoaInexistenteException();
		}
		else {
			return pessoaAchada;
		}
	}

	@Override
	public void removerPessoa(Pessoa p) {
		
		Scanner ler = new Scanner(System.in);
		
		System.out.println("Deseja realmente remover o registro dessa pessoa? 1 - Sim");
		
		if(ler.nextInt() == 1){
			listaPessoas.remove(p);
			System.out.println("Pessoa removida");
		}
		else {
			System.out.println("Cancelado");
		}
		
	}
}
