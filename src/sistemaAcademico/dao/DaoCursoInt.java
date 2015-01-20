package sistemaAcademico.dao;

import java.util.ArrayList;

import sistemaAcademico.classesBasicas.Curso;

public interface DaoCursoInt {
	
	public ArrayList<Curso> consultarTudo();
	public boolean  cadastrar(Curso curso);
	public boolean excluirPorNome(String nome);
	public boolean alterarPorNome(String nomeOld, String nomeNew);
	
}
