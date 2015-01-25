
package sistemaAcademico.daoJDBC;

import java.sql.SQLException;

import sistemaAcademico.classesBasicas.HistoricoEscolar;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.HistoricoInexistenteException;

public interface DaoHistoricoEscolarJDBCInt {

	public void inserir(HistoricoEscolar historico, int chaveDis) throws ConexaoException, SQLException;
	public void remover(HistoricoEscolar historico) throws ConexaoException, SQLException;
	public void alterar(HistoricoEscolar historico) throws ConexaoException, SQLException;
	public HistoricoEscolar pesquisar(String matricula) throws ConexaoException, SQLException, HistoricoInexistenteException;
}
