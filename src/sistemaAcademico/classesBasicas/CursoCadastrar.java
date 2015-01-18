package sistemaAcademico.classesBasicas;


public class CursoCadastrar {
	
	//obj usado para chamar cursoJaCadastrado()
	CursoConsultar consultar=new CursoConsultar();
	
	//cadastra curso
	public boolean  cadastrar(Curso curso){
		boolean cadastrado;
		CursoRepositorio cr= new CursoRepositorio();
		if(consultar.cursoJaCadastrado(curso.getNome())){
			cadastrado=false;
		}else{
			cr.addCurso(curso);
			cadastrado=true;
		}
		return cadastrado;	
	}
}
