package sistemaAcademico.daoJDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import sistemaAcademico.classesBasicas.Frequencia;
import sistemaAcademico.classesBasicas.Turma;

public interface DaoFrequenciaJDBCInt {
	
	public ResultSet consultar(String select) throws ClassNotFoundException, SQLException;
	public boolean cadastrarFrequencia(Frequencia frequencia)throws SQLException, ClassNotFoundException;
	public boolean alterarFrequencia(Frequencia frequencia) throws ClassNotFoundException, SQLException;
	public boolean ListarFrequencia(Turma turma, Date data);
}
