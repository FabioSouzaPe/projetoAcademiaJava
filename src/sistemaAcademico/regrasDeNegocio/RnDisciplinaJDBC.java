package sistemaAcademico.regrasDeNegocio;

import java.sql.SQLException;


import sistemaAcademico.classesBasicas.Disciplina;
import sistemaAcademico.classesBasicas.Turma;
import sistemaAcademico.daoJDBC.DaoDisciplinaJDBC;
import sistemaAcademico.daoJDBC.DaoDisciplinaJDBCInt;
import sistemaAcademico.daoJDBC.DaoTurmaJDBC;
import sistemaAcademico.exceptions.AlunoMatriculadoTurmaException;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.CursoCheioException;
import sistemaAcademico.exceptions.TurmaCheiaException;
import sistemaAcademico.exceptions.TurmaExistenteException;
import sistemaAcademico.exceptions.TurmaInexistenteException;

public class RnDisciplinaJDBC {

	
	DaoDisciplinaJDBCInt dao = new DaoDisciplinaJDBC();
	DaoDisciplinaJDBC d = new DaoDisciplinaJDBC();	
	
	
	
	// Regra de negócio para cadastro de uma disciplina
	public void cadastrarDisciplina (Disciplina disciplina) throws TurmaExistenteException, CursoCheioException, ConexaoException, SQLException {
		
			
		try {
				dao.cadastrarDisciplina(disciplina);
			} catch (ConexaoException | SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
	
	}
	
	public void	InserirDisciplinaEmTurma (Disciplina disciplina,Turma turma) throws TurmaCheiaException, AlunoMatriculadoTurmaException {
		Turma minhaTurma = new Turma();
		DaoTurmaJDBC al = new DaoTurmaJDBC();
			
		if (turma.getIdDisciplina() == 0) {
			try {	
				dao.inserDisciplinaEmTurma(disciplina, minhaTurma);
				System.out.println("Inserido com sucesso");
			} catch (ConexaoException | SQLException e) {
				// TODO Auto-generated catch block
			}
		} else throw new TurmaCheiaException();
		
	}
	
	public void remover (Disciplina disciplina) throws TurmaInexistenteException {
		
		try {
			if (dao.pesquisarDisciplina(disciplina.getNome()) != null) {
				
				dao.removerDisciplina(disciplina);
			} else throw new TurmaInexistenteException();
		} catch (ConexaoException | SQLException e) {
			// TODO Auto-generated catch block
		}
	}
	
	public void alterar (Disciplina disc) throws TurmaInexistenteException {
	
		try {
				
				dao.alterarDisciplina(disc);
				System.out.println("Alterado com sucesso");
		} catch (ConexaoException | SQLException e) {
			// TODO Auto-generated catch block
		}
		
	}
	
	public void removerDisciplinaDeTurma(Turma turma) {
		
			try {
				dao.removerDisciplinaDeTurma(turma);
				System.out.println("Removido com sucesso");
			} catch (ConexaoException | SQLException e) {
				// TODO Auto-generated catch block
			}		
	}
	
	
	public Disciplina pesquisarDisciplina (String nome) {
		
		Disciplina t = null;
		try {
			t = dao.pesquisarDisciplina(nome);
		} catch (ConexaoException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return t;
		
	}
	
	
}

	

