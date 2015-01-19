package sistemaAcademico.dao;

import java.util.ArrayList;

import sistemAcademico.exceptions.PublicacaoInexistenteException;
import sistemaAcademico.classesBasicas.Publicacao;

public interface DaoPublicacaoInt {
	
	public void inserir(Publicacao publicacao);
	public Publicacao pesquisar(String nome) throws PublicacaoInexistenteException;
	public ArrayList<Publicacao> listar();
	public void alterar(Publicacao publicacao);
	public void remover(Publicacao publicacao);

}
