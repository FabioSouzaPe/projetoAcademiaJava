package sistemaAcademico.classesBasicas;

public class CursoExcluir {

	//exclui curso da lista
	public boolean excluir(String nome){
		boolean excluido=false;
		CursoRepositorio cr= new CursoRepositorio();
		if(cr.getCursos().size()!=0){
			
			for(int i =0; i<cr.getCursos().size();i++){
				if(nome.equals(cr.getCursos().get(i).getNome())){
					cr.getCursos().remove(i);
					excluido=true;
				}
			}
		}
		
		return excluido;
	}
	
}
