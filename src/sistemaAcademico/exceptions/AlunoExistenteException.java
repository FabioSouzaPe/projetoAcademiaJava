package sistemaAcademico.exceptions;

public class AlunoExistenteException extends Exception{
	
	public AlunoExistenteException(){
		super("Aluno ja cadastrado");
	}

}
