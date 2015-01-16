package sistemaAcademico.classesBasicas;

import java.util.Date;
import java.util.List;

public class Curso {
	private int id;
	private String nome;
	private Date data;
	private List<Turma> turma;
	
	
	public Curso(){	}


	public Curso(int id, String nome, Date data, List<Turma> turma) {
		super();
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.turma = turma;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public List<Turma> getTurma() {
		return turma;
	}


	public void setTurma(List<Turma> turma) {
		this.turma = turma;
	}
	
	
	
}
