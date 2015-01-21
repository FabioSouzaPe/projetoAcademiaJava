package sistemaAcademico.daoJDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistemaAcademico.classesBasicas.Pessoa;

public interface DaoPessoaIntJDBC {
	public List<Pessoa> getListaPessoas() throws SQLException, ClassNotFoundException;
	public void addPessoa(Pessoa pessoa) throws SQLException, ClassNotFoundException;
	public boolean verificaSeCadastrado(String cpf) throws ClassNotFoundException, SQLException;
	public Pessoa buscaPorCpf(String cpf) throws ClassNotFoundException, SQLException;
	public void removerPessoa(Pessoa p);
}
