package sistemaAcademico.dao;

import java.util.ArrayList;

import sistemaAcademico.exceptions.AlunoInexistenteException;
import sistemaAcademico.classesBasicas.Aluno;

public class DaoAluno implements DaoAlunoInt{

	ArrayList<Aluno> alunos = new ArrayList<Aluno>();
	
	@Override
	public void inserir(Aluno aluno) {
		alunos.add(aluno);
	}

	@Override
	public void alterar(Aluno aluno) {
		for(int i = 0; i <= alunos.size()-1; i ++){
			if(aluno.getMatricula().equals(alunos.get(i).getMatricula())){
				alunos.set(i, aluno);
			}
		}
	}

	@Override
	public void remover(Aluno aluno) {
		for(int i = 0; i <= alunos.size()-1; i++){
			if(aluno.getMatricula().equals(alunos.get(i).getMatricula())){
				alunos.remove(i);
			}
		}
	}

	@Override
	public Aluno pesquisar(String matricula) throws AlunoInexistenteException{
		if(alunos.size() != 0){
			for(int i = 0; i <= alunos.size()-1; i ++){
				if(matricula.equals(alunos.get(i).getMatricula())){
					return alunos.get(i);
				}
			}
			throw new AlunoInexistenteException();
		}
		return null;
	}

	@Override
	public ArrayList<Aluno> listar() {
		if(alunos.size() != 0){
			for(int i = 0; i <= alunos.size()-1; i ++){
				return alunos;
			}
		}
		return null;
	}

}
