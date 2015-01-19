package sistemaAcademico.dao;

import java.util.ArrayList;

import sistemAcademico.exceptions.PublicacaoInexistenteException;
import sistemaAcademico.classesBasicas.Publicacao;

public class DaoPublicacao implements DaoPublicacaoInt{
	
	private ArrayList<Publicacao> publicacoes = new ArrayList<Publicacao>();

	@Override
	public void inserir(Publicacao publicacao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Publicacao pesquisar(String nome)
			throws PublicacaoInexistenteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Publicacao> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alterar(Publicacao publicacao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(Publicacao publicacao) {
		// TODO Auto-generated method stub
		
	}

}
