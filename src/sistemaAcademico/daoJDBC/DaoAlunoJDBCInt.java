
package sistemaAcademico.daoJDBC;

import java.util.ArrayList;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.exceptions.AlunoInexistenteException;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.ErroSQLException;

public interface DaoAlunoJDBCInt {
	
	public void inserir(Aluno aluno, int chavePessoa, int chaveHistorico) throws ConexaoException, ErroSQLException;
	public void remover(Aluno aluno) throws ConexaoException, ErroSQLException;
	public Aluno pesquisar(String matricula) throws ConexaoException, AlunoInexistenteException, ErroSQLException;
	public void alterar(Aluno aluno) throws ConexaoException, ErroSQLException;
	public ArrayList<Aluno> listar() throws ConexaoException, ErroSQLException;

}
