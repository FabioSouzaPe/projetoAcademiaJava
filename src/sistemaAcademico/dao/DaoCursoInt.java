package sistemaAcademico.dao;

import java.util.ArrayList;
import java.util.List;

import sistemAcademico.exceptions.CursoInexistenteException;
import sistemaAcademico.classesBasicas.Curso;
import sistemaAcademico.classesBasicas.Turma;

public interface DaoCursoInt {
	
	public ArrayList<Curso> consultarTudo() /*throws CursoInexistenteException*/;
	public List<Turma> consultarTurmas(String nomeCurso);
	public boolean  cadastrar(Curso curso);
	public boolean excluirPorNome(String nome);
	public boolean alterarPorNome(String nomeOld, String nomeNew);
	
}
