package sistemaAcademico.exceptions;

public class AlunoInexistenteException extends Exception{
	
	public AlunoInexistenteException(){
		super("Aluno n�o encontrado");
	}

}
