package sistemaAcademico.classesBasicas;

import java.util.Date;

import sistemaAcademico.dao.DaoFrequencia;
import sistemaAcademico.dao.DaoFrequenciaInt;

public class FrequenciaMain {
	
	
	public static void main(String[] args) {
		
		DaoFrequenciaInt dao= new DaoFrequencia();
		
		
		Frequencia frequencia= new Frequencia(10, new Date() , null, true, "boa");
		dao.registarFrequencia(frequencia);
		
		if(dao.consultarFrequencia().size()!=0){
			for(int i=0; i<dao.consultarFrequencia().size();i++){
				System.out.println(dao.consultarFrequencia().get(i).getid());
				System.out.println(dao.consultarFrequencia().get(i).getData());
				System.out.println(dao.consultarFrequencia().get(i).getTurma());
				System.out.println(dao.consultarFrequencia().get(i).isPresenca());
				System.out.println(dao.consultarFrequencia().get(i).getAvaliacao());
			}
		}
	}
}
