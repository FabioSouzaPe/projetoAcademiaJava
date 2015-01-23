package sistemaAcademico.enuns;

public enum SituacaoAluno {
	
	ANDAMENTO(1),
	CONCLUIDO(2),
	TRANCADO(3);
	
	private int valor;
	
	SituacaoAluno(int valorOp){
		valor = valorOp;
	}
	
	public int getValor(){
		return valor;
	}

}
