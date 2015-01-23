package sistemaAcademico.regrasDeNegocio;

import java.sql.SQLException;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.daoJDBC.DaoAlunoJDBCInt;
import sistemaAcademico.daoJDBC.DaoAlunoJdbc;
import sistemaAcademico.exceptions.AlunoExistenteException;
import sistemaAcademico.exceptions.AlunoInexistenteException;
import sistemaAcademico.exceptions.ConexaoException;

public class RnAlunoJDBC {
	
	private DaoAlunoJDBCInt dao;
	
	public RnAlunoJDBC(){
		dao = new DaoAlunoJdbc();
	}
	
	public void cadastrarAluno(Aluno aluno, int chave) throws ConexaoException, SQLException, AlunoExistenteException{
		try{
			Aluno teste = pesquisar(aluno.getMatricula());
			throw new AlunoExistenteException();
		}catch(AlunoInexistenteException e){
			dao.inserir(aluno, chave);
		}
	}
	
	public Aluno pesquisar(String matricula) throws ConexaoException, AlunoInexistenteException, SQLException{
		return dao.pesquisar(matricula);
	}
	
	public void remover(Aluno aluno) throws ConexaoException, SQLException{
		dao.remover(aluno);;
	}
	
	public void alterar(Aluno aluno) throws ConexaoException, SQLException{
		dao.alterar(aluno);
	}

}
