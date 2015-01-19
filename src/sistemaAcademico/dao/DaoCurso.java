package sistemaAcademico.dao;

import java.util.ArrayList;

import sistemaAcademico.classesBasicas.Curso;

public class DaoCurso implements DaoCursoInt{

	private static ArrayList<Curso> curso = new ArrayList<Curso>();
	
	
	@Override
	public ArrayList<Curso> consultarTudo() {
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
	public boolean alterarPorNome(String nome) {
		
		boolean excluido=false;
		
		for(int i =0; i<consultarTudo().size();i++){
			if(nome.equals(consultarTudo().get(i).getNome())){
				//dao.consultarTudo().;
				excluido=true;
			}
		}
		return excluido;
	}

}
