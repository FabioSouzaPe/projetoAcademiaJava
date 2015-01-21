package sistemaAcademico.daoJDBC;

import java.sql.SQLException;
import java.util.ArrayList;

import sistemaAcademico.classesBasicas.Pessoa;

public interface DaoPessoaIntJDBC {
	public ArrayList<Pessoa> getListaPessoas() throws SQLException;
	public void addPessoa(Pessoa pessoa) throws SQLException;
	public void removerPessoa(Pessoa p);
}
