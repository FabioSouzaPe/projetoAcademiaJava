package sistemaAcademico.exceptions;

public class ProfessorExistenteException extends Exception{
	public ProfessorExistenteException(){
		super("Professor j� existe");
	}
}
