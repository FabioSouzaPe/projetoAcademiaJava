package sistemaAcademico.regrasDeNegocio;

import java.sql.SQLException;
import java.util.ArrayList;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.daoJDBC.DaoAlunoJDBCInt;
import sistemaAcademico.daoJDBC.DaoAlunoJdbc;
import sistemaAcademico.exceptions.AlunoExistenteException;
import sistemaAcademico.exceptions.AlunoInexistenteException;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.ErroSQLException;

public class RnAlunoJDBC {
	
	private DaoAlunoJDBCInt dao;
	
	public RnAlunoJDBC(){
		dao = new DaoAlunoJdbc();
	}
	
	public void cadastrarAluno(Aluno aluno, int chavePessoa, int chaveHistorico) throws ConexaoException, ErroSQLException, AlunoExistenteException{
		try{
			Aluno teste = pesquisar(aluno.getMatricula());
			throw new AlunoExistenteException();
		}catch(AlunoInexistenteException e){
			dao.inserir(aluno, chavePessoa, chaveHistorico);
		}
	}
	
	public Aluno pesquisar(String matricula) throws ConexaoException, AlunoInexistenteException, ErroSQLException{
		return dao.pesquisar(matricula);
	}
	
	public void remover(Aluno aluno) throws ConexaoException, ErroSQLException{
		dao.remover(aluno);;
	}
	
	public void alterar(Aluno aluno) throws ConexaoException, ErroSQLException{
		dao.alterar(aluno);
	}
	
	public ArrayList<Aluno> listar() throws ConexaoException, ErroSQLException{
		return dao.listar();
	}

}
