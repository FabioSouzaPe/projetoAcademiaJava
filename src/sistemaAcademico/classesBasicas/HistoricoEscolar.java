package sistemaAcademico.classesBasicas;

import java.util.ArrayList;
import java.util.Date;

import sistemaAcademico.enuns.SituacaoAluno;

public class HistoricoEscolar {
	
	private int id;
	private Date data;
	private Disciplina disciplina;
	private Aluno aluno;
	private String obs;
	private double conficienteRedimento;
	private SituacaoAluno situacao;
	
	public HistoricoEscolar(){
		
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

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public double getConficienteRedimento() {
		return conficienteRedimento;
	}

	public void setConficienteRedimento(double conficienteRedimento) {
		this.conficienteRedimento = conficienteRedimento;
	}

	public SituacaoAluno getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoAluno situacao) {
		this.situacao = situacao;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
}
