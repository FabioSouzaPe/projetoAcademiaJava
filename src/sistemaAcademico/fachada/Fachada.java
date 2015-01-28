package sistemaAcademico.fachada;


import java.util.ArrayList;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.HistoricoEscolar;
import sistemaAcademico.exceptions.AlunoExistenteException;
import sistemaAcademico.exceptions.AlunoInexistenteException;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.ErroSQLException;
import sistemaAcademico.exceptions.HistoricoExistenteException;
import sistemaAcademico.exceptions.HistoricoInexistenteException;
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
	}
	
	public static Fachada getInstancia(){
		if(instancia == null){
			instancia = new Fachada();
		}
		
		return instancia;
	}
	
	public void adicionarAluno(Aluno aluno, int i) throws ConexaoException, ErroSQLException, AlunoExistenteException{
		this.regraAluno.cadastrarAluno(aluno, i);
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
	
	public void cadastrarHistorico(HistoricoEscolar historico) throws ConexaoException, ErroSQLException, HistoricoExistenteException{
		this.regraHistorico.cadastrarHistorico(historico);
	}
	
	public HistoricoEscolar pesquisarHistorico(String matricula) throws ConexaoException, ErroSQLException, HistoricoInexistenteException{
		return this.regraHistorico.pesquisar(matricula);
	}

}
