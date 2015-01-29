package sistemaAcademico.fachada;


import java.util.ArrayList;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.HistoricoEscolar;
import sistemaAcademico.classesBasicas.Publicacao;
import sistemaAcademico.exceptions.AlunoExistenteException;
import sistemaAcademico.exceptions.AlunoInexistenteException;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.ErroSQLException;
import sistemaAcademico.exceptions.HistoricoExistenteException;
import sistemaAcademico.exceptions.HistoricoInexistenteException;
import sistemaAcademico.exceptions.PublicacaoExistenteException;
import sistemaAcademico.exceptions.PublicacaoInexistenteException;
import sistemaAcademico.regrasDeNegocio.RnAlunoJDBC;
import sistemaAcademico.regrasDeNegocio.RnHistoricoEscolarJDBC;
import sistemaAcademico.regrasDeNegocio.RnPublicacaoJDBC;

public class Fachada {
	
	private static Fachada instancia;
	
	private RnAlunoJDBC regraAluno;
	private RnHistoricoEscolarJDBC regraHistorico;
	private RnPublicacaoJDBC regraPublicacao;
	
	private Fachada(){
		this.regraAluno = new RnAlunoJDBC();
		this.regraHistorico = new RnHistoricoEscolarJDBC();
		this.regraPublicacao = new RnPublicacaoJDBC();
	}
	
	public static Fachada getInstancia(){
		if(instancia == null){
			instancia = new Fachada();
		}
		
		return instancia;
	}
	
	public void adicionarAluno(Aluno aluno, int chavePessoa, int chaveHistorico) throws ConexaoException, ErroSQLException, AlunoExistenteException{
		this.regraAluno.cadastrarAluno(aluno, chavePessoa, chaveHistorico);
	}
	
	public Aluno pesquisarAluno(String matricula) throws ConexaoException, AlunoInexistenteException, ErroSQLException{
		return this.regraAluno.pesquisar(matricula);
	}
	
	public void alterarAluno(Aluno aluno) throws ConexaoException, ErroSQLException{
		this.regraAluno.alterar(aluno);
	}
	
	public void removerAluno(Aluno aluno) throws ConexaoException, ErroSQLException{
		this.regraAluno.remover(aluno);
	}
	
	public ArrayList<Aluno> listarAlunos() throws ConexaoException, ErroSQLException{
		return this.regraAluno.listar();
	}
	
	public void cadastrarPublicacao(Publicacao publicacao) throws PublicacaoExistenteException, ErroSQLException, ConexaoException{
		this.regraPublicacao.cadastrarPublicacao(publicacao);
	}
	
	public void removerPublicacao(Publicacao publicacao) throws ConexaoException, ErroSQLException{
		this.regraPublicacao.remover(publicacao);
	}
	
	public void alterarPublicacao(Publicacao publicacao) throws ConexaoException, ErroSQLException{
		this.regraPublicacao.alterar(publicacao);
	}
	
	public Publicacao pesquisarPublicacao(String nome) throws PublicacaoInexistenteException, ConexaoException, ErroSQLException{
		return this.regraPublicacao.pesquisar(nome);
	}
	
	public ArrayList<Publicacao> listarPublicacoes() throws ConexaoException, ErroSQLException, PublicacaoInexistenteException{
		return this.regraPublicacao.listarPublicacoes();
	}
	
	public ArrayList<Publicacao> listarPublicacoesMatricula(String matricula) throws ConexaoException, ErroSQLException, PublicacaoInexistenteException{
		return this.regraPublicacao.listarPorMatricula(matricula);
	}
	
	public int cadastrarHistorico(HistoricoEscolar historico) throws ConexaoException, ErroSQLException, HistoricoExistenteException{
		return this.regraHistorico.cadastrarHistorico(historico);
	}
	
	public HistoricoEscolar pesquisarHistorico(String matricula) throws ConexaoException, ErroSQLException, HistoricoInexistenteException{
		return this.regraHistorico.pesquisar(matricula);
	}
	
	public void removerHistorico(HistoricoEscolar historico) throws ConexaoException, ErroSQLException{
		this.regraHistorico.remover(historico);
	}
	
	public void alterarHistorico(HistoricoEscolar historico) throws ConexaoException, ErroSQLException{
		this.regraHistorico.alterar(historico);
	}

}
