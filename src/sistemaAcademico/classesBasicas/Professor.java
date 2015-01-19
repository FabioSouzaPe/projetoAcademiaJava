package sistemaAcademico.classesBasicas;

import java.util.Date;

import sistemaAcademico.enuns.Titulo;

public class Professor {
	
	private Date admissao;
	private String departamento;
	private String instituicao;
	Titulo titulo;
	
	public Professor(Date admissao,String departamento,String instituicao,Titulo titulo ){
		
		this.admissao = admissao;
        this.departamento = departamento;
        this.instituicao = instituicao;
        this.titulo = titulo;
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
	
	

}
