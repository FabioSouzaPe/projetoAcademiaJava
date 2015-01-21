package sistemaAcademico.dao;

import java.util.ArrayList;

import sistemaAcademico.classesBasicas.Frequencia;

public interface DaoFrequenciaInt {
	
	public boolean registarFrequencia(Frequencia f);
	public boolean alterarFrequencia();
	public boolean excluirFrequencia();
	public ArrayList <Frequencia> consultarFrequencia();
}
