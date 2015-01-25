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
	
	public void setCargaHoraria(int carga){
		this.cargaHoraria = carga;
	}
	
	public int getCargaHoraria(){
		return cargaHoraria;
	}
}
