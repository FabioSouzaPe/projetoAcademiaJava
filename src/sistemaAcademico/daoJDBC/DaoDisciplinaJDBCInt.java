package sistemaAcademico.daoJDBC;

import java.sql.SQLException;

import sistemaAcademico.classesBasicas.Disciplina;
import sistemaAcademico.classesBasicas.Turma;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.ErroSQLException;

public interface DaoDisciplinaJDBCInt {

	
	public abstract void cadastrarDisciplina(Disciplina disciplina) throws SQLException, ConexaoException;
	public abstract void removerDisciplina(Disciplina disciplinaRemovida) throws ConexaoException, SQLException;
	public abstract void alterarDisciplina (Disciplina d) throws ConexaoException, SQLException;
	public abstract Disciplina pesquisarDisciplina(String nome) throws SQLException, ConexaoException;
	public abstract void inserDisciplinaEmTurma(Disciplina novaDisciplina, Turma turma) throws ConexaoException, SQLException;
	public abstract void removerDisciplinaDeTurma (Turma turma) throws ConexaoException, SQLException;
	
}
