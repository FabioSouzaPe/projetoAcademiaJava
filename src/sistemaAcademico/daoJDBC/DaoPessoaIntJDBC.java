package sistemaAcademico.daoJDBC;

import java.sql.SQLException;
import java.util.List;

import sistemaAcademico.classesBasicas.Endereco;
import sistemaAcademico.classesBasicas.Fone;
import sistemaAcademico.classesBasicas.Pessoa;

public interface DaoPessoaIntJDBC {
	public List<Pessoa> getListaPessoas() throws SQLException, ClassNotFoundException;
	public int addPessoa(Pessoa pessoa) throws SQLException, ClassNotFoundException;
	public boolean verificaSeCadastrado(String cpf) throws ClassNotFoundException, SQLException;
	public Pessoa buscaPorCpf(String cpf) throws ClassNotFoundException, SQLException;
	public void alterarEnderecoPessoa(Pessoa p, Endereco en) throws ClassNotFoundException, SQLException;
	public void alterarFonePessoa(Pessoa p, Fone f) throws ClassNotFoundException, SQLException;
}
