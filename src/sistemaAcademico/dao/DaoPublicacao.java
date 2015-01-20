package sistemaAcademico.dao;

import java.util.ArrayList;

import sistemAcademico.exceptions.PublicacaoInexistenteException;
import sistemaAcademico.classesBasicas.Publicacao;

public class DaoPublicacao implements DaoPublicacaoInt{
	
	private ArrayList<Publicacao> publicacoes = new ArrayList<Publicacao>();

	@Override
	public void inserir(Publicacao publicacao) {
		publicacoes.add(publicacao);
	}

	@Override
	public Publicacao pesquisar(String nome) throws PublicacaoInexistenteException {
		if(publicacoes.size() != 0){
			for(int i = 0; i <= publicacoes.size()-1; i++){
				if(publicacoes.get(i).getNome().equals(nome)){
					return publicacoes.get(i);
				}
			}
			throw new PublicacaoInexistenteException();
		}
		return null;
	}

	@Override
	public ArrayList<Publicacao> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alterar(Publicacao publicacao) {
		for (int i = 0; i <= publicacoes.size()-1; i++) {
			if(publicacao.getNome().equals(publicacoes.get(i).getNome())){
				publicacoes.set(i, publicacao);
			}
		}
	}

	@Override
	public void remover(Publicacao publicacao) {
		for (int i = 0; i <= publicacoes.size()-1; i++) {
			if(publicacao.getNome().equals(publicacoes.get(i).getNome())){
				publicacoes.remove(i);
			}
		}
	}

}
