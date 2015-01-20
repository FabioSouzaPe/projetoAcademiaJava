package sistemaAcademico.dao;

import java.util.ArrayList;

import sistemaAcademico.classesBasicas.Frequencia;

public class DaoFrequencia implements DaoFrequenciaInt {

	private static ArrayList <Frequencia> frequencia = new ArrayList<Frequencia>();
	
	@Override
	public boolean registarFrequencia(Frequencia f) {
		DaoFrequencia.frequencia.add(f);
		return true;
	}

	@Override
	public boolean alterarFrequencia() {
		
		return false;
	}

	@Override
	public boolean excluirFrequencia() {
		
		return false;
	}

	@Override
	public ArrayList <Frequencia>consultarFrequencia() {
		
		return DaoFrequencia.frequencia;
	}

}
