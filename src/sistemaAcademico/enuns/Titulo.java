package sistemaAcademico.enuns;

public enum Titulo {
	
  DOUTORADO(1),MESTRADO(2),POSGRADUACAO(3);
  
  public int valortitulo;
  
   Titulo (int valor){
	   valortitulo = valor;	
	   
   }
   public int getValor(){
		return valortitulo;
	}
}
  


