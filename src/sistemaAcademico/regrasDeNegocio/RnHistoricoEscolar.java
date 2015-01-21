package sistemaAcademico.regrasDeNegocio;

import sistemaAcademico.exceptions.HistoricoExistenteException;
import sistemaAcademico.exceptions.HistoricoInexistenteException;
import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.HistoricoEscolar;
import sistemaAcademico.dao.DaoHistoricoEscolar;
import sistemaAcademico.dao.DaoHistoricoEscolarInt;

public class RnHistoricoEscolar {
	
	public DaoHistoricoEscolarInt dao = new DaoHistoricoEscolar();
	
	public void cadastrar(HistoricoEscolar historico) throws HistoricoExistenteException{
		try{
			if(pesquisar(historico.getAluno()) == null){
				dao.inserir(historico);
			}else{
				throw new HistoricoExistenteException();
			}
		}catch(HistoricoInexistenteException e){
			dao.inserir(historico);
		}
	}
	
	public void remover(HistoricoEscolar historico){
		try{
			if(pesquisar(historico.getAluno()) != null){
				dao.remover(historico);
			}
		}catch(HistoricoInexistenteException e){
			System.out.println(e.getMessage());
		}
	}
	
	public void alterar(HistoricoEscolar historico){
		try{
			if(pesquisar(historico.getAluno()) != null){
				dao.alterar(historico);
			}
		}catch(HistoricoInexistenteException e){
			System.out.println(e.getMessage());
		}
		
	}
	
	public HistoricoEscolar pesquisar(Aluno aluno) throws HistoricoInexistenteException{
		return dao.pequisar(aluno);
	}

}
