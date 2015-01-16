package sistemaAcademico.classesBasicas;

import java.util.ArrayList;

public class Pessoa {
	private int id;
	private String nome;
	private String cpf;
	private char sexo;
	private Endereco endereco;
	private ArrayList<Fone> fones;

	public Pessoa(int id, String nome, String cpf, char sexo,
			Endereco endereco, ArrayList<Fone> fones) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.endereco = endereco;
		this.fones = fones;
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
	public void setFones(ArrayList<Fone> fones) {
		this.fones = fones;
	}
}
