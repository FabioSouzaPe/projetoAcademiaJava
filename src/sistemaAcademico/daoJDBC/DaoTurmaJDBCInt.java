package sistemaAcademico.daoJDBC;

import java.sql.SQLException;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.Turma;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.ErroSQLException;


public interface DaoTurmaJDBCInt {

	
	public abstract void cadastrarTurma(Turma t) throws ConexaoException,SQLException;;
	public abstract void removerTurma(Turma TurmaRemovida) throws ConexaoException, SQLException;;
	public abstract Turma consultarTurma(String nome) throws ConexaoException, SQLException;
	//public abstract List<Turma> consultarTurmas();
	public abstract void alterarTurma(Turma turmaAtual) throws SQLException, ConexaoException;
	public abstract void matricularAluno (Aluno aluno,Turma turma) throws ConexaoException, SQLException;
	public abstract void removerAluno (Aluno aluno, Turma turma) throws ConexaoException, SQLException;;
	
	
}
