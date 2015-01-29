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
import sistemaAcademico.exceptions.AlunoExistenteException;
import sistemaAcademico.exceptions.AlunoInexistenteException;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.ErroSQLException;
import sistemaAcademico.exceptions.HistoricoInexistenteException;
import sistemaAcademico.exceptions.PublicacaoExistenteException;
import sistemaAcademico.exceptions.PublicacaoInexistenteException;
import sistemaAcademico.fachada.Fachada;
import sistemaAcademico.regrasDeNegocio.RnAlunoJDBC;
import sistemaAcademico.regrasDeNegocio.RnPublicacaoJDBC;


public class Teste {
	
	public static void main(String[] args) throws SQLException{
		DaoAlunoJdbc dao = new DaoAlunoJdbc();
		RnAlunoJDBC rn = new RnAlunoJDBC();
		ArrayList<Publicacao> publi = new ArrayList<Publicacao>();
		Aluno a = new Aluno();
		a.setMatricula("000333");
		Date data = new Date();
		a.setData(data);
		
		
		Professor pp = new Professor();
		pp.setMatricula("1111");
		Publicacao p = new Publicacao();
		p.setAluno(a);
		p.setNome("Isso aqui vai longe");
		p.setConteudo("Conteudo Muito Ruim");
		p.setMeioDeComunicacao(Meio.SITE);
		p.setProfessor(pp);
		DaoPublicacaoJdbc dao2 = new DaoPublicacaoJdbc();
		
		
		/*
		 * teste dos capiroto
		 */
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
		pessoa.setCpf("08731338462");
		pessoa.setNome("De morais");
		pessoa.setSexo('m');
		DaoPessoaJDBC daoM = new DaoPessoaJDBC();
		
		//try {
		//	int i = daoM.addPessoa(pessoa);
		//	dao.inserir(a, i);
		//} catch (ClassNotFoundException | ConexaoException e) {
		//	// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
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
		
		
		try {
			rnPublica.listarPorMatricula("1");
		} catch (ConexaoException | ErroSQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PublicacaoInexistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*teste do pesquisarHistorico
		try {
			HistoricoEscolar e = new HistoricoEscolar();
			e = Fachada.getInstancia().pesquisarHistorico("000333");
			System.out.println("Nota aluno : " +e.getConficienteRedimento()+ "\n" + "Observaçoes : " +e.getObs()+"\n" + 
								"Situaçao : "+e.getSituacao()+"\n" + "Matricula : " +e.getAluno().getMatricula() +"\n" +
								"Nome aluno: "+e.getAluno().getPessoa().getNome()+"\n" +
								"Nome disciplia : "+e.getDisciplina().getNome()+ "\n" + "Carga horaria : "+e.getDisciplina().getCargaHoraria());
					
		} catch (ConexaoException | ErroSQLException
				| HistoricoInexistenteException e) {
			System.out.println(e.getMessage());	
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
		
		//try {
		//	dao2.listar();
		//	for (int i = 0; i < dao2.listar().size(); i++) {
		//		System.out.println(dao2.listar().get(i).getNome()
		//				+ dao2.listar().get(i).getConteudo() +
		//				dao2.listar().get(i).getMeioDeComunicacao()+
		//				dao2.listar().get(i).getAluno().getPessoa().getNome());
		//	}
		//} catch (ConexaoException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
			//try {
			//	Publicacao pu = dao2.pesquisar("nome");
			//	System.out.print(pu.toString());
			//} catch (PublicacaoInexistenteException | ConexaoException | SQLException e) {
			//	// TODO Auto-generated catch block
			//	System.out.print(e.getMessage());
			//}
		
		//System.out.println(pu.toString());
		//dao2.remover(pu);
		
		
		
		
		/**
			try {
				//rn.remover(a);
				Aluno teste = new Aluno();
				teste = rn.pesquisar("0123");
				//rn.cadastrarAluno(a, 0);
				System.out.println(teste.toString()+" Publicações  : ");
				for(int i = 0; i < teste.getPublicacoes().size(); i++){
					System.out.println("Conteudo : "+teste.getPublicacoes().get(i).getConteudo());
				}
			} catch (ConexaoException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AlunoInexistenteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   **/
		
	}

}
