package sistemaAcademico.dao;

import java.util.ArrayList;

import sistemaAcademico.classesBasicas.Curso;

public class DaoCurso implements DaoCursoInt{

	public static ArrayList<Curso> curso = new ArrayList<Curso>();
	
	
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
		DaoCurso dao=new DaoCurso();
		boolean excluido=false;
		
		for(int i =0; i<dao.consultarTudo().size();i++){
			if(nome.equals(dao.consultarTudo().get(i).getNome())){
				dao.consultarTudo().remove(i);
				excluido=true;
			}
		}
		return excluido;
	}

}
