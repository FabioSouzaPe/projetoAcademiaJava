package sistemaAcademico.classesBasicas;

import java.util.ArrayList;

public class CursoConsultar {

	//retorna uma lista com todos os cursos cadastrados
	public ArrayList<Curso> consultar() {
		CursoRepositorio cr = new CursoRepositorio();
		return cr.getCursos();
	}
	//verifica se o curso já está cadastrado
	public boolean cursoJaCadastrado(String nome) {
		boolean jaCadastrado = false;
		CursoRepositorio cr = new CursoRepositorio();
		if (cr.getCursos().size() != 0) {

			for (int i = 0; i < cr.getCursos().size(); i++) {
				if (nome.equals(cr.getCursos().get(i).getNome())) {
					jaCadastrado = true;
				}
			}
		}

		return jaCadastrado;
	}
}
