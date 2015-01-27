package sistemaAcademico.classesBasicas;

public class Disciplina {

	
	private int id;
	private String nome;
	private int cargaHoraria;

	
	public Disciplina() {
	}
	
	public Disciplina(String nome, int cargaHoraria){
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
	}

	private Turma turma;

	
	
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	public int getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
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

}
