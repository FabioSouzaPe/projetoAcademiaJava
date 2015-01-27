package sistemaAcademico.exceptions;

public class PessoaInexistenteException extends NullPointerException {

	public PessoaInexistenteException() {
		super("Pessoa não exsite");
	}

	@Override
	public String getMessage() {
		return "Pessoa não exsite";
	}

	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		super.printStackTrace();
	}
}