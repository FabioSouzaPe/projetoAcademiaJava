package sistemaAcademico.dao;

import java.util.ArrayList;
import java.util.List;

import sistemaAcademico.exceptions.CursoInexistenteException;
import sistemaAcademico.classesBasicas.Curso;
import sistemaAcademico.classesBasicas.Turma;

public interface DaoCursoInt {
	
	public ArrayList<Curso> consultarTudo();
	public List<Turma> consultarTurmas(int index, Curso Curso);
	public void cadastrar(Curso curso);
	public void excluir(Curso curso);
	public void alterar(int index, Curso CursoNew);
	
}
