package sistemaAcademico.daoJDBC;

import java.sql.SQLException;

import sistemaAcademico.classesBasicas.Publicacao;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.PublicacaoInexistenteException;

public interface DaoPublicacaoJDBCInt {
	
	public void inserir(Publicacao publicao) throws SQLException, ConexaoException;
	public Publicacao pesquisar(String nome) throws PublicacaoInexistenteException, ConexaoException, SQLException;
	public void alterar(Publicacao publicacao) throws ConexaoException, SQLException;
	public void remover(Publicacao publicacao) throws ConexaoException, SQLException;

}
