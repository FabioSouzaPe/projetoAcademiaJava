package sistemaAcademico.daoJDBC;

import java.sql.ResultSet;
import java.sql.SQLException;

import sistemaAcademico.exceptions.ConexaoException;

public interface IDaoGenerico {
	
	public ResultSet dql(String select) throws ClassNotFoundException, SQLException, ConexaoException;
	public boolean dml(String sql) throws ClassNotFoundException, SQLException, ConexaoException ;
}
