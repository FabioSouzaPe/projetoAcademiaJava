package sistemaAcademico.enuns;

public enum Titulo {
	
  DOUTORADO(1),MESTRADO(2),POSGRADUACAO(3);
  
   int codigo;
  
   Titulo (int codigo){
	   this.codigo = codigo;	
	   
   }
   
   public Titulo getName(int codigo){
	   
	   Titulo titulo = null;
	   
	   switch (codigo) {
	case 1:
		
		titulo = Titulo.DOUTORADO;
		
		break;
		
	case 2:
		
		titulo = Titulo.MESTRADO;
		
		break;
		
	case 3:
	
	titulo = Titulo.POSGRADUACAO;
	
	break;

	default:
		break;
	}
	   
	return titulo;
	   
   }
   
   
   public int getcodigo(){
		return codigo;
	}
   public void setcodigo(int codigo){
	   this.codigo = codigo;
	   
   }
}
  


