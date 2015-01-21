package sistemaAcademico.dao.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistemaAcademico.classesBasicas.Curso;
import sistemaAcademico.classesBasicas.Turma;

public interface DaoCursoJDBCInt {
	
	public ResultSet consultar(String select)throws ClassNotFoundException, SQLException;
	public List<Turma> consultarTurmas();
	public boolean cadastrar(Curso curso)throws  ClassNotFoundException, SQLException;
	public boolean excluir(int id)throws  ClassNotFoundException, SQLException;
	public boolean alterar(int id, Curso curso)throws  ClassNotFoundException, SQLException;
}
