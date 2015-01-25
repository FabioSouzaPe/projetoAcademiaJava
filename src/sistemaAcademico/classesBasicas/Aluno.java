package sistemaAcademico.classesBasicas;

import java.util.ArrayList;
import java.util.Date;

public class Aluno{
	
	private String matricula;
	private ArrayList<Publicacao> publicacoes;
	private Date data;
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	private Pessoa pessoa;
	
	public Aluno(String matricula, ArrayList<Publicacao> publicacoes, Date data, Pessoa pesspa){
		this.matricula = matricula;
		this.publicacoes = this.publicacoes;
		this.data = data;
		this.pessoa = pessoa;
	}
	
	public Aluno(){
		
	}
	
	public String toString(){
		return "Matricula: " + this.matricula + " Data da matricula: " + this.data;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	

	public ArrayList<Publicacao> getPublicacoes() {
		return publicacoes;
	}

	public void setPublicacoes(ArrayList<Publicacao> publicacoes) {
		this.publicacoes = publicacoes;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
}
