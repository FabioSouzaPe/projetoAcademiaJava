package sistemaAcademico.daoJDBC;

import java.util.ArrayList;

import sistemaAcademico.classesBasicas.Pessoa;

public interface DaoPessoaIntJDBC {
	public ArrayList<Pessoa> getListaPessoas();
	public void addPessoa(Pessoa pessoa);
	public void removerPessoa(Pessoa p);
}
