package sistemaAcademico.daoJDBC;

import java.sql.SQLException;
import java.util.List;

import sistemaAcademico.classesBasicas.Endereco;
import sistemaAcademico.classesBasicas.Fone;
import sistemaAcademico.classesBasicas.Pessoa;

public interface DaoPessoaIntJDBC {
	public List<Pessoa> getListaPessoas() throws SQLException, ClassNotFoundException;
	public int addPessoa(Pessoa pessoa) throws SQLException, ClassNotFoundException;
	public Pessoa buscaPorCpf(String cpf) throws ClassNotFoundException, SQLException;
	public void alterarPessoa(Endereco endereco) throws ClassNotFoundException, SQLException;
	public void alterarPessoa(Fone fone) throws ClassNotFoundException, SQLException;
	public void adicionaFone(Fone fone, int id) throws ClassNotFoundException, SQLException;
	public void removerPessoa(int id, int idEndereco) throws ClassNotFoundException, SQLException;
}
