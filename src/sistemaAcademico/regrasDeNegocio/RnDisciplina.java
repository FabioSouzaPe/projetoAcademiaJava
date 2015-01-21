package sistemaAcademico.regrasDeNegocio;


import sistemaAcademico.exceptions.DisciplinaExistenteException;
import sistemaAcademico.exceptions.DisciplinaInexistenteException;
import sistemaAcademico.classesBasicas.Disciplina;
import sistemaAcademico.dao.DaoDisciplina;
import sistemaAcademico.dao.DaoDisciplinaInt;


public class RnDisciplina  {

	
	DaoDisciplinaInt dao;
	DaoDisciplina d = new DaoDisciplina();	
	
	public void cadastrar (String novaDisciplina) throws DisciplinaExistenteException{
		
		if (d.pesquisarDisciplina(novaDisciplina) != null) {
			
			d.cadastrarDisciplina(novaDisciplina);
			
		} else throw new DisciplinaExistenteException();
	}
	
	
	public void remover (Disciplina d) throws DisciplinaInexistenteException {
		
		if (dao.pesquisarDisciplina(d.getNome()) != null) {
	
			dao.removerDisciplina(d);
		} else throw new DisciplinaInexistenteException();
	}
	
	
	public void alterar ( String novoNome, Disciplina d) throws DisciplinaInexistenteException {
		
		if (dao.pesquisarDisciplina(novoNome) != null) {
			
			dao.alterarDisciplina(novoNome,d);
		} else throw new DisciplinaInexistenteException();
		
	}

	

	
	
	
}
