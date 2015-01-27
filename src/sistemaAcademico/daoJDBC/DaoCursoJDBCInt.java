package sistemaAcademico.daoJDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistemaAcademico.classesBasicas.Curso;
import sistemaAcademico.classesBasicas.Turma;
import sistemaAcademico.exceptions.ConexaoException;

public interface DaoCursoJDBCInt {
	
	public ResultSet consultar(String select)throws ClassNotFoundException, SQLException, ConexaoException;
	public List<Turma> consultarTurmas();
	public boolean cadastrar(Curso curso)throws  ClassNotFoundException, SQLException, ConexaoException;
	public boolean excluir(int id)throws  ClassNotFoundException, SQLException, ConexaoException;
	public boolean alterar(int id, Curso curso)throws  ClassNotFoundException, SQLException, ConexaoException;
}
