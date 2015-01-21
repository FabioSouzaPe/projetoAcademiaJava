package sistemaAcademico.classesBasicas;

import java.util.Date;
import java.util.HashMap;

public class Frequencia {
	private int id;
	private Date data;
	private Turma turma;
	private HashMap<Integer,Boolean> presenca;
	private HashMap<Integer,String> avaliacao;
	
	public Frequencia(){}

	public Frequencia(int id, Date data, Turma turma,HashMap<Integer, Boolean> presenca,HashMap<Integer, String> avaliacao) {
		this.id = id;
		this.data = data;
		this.turma = turma;
		this.presenca = presenca;
		this.avaliacao = avaliacao;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public Turma getTurma() {
		return turma;
	}


	public void setTurma(Turma turma) {
		this.turma = turma;
	}


	public HashMap<Integer, Boolean> getPresenca() {
		return presenca;
	}


	public void setPresenca(HashMap<Integer, Boolean> presenca) {
		this.presenca = presenca;
	}


	public HashMap<Integer, String> getAvaliacao() {
		return avaliacao;
	}


	public void setAvaliacao(HashMap<Integer, String> avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	
}