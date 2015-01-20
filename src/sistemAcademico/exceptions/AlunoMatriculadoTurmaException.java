package sistemAcademico.exceptions;

public class AlunoMatriculadoTurmaException extends Exception {

	
	public AlunoMatriculadoTurmaException() {
		// TODO Auto-generated constructor stub
		super ("O aluno nao pode estar matriculado em mais de uma turma");
	}
	
}
