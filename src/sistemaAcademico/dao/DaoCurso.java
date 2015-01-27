package sistemaAcademico.dao;

import java.util.ArrayList;
import java.util.List;
import sistemaAcademico.exceptions.CursoException;
import sistemaAcademico.classesBasicas.Curso;
import sistemaAcademico.classesBasicas.Turma;

public class DaoCurso implements DaoCursoInt{

	private static ArrayList<Curso> curso = new ArrayList<Curso>();
	
	
	@Override
	public ArrayList<Curso> consultarTudo()  {
		
		return DaoCurso.curso;
	}

	@Override
	public void cadastrar(Curso curso) {
		DaoCurso.curso.add(curso);
	}

	@Override
	public void excluir(Curso curso) {
		DaoCurso.curso.remove(curso);
		
	}

	@Override
	public void alterar(int index, Curso CursoNew) {
		
		DaoCurso.curso.set(index, CursoNew);
		
	}

	@Override
	public List<Turma> consultarTurmas(int index, Curso Curso) {
		// TODO Auto-generated method stub
		return null;
	}


}
