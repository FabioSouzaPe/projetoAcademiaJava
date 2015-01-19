package sistemaAcademico.regrasDeNegocio;



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
}
