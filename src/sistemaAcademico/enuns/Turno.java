package sistemaAcademico.enuns;

public enum Turno {

	MANHÃ(1),TARDE(2),NOITE(3) ;
	
	int codigo;
	  
	   Turno (int codigo){
		   this.codigo = codigo;	
		   
	   }
	   
	   public static Turno getName(int codigo){
		   
		   Turno turno = null;
		   
		   switch (codigo) {
		case 1:
			
			turno = Turno.MANHÃ;
			
			break;
			
		case 2:
			
			turno = Turno.TARDE;
			
			break;
			
		case 3:
		
		turno = Turno.NOITE;
		
		break;

		default:
			break;
		}
		return turno;
		   
	   }
	
}
