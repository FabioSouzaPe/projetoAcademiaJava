package sistemaAcademico.regrasDeNegocio;

import sistemAcademico.exceptions.PublicacaoExistenteException;
import sistemAcademico.exceptions.PublicacaoInexistenteException;
import sistemaAcademico.classesBasicas.Publicacao;
import sistemaAcademico.dao.DaoPublicacao;
import sistemaAcademico.dao.DaoPublicacaoInt;

public class RnPublicacao {
	
	DaoPublicacaoInt dao = new DaoPublicacao();
	
	public void cadastrar(Publicacao publicacao) throws PublicacaoExistenteException{
		try{
			if(pesquisar(publicacao.getNome()) == null){
				dao.inserir(publicacao);
			}else{
				throw new PublicacaoExistenteException();
			}
		}catch(PublicacaoInexistenteException e){
			dao.inserir(publicacao);
		}
	}
	
	public void remover(Publicacao publicacao){
		try{
			if(pesquisar(publicacao.getNome())!=null){
				dao.remover(publicacao);
			}
		}catch(PublicacaoInexistenteException e){
			System.out.println(e.getMessage());
		}
	}
	
	public void alterar(Publicacao publicacao){
		try{
			if(pesquisar(publicacao.getNome())!=null){
				dao.alterar(publicacao);
			}
		}catch(PublicacaoInexistenteException e){
			System.out.println(e.getMessage());
		}
	}
	
	public Publicacao pesquisar(String nome) throws PublicacaoInexistenteException{
		return dao.pesquisar(nome);
	}

}
