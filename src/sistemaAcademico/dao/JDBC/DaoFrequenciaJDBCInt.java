package sistemaAcademico.dao.JDBC;

import java.util.Date;

import sistemaAcademico.classesBasicas.Frequencia;
import sistemaAcademico.classesBasicas.Turma;

public interface DaoFrequenciaJDBCInt {
	
	public boolean cadastrarFrequencia(Frequencia frequencia);
	public boolean alterarFrequencia(Frequencia frequencia);
	public boolean ListarFrequencia(Turma turma, Date data);
}
