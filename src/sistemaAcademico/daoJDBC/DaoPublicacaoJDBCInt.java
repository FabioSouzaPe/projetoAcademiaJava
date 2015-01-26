package sistemaAcademico.daoJDBC;

import java.sql.SQLException;
import java.util.ArrayList;

import sistemaAcademico.classesBasicas.Publicacao;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.PublicacaoInexistenteException;

public interface DaoPublicacaoJDBCInt {
	
	public void inserir(Publicacao publicao) throws SQLException, ConexaoException;
	public Publicacao pesquisar(String nome) throws PublicacaoInexistenteException, ConexaoException, SQLException;
	public void alterar(Publicacao publicacao) throws ConexaoException, SQLException;
	public void remover(Publicacao publicacao) throws ConexaoException, SQLException;
	public ArrayList<Publicacao> listar() throws ConexaoException, SQLException;
	public ArrayList<Publicacao> listarPorAluno() throws ConexaoException, SQLException;
	public ArrayList<Publicacao> listarPorMatricula(String matricula) throws ConexaoException, SQLException;
}
