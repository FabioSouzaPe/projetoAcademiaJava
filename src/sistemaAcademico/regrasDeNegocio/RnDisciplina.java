package sistemaAcademico.regrasDeNegocio;


import java.sql.SQLException;

import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.DisciplinaExistenteException;
import sistemaAcademico.exceptions.DisciplinaInexistenteException;
import sistemaAcademico.classesBasicas.Disciplina;
import sistemaAcademico.dao.DaoDisciplina;
import sistemaAcademico.dao.DaoDisciplinaInt;
import sistemaAcademico.daoJDBC.DaoDisciplinaJDBC;
import sistemaAcademico.daoJDBC.DaoDisciplinaJDBCInt;


public class RnDisciplina  {

	
	DaoDisciplinaJDBCInt dao;
	DaoDisciplinaJDBC d = new DaoDisciplinaJDBC();	
	
	public void cadastrar (Disciplina novaDisciplina) throws DisciplinaExistenteException{
		
		try {
			if (d.pesquisarDisciplina(novaDisciplina.getNome()) != null) {
				
				d.cadastrarDisciplina(novaDisciplina);
				
			} else throw new DisciplinaExistenteException();
		} catch (SQLException | ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void remover (Disciplina d) throws DisciplinaInexistenteException {
		
		try {
			if (dao.pesquisarDisciplina(d.getNome()) != null) {

				dao.removerDisciplina(d);
			} else throw new DisciplinaInexistenteException();
		} catch (SQLException | ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void alterar (Disciplina d) throws DisciplinaInexistenteException {
		
		try {
			if (dao.pesquisarDisciplina(d.getNome()) != null) {
				dao.alterarDisciplina(d);
			} else throw new DisciplinaInexistenteException();
		} catch (SQLException | ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

	
	
	
}
