
package sistemaAcademico.daoJDBC;

import sistemaAcademico.classesBasicas.HistoricoEscolar;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.ErroSQLException;
import sistemaAcademico.exceptions.HistoricoInexistenteException;

public interface DaoHistoricoEscolarJDBCInt {

	public int inserir(HistoricoEscolar historico) throws ConexaoException, ErroSQLException;
	public void remover(HistoricoEscolar historico) throws ConexaoException, ErroSQLException;
	public void alterar(HistoricoEscolar historico) throws ConexaoException, ErroSQLException;
	public HistoricoEscolar pesquisar(String matricula) throws ConexaoException, ErroSQLException, HistoricoInexistenteException;
}
