package sistemaAcademico.classesBasicas;

import java.util.Date;
import java.util.List;

public class Curso {
	private int id;
	private String nome;
	private Date data;
	
	
	
	public Curso(){	}


	public Curso(int id, String nome, Date data ) {
		this.id = id;
		this.nome = nome;
		this.data = data;
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


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}

	
	
<<<<<<< HEAD
=======
	
>>>>>>> origin/projetoAcademiaJava_16012015_edson
}
