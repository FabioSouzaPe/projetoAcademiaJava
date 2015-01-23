
package sistemaAcademico.daoJDBC;

import java.sql.SQLException;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.exceptions.AlunoInexistenteException;
import sistemaAcademico.exceptions.ConexaoException;

public interface DaoAlunoJDBCInt {
	
	public void inserir(Aluno aluno, int chaveHistorico, int chavePessoa) throws ConexaoException, SQLException;
	public void remover(Aluno aluno) throws ConexaoException, SQLException;
	public Aluno pesquisar(String matricula) throws ConexaoException, AlunoInexistenteException, SQLException;
	public void alterar(Aluno aluno) throws ConexaoException, SQLException;

}
