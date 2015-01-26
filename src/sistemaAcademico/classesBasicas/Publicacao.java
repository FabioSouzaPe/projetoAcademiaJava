package sistemaAcademico.classesBasicas;

import sistemaAcademico.enuns.Meio;

public class Publicacao {
	
	private int id;
	private String nome;
	private String conteudo;
	private Professor professor;
	private Aluno aluno;
	private Meio meioDeComunicacao;
	
	public Publicacao(int id, String nome,String conteudo ,Professor professor ,Aluno aluno , Meio meioDeComunicacao){
		this.id = id;
		this.nome = nome;
		this.conteudo = conteudo;
		this.professor = professor;
		this.aluno = aluno;
		this.meioDeComunicacao = meioDeComunicacao;
	}
	
	public Publicacao(){
		
	}
	
	public String toString(){
		return "Nome: " + nome + "\n" + 
				" Matricula Professor: " + professor.getMatricula() + "\n" +
				" Professor Departamento: " + professor.getDepartamento() + "\n" +
				" Matricula aluno: " + aluno.getMatricula() + "\n" +
				" Meio: " + meioDeComunicacao + "\n" +
				" Conteudo : " + conteudo;
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
	
	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Meio getMeioDeComunicacao() {
		return meioDeComunicacao;
	}

	public void setMeioDeComunicacao(Meio meioDeComunicacao) {
		this.meioDeComunicacao = meioDeComunicacao;
	}
	
	

}
