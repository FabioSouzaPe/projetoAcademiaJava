package sistemaAcademico.daoJDBC;

import java.sql.SQLException;
import java.util.ArrayList;

import sistemaAcademico.classesBasicas.Publicacao;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.ErroSQLException;
import sistemaAcademico.exceptions.PublicacaoInexistenteException;

public interface DaoPublicacaoJDBCInt {
	
	public void inserir(Publicacao publicao) throws ErroSQLException, ConexaoException;
	public Publicacao pesquisar(String nome) throws PublicacaoInexistenteException, ConexaoException, ErroSQLException;
	public void alterar(Publicacao publicacao) throws ConexaoException, ErroSQLException;
	public void remover(Publicacao publicacao) throws ConexaoException, ErroSQLException;
	public ArrayList<Publicacao> listar() throws ConexaoException, ErroSQLException;
	public ArrayList<Publicacao> listarPorMatricula(String matricula) throws ConexaoException, ErroSQLException;
}
