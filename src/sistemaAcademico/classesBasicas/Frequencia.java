package sistemaAcademico.classesBasicas;

import java.util.Date;

public class Frequencia {
	private int id;
	private Date data;
	private Turma turma;
	private boolean presenca;
	private String avaliacao;
	
	
	
	
	public Frequencia(){}
	
	public Frequencia(int id, Date data, Turma turma, boolean presenca,
			String avaliacao) {
		super();
		this.id = id;
		this.data = data;
		this.turma = turma;
		this.presenca = presenca;
		this.avaliacao = avaliacao;
	}
	
	public int getid() {
		return id;
	}

	public void setid(int id) {
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

	public boolean isPresenca() {
		return presenca;
	}

	public void setPresenca(boolean presenca) {
		this.presenca = presenca;
	}

	public String getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}

	
}
