package sistemaAcademico.classesBasicas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import sistemaAcademico.dao.DaoPublicacao;
import sistemaAcademico.daoJDBC.DaoAlunoJdbc;
import sistemaAcademico.daoJDBC.DaoHistoricoEscolarJDBC;
import sistemaAcademico.daoJDBC.DaoPessoaJDBC;
import sistemaAcademico.daoJDBC.DaoPublicacaoJdbc;
import sistemaAcademico.enuns.Meio;
import sistemaAcademico.enuns.SituacaoAluno;
import sistemaAcademico.exceptions.AlunoExistenteException;
import sistemaAcademico.exceptions.AlunoInexistenteException;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.ErroSQLException;
import sistemaAcademico.exceptions.HistoricoExistenteException;
import sistemaAcademico.exceptions.HistoricoInexistenteException;
import sistemaAcademico.exceptions.PublicacaoExistenteException;
import sistemaAcademico.exceptions.PublicacaoInexistenteException;
import sistemaAcademico.fachada.Fachada;
import sistemaAcademico.regrasDeNegocio.RnAlunoJDBC;
import sistemaAcademico.regrasDeNegocio.RnPublicacaoJDBC;


public class Teste {
	
	public static void main(String[] args) throws SQLException{
		Fachada fachada = Fachada.getInstancia();
		Aluno a = new Aluno();
		a.setMatricula("000444");
		Date data = new Date();
		a.setData(data);
		
		Fone fone = new Fone();
		fone.setFone("6666-9999");
		fone.setDdd("81");
		Endereco end = new Endereco();
		end.setBairro("bairu");
		end.setCep("019237191");
		end.setCidade("prazeres");
		end.setLogradouro("rua nao sei de que");
		end.setNumero("37");
		end.setUf("pe");
		Pessoa pessoa = new Pessoa();
		pessoa.addFones(fone);
		pessoa.setEndereco(end);
		pessoa.setCpf("2973384666");
		pessoa.setNome("De Souza");
		pessoa.setSexo('m');
		DaoPessoaJDBC daoM = new DaoPessoaJDBC();
		
		HistoricoEscolar historico = new HistoricoEscolar();
		historico.setSituacao(SituacaoAluno.ANDAMENTO);
		historico.setAluno(a);
		try {
			//int chavePessoa = daoM.addPessoa(pessoa);
			//int chaveHistorico = fachada.cadastrarHistorico(historico);
			//fachada.cadastrarAluno(a, chavePessoa, chaveHistorico)
			HistoricoEscolar teste = new HistoricoEscolar();
			teste = fachada.pesquisarHistorico("000444");
			System.out.println(teste.getSituacao());
		} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErroSQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HistoricoInexistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		RnPublicacaoJDBC rnPublica = new RnPublicacaoJDBC();
		Aluno aa = new Aluno();
		Professor professor = new Professor();
		aa.setMatricula("000333");
		professor.setMatricula("12234");
		Publicacao publicacao = new Publicacao();
		publicacao.setAluno(aa);
		publicacao.setProfessor(professor);
		publicacao.setConteudo("testar");
		publicacao.setNome("Teste2");
		publicacao.setMeioDeComunicacao(Meio.SITE);
		*/
		//Teste da classe publicação. Finalizado?
		//try {
		//	rnPublica.listarPorMatricula("000333");
		//} catch (ConexaoException | ErroSQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//} catch (PublicacaoInexistenteException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
		/*teste do pesquisarHistorico
		try {
			//Fachada fachada = Fachada.getInstancia();
			HistoricoEscolar e = new HistoricoEscolar();
			//e = fachada.pesquisarHistorico("000333");
			//e.setObs("Bom demais");
			//fachada.alterarHistorico(e);
		e = Fachada.getInstancia().pesquisarHistorico("000333");
			System.out.println("Nota aluno : " +e.getConficienteRedimento()+ "\n" + "Observaçoes : " +e.getObs()+"\n" + 
								"Situaçao : "+e.getSituacao()+"\n" + "Matricula : " +e.getAluno().getMatricula() +"\n" +
								"Nome aluno: "+e.getAluno().getPessoa().getNome()+"\n" +
								"Data : "+e.getData()+"\n" +
								"Nome disciplia : "+e.getDisciplina().getNome()+ "\n" + "Carga horaria : "+e.getDisciplina().getCargaHoraria());
					
		} catch (ConexaoException | ErroSQLException e) {
			System.out.println(e.getMessage());	
		} catch (HistoricoInexistenteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		
		//HistoricoEscolar e = new HistoricoEscolar();
		//DaoHistoricoEscolarJDBC de = new DaoHistoricoEscolarJDBC();
		//try {
		//	e = de.pesquisar("000333");
		//	System.out.println("Nota aluno : " +e.getConficienteRedimento()+ "\n" + "Observaçoes : " +e.getObs()+"\n" + 
		//			"Situaçao : "+e.getSituacao()+"\n" + "Matricula : " +e.getAluno().getMatricula() +"\n" +
		//			"Nome aluno: "+e.getAluno().getPessoa().getNome()+"\n" +
		//			"Nome disciplia : "+e.getDisciplina().getNome()+ "\n" + "Carga horaria : "+e.getDisciplina().getCargaHoraria());
		//} catch (ConexaoException | HistoricoInexistenteException ee) {
		//	// TODO Auto-generated catch block
		//	ee.printStackTrace();
		//}
	}

}
