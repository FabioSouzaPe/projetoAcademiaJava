package sistemaAcademico.regrasDeNegocio;

import java.sql.SQLException;
import java.util.ArrayList;

import sistemaAcademico.classesBasicas.Publicacao;
import sistemaAcademico.daoJDBC.DaoPublicacaoJDBCInt;
import sistemaAcademico.daoJDBC.DaoPublicacaoJdbc;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.ErroSQLException;
import sistemaAcademico.exceptions.PublicacaoExistenteException;
import sistemaAcademico.exceptions.PublicacaoInexistenteException;

public class RnPublicacaoJDBC {
	
	private DaoPublicacaoJDBCInt dao;
	
	public RnPublicacaoJDBC(){
		dao = new DaoPublicacaoJdbc();
	}
	
	public void cadastrarPublicacao(Publicacao publicacao) throws PublicacaoExistenteException, ErroSQLException, ConexaoException{
		try {
			Publicacao p = pesquisar(publicacao.getNome());
			throw new PublicacaoExistenteException();
		} catch (PublicacaoInexistenteException e) {
			dao.inserir(publicacao);
		}
	}
	
	public Publicacao pesquisar(String nome) throws PublicacaoInexistenteException, ConexaoException, ErroSQLException{
		return dao.pesquisar(nome);
	}
	
	public void remover(Publicacao publicacao) throws ConexaoException, ErroSQLException{
		dao.remover(publicacao);
	}
	
	public void alterar(Publicacao publicacao) throws ConexaoException, ErroSQLException{
		dao.alterar(publicacao);
	}
	
	public ArrayList<Publicacao> listarPublicacoes() throws ConexaoException, ErroSQLException, PublicacaoInexistenteException{
		if(dao.listar().size() == 0){
			throw new PublicacaoInexistenteException();
		}
		return dao.listar();
	}
	
	public ArrayList<Publicacao> listarPorMatricula(String matricula) throws ConexaoException, ErroSQLException, PublicacaoInexistenteException{
		if(dao.listarPorMatricula(matricula).size() == 0){
			throw new PublicacaoInexistenteException();
		}
		return dao.listarPorMatricula(matricula);
	}

}
