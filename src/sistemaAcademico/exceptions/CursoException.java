package sistemaAcademico.exceptions;

public class CursoException extends Exception{
	
	String mensagem;
	public CursoException(String erro){
		super(erro);
		this.mensagem=erro;
	}
	
	public String getMensagem(){
		return mensagem;
	}
}
