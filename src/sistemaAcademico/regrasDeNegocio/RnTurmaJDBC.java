package sistemaAcademico.regrasDeNegocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.Turma;
import sistemaAcademico.daoJDBC.DaoAlunoJdbc;
import sistemaAcademico.daoJDBC.DaoTurmaJDBC;
import sistemaAcademico.daoJDBC.DaoTurmaJDBCInt;
import sistemaAcademico.exceptions.AlunoInexistenteException;
import sistemaAcademico.exceptions.AlunoMatriculadoTurmaException;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.CursoCheioException;
import sistemaAcademico.exceptions.TurmaCheiaException;
import sistemaAcademico.exceptions.TurmaExistenteException;
import sistemaAcademico.exceptions.TurmaInexistenteException;

public class RnTurmaJDBC {

	
	DaoTurmaJDBCInt dao = new DaoTurmaJDBC();
	DaoTurmaJDBC d = new DaoTurmaJDBC();	
	
	public void cadastrar (Turma turma) throws TurmaExistenteException, CursoCheioException, ConexaoException, SQLException {
		
			
				dao.cadastrarTurma(turma);
			
	
	}
	
	public void matricularAluno (Aluno aluno,Turma turma) throws TurmaCheiaException, AlunoMatriculadoTurmaException, ConexaoException, SQLException {
		
		Turma minhaTurma = new Turma();
		DaoAlunoJdbc al = new DaoAlunoJdbc();
		
		minhaTurma = dao.consultarTurma(turma.getNomeDaTurma());
			
		if (turma.getAlunosDaTurma().size() <= 50) {
			try {	
				dao.matricularAluno(aluno, minhaTurma);
				System.out.println("Matriculado com sucesso");
			} catch (ConexaoException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else throw new TurmaCheiaException();
		
	}
	
	public void remover (Turma turma) throws TurmaInexistenteException {
		
		try {
			if (dao.consultarTurma(turma.getNomeDaTurma()) != null) {
				
				dao.removerTurma(turma);
			} else throw new TurmaInexistenteException();
		} catch (ConexaoException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alterar (Turma turmaAtual) throws TurmaInexistenteException {
	
		try {
				
				dao.alterarTurma(turmaAtual);
				System.out.println("Alterado com sucesso");
		} catch (ConexaoException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
		
	}
	
	public void removerAluno(Aluno aluno,Turma turma) {
		
		try {
		
			if (turma.getAlunosDaTurma().contains(aluno) == true ) {
			
			try {
				dao.removerAluno(aluno, turma);
				System.out.println("Removido com sucesso");
			} catch (ConexaoException | SQLException e) {
				// TODO Auto-generated catch block
			}
			
		} else
			
				throw new AlunoInexistenteException();
			} catch (AlunoInexistenteException e) {
				// TODO Auto-generated catch block
			}
		
	}
	
	public Turma pesquisarTurma (String nome) {
		
		Turma t = null;
		try {
			t = dao.consultarTurma(nome);
		} catch (ConexaoException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return t;
			
	}
	
	
	public List<HashMap> listarAlunosDaTurma (Turma turma) throws SQLException, ConexaoException {
	
		List<HashMap> alunos = new ArrayList();
		alunos = d.listarAlunosDaturma(turma);
		return alunos;
		
	}
}
