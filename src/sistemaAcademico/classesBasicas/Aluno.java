package sistemaAcademico.classesBasicas;

import java.util.ArrayList;
import java.util.Date;

public class Aluno{
	
	private String matricula;
	private ArrayList<Publicacao> publicacoes;
	private HistoricoEscolar historico;
	private Date data;
	private Pessoa pessoa;
	
	public Aluno(String matricula, ArrayList<Publicacao> publicacoes,HistoricoEscolar historico,Date data, Pessoa pessoa){
		this.matricula = matricula;
		this.publicacoes = this.publicacoes;
		this.historico = historico;
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
	
	public HistoricoEscolar getHistorico() {
		return historico;
	}

	public void setHistorico(HistoricoEscolar historico) {
		this.historico = historico;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
