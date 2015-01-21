package sistemaAcademico.dao;

import java.util.ArrayList;

import sistemaAcademico.exceptions.AlunoInexistenteException;
import sistemaAcademico.classesBasicas.Aluno;

public interface DaoAlunoInt {
	
	public void inserir(Aluno aluno);
	public void alterar(Aluno aluno);
	public void remover(Aluno aluno);
	public Aluno pesquisar(String matricula) throws AlunoInexistenteException;
	public ArrayList<Aluno> listar();

}
