package sistemaAcademico.classesBasicas;

import java.util.ArrayList;

public class CursoRepositorio {

	public static ArrayList<Curso> curso = new ArrayList<Curso>();
	
	public ArrayList<Curso> getCursos() {
		
		return curso;
		
	}

	public void addCurso(Curso c) {
		this.curso.add(c);
		
	}
}
