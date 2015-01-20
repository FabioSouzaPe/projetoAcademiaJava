package sistemaAcademico.dao.JDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistemaAcademico.classesBasicas.Curso;
import sistemaAcademico.classesBasicas.Turma;

public interface DaoCursoJDBCInt {
	
	public ArrayList<Curso> consultarTudo();
	public List<Turma> consultarTurmas();
	public boolean cadastrar(Curso curso)throws  ClassNotFoundException, SQLException;
	public boolean excluir();
	public boolean alterar();
}
