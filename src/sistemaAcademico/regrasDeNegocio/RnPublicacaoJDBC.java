package sistemaAcademico.regrasDeNegocio;

import java.sql.SQLException;

import sistemaAcademico.classesBasicas.Publicacao;
import sistemaAcademico.daoJDBC.DaoPublicacaoJDBCInt;
import sistemaAcademico.daoJDBC.DaoPublicacaoJdbc;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.PublicacaoExistenteException;
import sistemaAcademico.exceptions.PublicacaoInexistenteException;

public class RnPublicacaoJDBC {
	
	private DaoPublicacaoJDBCInt dao;
	
	public RnPublicacaoJDBC(){
		dao = new DaoPublicacaoJdbc();
	}
	
	public void cadastrarPublicacao(Publicacao publicacao) throws PublicacaoExistenteException, SQLException, ConexaoException{
		try {
			Publicacao p = pesquisar(publicacao.getNome());
			throw new PublicacaoExistenteException();
		} catch (PublicacaoInexistenteException e) {
			dao.inserir(publicacao);
		}
	}
	
	public Publicacao pesquisar(String nome) throws PublicacaoInexistenteException, ConexaoException, SQLException{
		return dao.pesquisar(nome);
	}
	
	public void remover(Publicacao publicacao) throws ConexaoException, SQLException{
		dao.remover(publicacao);
	}
	
	public void alterar(Publicacao publicacao) throws ConexaoException, SQLException{
		dao.alterar(publicacao);
	}

}
