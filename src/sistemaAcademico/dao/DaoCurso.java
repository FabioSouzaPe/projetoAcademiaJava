package sistemaAcademico.dao;

import java.util.ArrayList;
import java.util.List;
import sistemAcademico.exceptions.CursoInexistenteException;
import sistemaAcademico.classesBasicas.Curso;
import sistemaAcademico.classesBasicas.Turma;

public class DaoCurso implements DaoCursoInt{

	private static ArrayList<Curso> curso = new ArrayList<Curso>();
	
	
	@Override
	public ArrayList<Curso> consultarTudo() /*throws CursoInexistenteException*/ {
		if(DaoCurso.curso==null){
		//	throw new CursoInexistenteException();
		}
		
		return DaoCurso.curso;
	}

	@Override
	public boolean cadastrar(Curso curso) {
		
		DaoCurso.curso.add(curso);
		return true;
	}

	@Override
	public boolean excluirPorNome(String nome) {
		
		boolean excluido=false;
		
		for(int i =0; i<consultarTudo().size();i++){
			if(nome.equals(consultarTudo().get(i).getNome())){
				consultarTudo().remove(i);
				excluido=true;
			}
		}
		return excluido;
	}

	@Override
	public boolean alterarPorNome(String nomeOld, String nomeNew) {
		
		boolean alterado=false;
		
		for(int i =0; i<consultarTudo().size();i++){
			if(nomeOld.equals(consultarTudo().get(i).getNome())){
				
				consultarTudo().get(i).setNome(nomeNew);;
				alterado=true;
			}
		}
		return alterado;
	}

	@Override
	public  List<Turma> consultarTurmas(String nomeCurso) {
		
		
		for(int i =0; i< consultarTudo().size();i++){
			if(nomeCurso.equals(consultarTudo().get(i).getNome())){
				return consultarTudo().get(i).getTurma();
			}
		}
		
		return null;
	}

}
