package sistemaAcademico.classesBasicas;

import java.util.ArrayList;

import sistemaAcademico.enuns.Escolaridade;

public class Pessoa {
	private int id;
	private String nome;
	private String cpf;
	private char sexo;
	private Endereco endereco;
	private ArrayList<Fone> fones = new ArrayList<Fone>();
	private ArrayList<Aluno> alunos = new ArrayList<Aluno>();
	private ArrayList<Professor> professores = new ArrayList<Professor>();

	public Pessoa(int id, String nome, String cpf, char sexo,
			Endereco endereco,Fone... fones) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.endereco = endereco;
		
		for (Fone fone : fones) {
			addFones(fone);
		}
		
	}
	
	public Pessoa(){}
	
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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public ArrayList<Fone> getFones() {
		return fones;
	}
	public void addFones(Fone fone) {
		this.fones.add(fone);
	}
}
