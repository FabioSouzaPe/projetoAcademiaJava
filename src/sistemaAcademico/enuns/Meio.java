package sistemaAcademico.enuns;

public enum Meio {
	
	SITE(1),REVISTA(2),CONGRESSO(3);
	
	private final int valor;
	
	Meio(int valorOp){
		valor = valorOp;
	}
	
	public int getValor(){
		return valor;
	}

	
	
}
