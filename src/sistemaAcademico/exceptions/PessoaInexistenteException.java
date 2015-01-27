package sistemaAcademico.exceptions;

public class PessoaInexistenteException extends NullPointerException {

	public PessoaInexistenteException() {
		super("Pessoa n�o exsite");
	}

	@Override
	public String getMessage() {
		return "Pessoa n�o exsite";
	}

	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		super.printStackTrace();
	}
}