package sistemaAcademico.regrasDeNegocio;

import java.sql.SQLException;

import sistemaAcademico.classesBasicas.HistoricoEscolar;
import sistemaAcademico.daoJDBC.DaoHistoricoEscolarJDBC;
import sistemaAcademico.daoJDBC.DaoHistoricoEscolarJDBCInt;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.HistoricoExistenteException;
import sistemaAcademico.exceptions.HistoricoInexistenteException;

public class RnHistoricoEscolarJDBC {
	
	private DaoHistoricoEscolarJDBCInt dao;
	
	public RnHistoricoEscolarJDBC(){
		dao = new DaoHistoricoEscolarJDBC();
	}
	
	public void cadastrarHistorico(HistoricoEscolar historico) throws ConexaoException, SQLException, HistoricoExistenteException{
		try{
			HistoricoEscolar teste = pesquisar(historico.getAluno().getMatricula());
			throw new HistoricoExistenteException();
		}catch(HistoricoInexistenteException e){
			dao.inserir(historico);
		}
	}
	
	public void remover(HistoricoEscolar historico) throws ConexaoException, SQLException{
		dao.remover(historico);
	}
	
	public void alterar(HistoricoEscolar historico) throws ConexaoException, SQLException{
		dao.alterar(historico);
	}
	
	public HistoricoEscolar pesquisar(String matricula) throws ConexaoException, SQLException, HistoricoInexistenteException{
		return dao.pesquisar(matricula);
	}

}
