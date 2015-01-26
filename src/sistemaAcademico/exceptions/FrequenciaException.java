package sistemaAcademico.exceptions;

public class FrequenciaException extends Exception {
	String mensagem;
	
	public FrequenciaException(String erro){
		super(erro);
		this.mensagem=erro;
	}
	
	public String getMensagem(){
		return mensagem;
	}
}
