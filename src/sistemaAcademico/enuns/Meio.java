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

	public Meio setMeio(int i){
		Meio meio = null;
		switch (i) {
		case 1:
			 meio = meio.SITE;
			break;
		case 2:
			meio = meio.CONGRESSO;
			break;
		case 3:
			meio = meio.REVISTA;
			break;
		}
		return meio;
	}
	
}
