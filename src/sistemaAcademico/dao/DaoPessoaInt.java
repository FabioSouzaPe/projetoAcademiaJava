package sistemaAcademico.dao;

import java.util.ArrayList;

import sistemaAcademico.classesBasicas.Pessoa;

public interface DaoPessoaInterface {
	public ArrayList<Pessoa> getListaPessoas();
	public void addPessoa(Pessoa pessoa);
}
