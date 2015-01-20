package sistemaAcademico.regrasDeNegocio;



import java.util.ArrayList;
import java.util.List;

import sistemaAcademico.classesBasicas.Turma;
import sistemaAcademico.dao.DaoCurso;
import sistemaAcademico.dao.DaoCursoInt;

public class RnCurso {
	
	DaoCursoInt dao = new DaoCurso();
		
		
	
	public boolean cursoJaCadastrado(String nome) {
		boolean jaCadastrado = false;
		
		if (dao.consultarTudo().size() != 0) {
			for (int i = 0; i < dao.consultarTudo().size(); i++) {
				if (nome.equals(dao.consultarTudo().get(i).getNome())) {
					jaCadastrado = true;
					
				}
			}
		}

		return jaCadastrado;
	}

	public boolean semCurso(){
		boolean vazio=false;
		if(dao.consultarTudo().size()==0){
			vazio=true;
		}
		return vazio;
	}
	
	public int quantidadeDeTurmas(String nomeCurso){
		int cont=0;
		List<Turma> t= new ArrayList<Turma>();
		t=dao.consultarTurmas(nomeCurso);
		
		if(t!=null){
			for(int i=0; i<t.size();i++){
				cont++;
			}
		}
		return cont;
	}
}
