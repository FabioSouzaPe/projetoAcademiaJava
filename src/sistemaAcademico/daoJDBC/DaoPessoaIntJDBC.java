package sistemaAcademico.daoJDBC;

import java.sql.SQLException;
import java.util.List;

import sistemaAcademico.classesBasicas.Endereco;
import sistemaAcademico.classesBasicas.Fone;
import sistemaAcademico.classesBasicas.Pessoa;
import sistemaAcademico.exceptions.ConexaoException;

public interface DaoPessoaIntJDBC {
	public List<Pessoa> getListaPessoas() throws SQLException, ClassNotFoundException, ConexaoException;
	public int addPessoa(Pessoa pessoa) throws SQLException, ClassNotFoundException, ConexaoException;
	public Pessoa buscaPorCpf(String cpf) throws ClassNotFoundException, SQLException, ConexaoException;
	public void alterarPessoa(Endereco endereco) throws ClassNotFoundException, SQLException, ConexaoException;
	public void alterarPessoa(Fone fone) throws ClassNotFoundException, SQLException, ConexaoException;
	public void adicionaFone(Fone fone, int id) throws ClassNotFoundException, SQLException, ConexaoException;
	public void removerPessoa(int id, int idEndereco) throws ClassNotFoundException, SQLException, ConexaoException;
}
