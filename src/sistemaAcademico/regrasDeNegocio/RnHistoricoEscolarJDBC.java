package sistemaAcademico.regrasDeNegocio;


import sistemaAcademico.classesBasicas.HistoricoEscolar;
import sistemaAcademico.daoJDBC.DaoHistoricoEscolarJDBC;
import sistemaAcademico.daoJDBC.DaoHistoricoEscolarJDBCInt;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.ErroSQLException;
import sistemaAcademico.exceptions.HistoricoExistenteException;
import sistemaAcademico.exceptions.HistoricoInexistenteException;

public class RnHistoricoEscolarJDBC {
	
	private DaoHistoricoEscolarJDBCInt dao;
	
	public RnHistoricoEscolarJDBC(){
		dao = new DaoHistoricoEscolarJDBC();
	}
	
	public int cadastrarHistorico(HistoricoEscolar historico) throws ConexaoException, ErroSQLException, HistoricoExistenteException{
		try{
			HistoricoEscolar teste = pesquisar(historico.getAluno().getMatricula());
			throw new HistoricoExistenteException();
		}catch(HistoricoInexistenteException e){
			int i = dao.inserir(historico);
			return i;
		}
	}
	
	public void remover(HistoricoEscolar historico) throws ConexaoException, ErroSQLException{
		dao.remover(historico);
	}
	
	public void alterar(HistoricoEscolar historico) throws ConexaoException, ErroSQLException{
		dao.alterar(historico);
	}
	
	public HistoricoEscolar pesquisar(String matricula) throws ConexaoException, ErroSQLException, HistoricoInexistenteException{
		return dao.pesquisar(matricula);
	}

}
