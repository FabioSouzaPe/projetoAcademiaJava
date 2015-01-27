package sistemaAcademico.regrasDeNegocio;

import java.util.List;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.Professor;
import sistemaAcademico.classesBasicas.Turma;
import sistemaAcademico.dao.DaoTurma;
import sistemaAcademico.dao.DaoTurmaInt;
import sistemaAcademico.enuns.Turno;
import sistemaAcademico.exceptions.AlunoMatriculadoTurmaException;
import sistemaAcademico.exceptions.CursoCheioException;
import sistemaAcademico.exceptions.TurmaCheiaException;
import sistemaAcademico.exceptions.TurmaExistenteException;
import sistemaAcademico.exceptions.TurmaInexistenteException;


public class RnTurma {

	DaoTurmaInt dao;
	DaoTurma d = new DaoTurma();	
	
	public void cadastrar (Turma turma) throws TurmaExistenteException, CursoCheioException {
		
		if (dao.consultarTurmas().size() == 10) {
			
			throw new CursoCheioException();
			
		}
		
		if (dao.consultarTurma(turma.getNomeDaTurma()) != null) {
			
			//dao.cadastrarTurma(turma.getAlunosDaTurma(),turma.getNomeDaTurma(),turma.getProfessorDaTurma(),  turma.getPeriodoAtual(), turma.getTurnoDaTurma());

		} else throw new TurmaExistenteException();
		
	}
	
	public void matricularAluno (Aluno aluno,Turma turma) throws TurmaCheiaException, AlunoMatriculadoTurmaException {
		
		List <Turma> minhasTurmas = dao.consultarTurmas();
		
		for (int i =0; i < minhasTurmas.size();i++) {
			
			if (minhasTurmas.get(i).getAlunosDaTurma().contains(aluno)) {
				throw new AlunoMatriculadoTurmaException();	
			}
		}
		
		if (turma.getAlunosDaTurma().size() <= 50) {
			turma.getAlunosDaTurma().add(aluno);
		} else throw new TurmaCheiaException();
		
	}
	
	public void remover (Turma turma) throws TurmaInexistenteException {
		
		if (dao.consultarTurma(turma.getNomeDaTurma()) != null) {
			
			dao.removerTurma(turma);
		} else throw new TurmaInexistenteException();
	}
	
	
	public void alterar (Turma turmaAtual, String novoNome, Professor novoProfessor, Turno novoTurno, String novoPeriodo) throws TurmaInexistenteException {
		

		if (dao.consultarTurma(turmaAtual.getNomeDaTurma()) != null) {
			
			dao.alterarTurma(turmaAtual,novoNome,novoProfessor, novoTurno,novoPeriodo);
		} else throw new TurmaInexistenteException();	
		
	}
	
	public void removerAluno(Aluno aluno,Turma turma) {
		
		if (dao.consultarTurma(turma.getNomeDaTurma()) != null) {
			
			turma.getAlunosDaTurma().remove(aluno);
			
		}
		
	}
	
}