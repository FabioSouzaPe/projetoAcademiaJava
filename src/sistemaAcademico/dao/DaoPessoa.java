package sistemaAcademico.dao;

import java.util.ArrayList;

import sistemaAcademico.classesBasicas.Pessoa;

public class DaoPessoa implements DaoPessoaInterface {
	
	private ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();

	public ArrayList<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void addPessoa(Pessoa pessoa) {
		this.listaPessoas.add(pessoa);
	}
}
