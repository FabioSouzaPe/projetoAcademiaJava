package sistemAcademico.exceptions;

public class DisciplinaExistenteException extends Exception {

	public DisciplinaExistenteException() {
		
		super("Disciplina ja foi criada anteriormente");
		
	}
	
}
