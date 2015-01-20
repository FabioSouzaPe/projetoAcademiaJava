package sistemaAcademico.classesBasicas;

import java.util.Date;

import sistemaAcademico.enuns.Titulo;

public class Professor {
	
	private int id;
	private Date admissao;
	private String departamento;
	private String instituicao;
	private Titulo titulo;
	private String matricula;
	 
	 
	
	public Professor(String matricula,Date admissao,String departamento,String instituicao,Titulo titulo ){
		

		this.admissao = admissao;
        this.departamento = departamento;
        this.instituicao = instituicao;
        this.titulo = titulo;
        this.matricula = matricula;
	}
	
	                                                        
	
	
	public Date getAdmissao() {
		return admissao;
	}
	public void setAdmissao(Date admissao) {
		this.admissao = admissao;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}




	public Titulo getTitulo() {
		return titulo;
	}




	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getMatricula() {
		return matricula;
	}




	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	 
	
	

}
