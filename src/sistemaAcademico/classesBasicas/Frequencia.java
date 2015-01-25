package sistemaAcademico.classesBasicas;

import java.util.Date;
import java.util.HashMap;

public class Frequencia {
	private int id;
	private Date data;
	private Turma turma;
	private Aluno aluno;
	private Boolean presenca;
	private String avaliacao;
	private Disciplina disciplina;
	
	

	public Frequencia(){}

	public Frequencia(int id, Date data, Turma turma, Aluno aluno, Boolean presenca, String avaliacao, Disciplina disciplina) {
		
		this.id = id;
		this.data = data;
		this.turma = turma;
		this.aluno = aluno;
		this.presenca = presenca;
		this.avaliacao = avaliacao;
		this.disciplina = disciplina;
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

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Boolean getPresenca() {
		return presenca;
	}

	public void setPresenca(Boolean presenca) {
		this.presenca = presenca;
	}

	public String getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	
}