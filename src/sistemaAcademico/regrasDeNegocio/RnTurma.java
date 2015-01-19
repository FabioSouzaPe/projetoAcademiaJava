package sistemaAcademico.regrasDeNegocio;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.Professor;
import sistemaAcademico.classesBasicas.Turma;
import sistemaAcademico.dao.DaoTurma;
import sistemaAcademico.dao.DaoTurmaInt;
import sistemaAcademico.enuns.Turno;


public class RnTurma {

	DaoTurmaInt dao;
	DaoTurma d = new DaoTurma();	
	
	public void cadastrar (Turma turma) {
		
		if (dao.consultarTurma(turma.getNomeDaTurma()) != null) {
			
			dao.cadastrarTurma(turma.getAlunosDaTurma(),turma.getNomeDaTurma(),turma.getProfessorDaTurma(), turma.getDisciplinasDaTurma(), turma.getPeriodoAtual(), turma.getTurnoDaTurma());
			
		} else System.out.println("Turma já existe");
		
	}
	
	public void matricularAluno (Aluno aluno,Turma turma) {
		
		if (turma.getAlunosDaTurma().size() <= 50) {
			turma.getAlunosDaTurma().add(aluno);
		}
		
	}
	
	public void remover (Turma turma) {
		
		if (dao.consultarTurma(turma.getNomeDaTurma()) != null) {
			
			dao.removerTurma(turma);
		} else System.out.println("Turma nao pode ser removido");
	}
	
	
	
	public void alterar (Turma turmaAtual, String novoNome, Professor novoProfessor, Turno novoTurno, String novoPeriodo) {
		

		if (dao.consultarTurma(turmaAtual.getNomeDaTurma()) != null) {
			
			dao.alterarTurma(turmaAtual,novoNome,novoProfessor, novoTurno,novoPeriodo);
		} else System.out.println("Turma nao existe");
		
		
	}

}