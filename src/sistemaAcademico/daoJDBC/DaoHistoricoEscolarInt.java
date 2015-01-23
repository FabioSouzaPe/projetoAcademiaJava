package sistemaAcademico.daoJDBC;

import java.sql.SQLException;

import sistemaAcademico.classesBasicas.HistoricoEscolar;
import sistemaAcademico.exceptions.ConexaoException;

public interface DaoHistoricoEscolarInt {

	public void inserir(HistoricoEscolar historico, int chaveDis) throws ConexaoException, SQLException;
	public void remover(HistoricoEscolar historico) throws ConexaoException, SQLException;
	public void alterar(HistoricoEscolar historico) throws ConexaoException, SQLException;
}
