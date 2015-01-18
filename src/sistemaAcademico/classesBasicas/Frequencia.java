package sistemaAcademico.classesBasicas;

public class Frequencia {
	private int frequencia;
	private int data;
	private Turma turma;
	private boolean presenca;
	private String avaliacao;
	
	
	
	
	public Frequencia(){}
	
	public Frequencia(int frequencia, int data, Turma turma, boolean presenca,
			String avaliacao) {
		super();
		this.frequencia = frequencia;
		this.data = data;
		this.turma = turma;
		this.presenca = presenca;
		this.avaliacao = avaliacao;
	}
	
	public int getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(int frequencia) {
		this.frequencia = frequencia;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
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
