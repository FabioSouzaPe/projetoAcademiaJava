package sistemaAcademico.dao;

import java.util.List;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.Disciplina;
import sistemaAcademico.classesBasicas.Professor;
import sistemaAcademico.classesBasicas.Turma;
import sistemaAcademico.enuns.Turno;

public interface DaoTurmaInt {

	
	public abstract void cadastrarTurma(List <Aluno> a,String nome, Professor p, List <Disciplina> d, String periodo, Turno t);
	public abstract void removerTurma(Turma TurmaRemovida);
	public abstract Turma consultarTurma(String nome);
	public abstract List<Turma> consultarTurmas();
	public abstract void alterarTurma(Turma turmaAtual, String novoNome, Professor novoProfessor, Turno novoTurno, String novoPeriodo);
	
	
}
