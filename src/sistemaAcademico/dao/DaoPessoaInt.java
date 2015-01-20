package sistemaAcademico.dao;

import java.util.ArrayList;

import sistemaAcademico.classesBasicas.Pessoa;

public interface DaoPessoaInt {
	public ArrayList<Pessoa> getListaPessoas();
	public void addPessoa(Pessoa pessoa);
	public void removerPessoa(Pessoa p);
}
