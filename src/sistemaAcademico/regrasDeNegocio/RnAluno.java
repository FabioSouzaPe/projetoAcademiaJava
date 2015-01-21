package sistemaAcademico.regrasDeNegocio;

import sistemaAcademico.exceptions.AlunoExistenteException;
import sistemaAcademico.exceptions.AlunoInexistenteException;
import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.dao.DaoAluno;
import sistemaAcademico.dao.DaoAlunoInt;

public class RnAluno {
	
	public DaoAlunoInt dao = new DaoAluno();
	
	public void inserir(Aluno aluno) throws AlunoExistenteException{
		try{
			if(pesquisar(aluno.getMatricula()) == null){
				dao.inserir(aluno);
			}else{
				throw new AlunoExistenteException();
			}
		}catch(AlunoInexistenteException e){
			dao.inserir(aluno);
		}
	}
	
	public void alterar(Aluno aluno){
		try{
			if(pesquisar(aluno.getMatricula()) != null){
				dao.alterar(aluno);
			}
		}catch(AlunoInexistenteException e){
			System.out.println(e.getMessage());
		}
	}
	
	public void remover(Aluno aluno){
		try{
			if(pesquisar(aluno.getMatricula()) != null){
				dao.remover(aluno);
			}
		}catch(AlunoInexistenteException e){
			System.out.println(e.getMessage());
		}
	}
	
	public Aluno pesquisar(String matricula) throws AlunoInexistenteException{
		return dao.pesquisar(matricula);
	}

}
