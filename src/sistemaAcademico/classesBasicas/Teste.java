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
import sistemaAcademico.regrasDeNegocio.RnAlunoJDBC;


public class Teste {
	
	public static void main(String[] args) throws SQLException{
		DaoAlunoJdbc dao = new DaoAlunoJdbc();
		RnAlunoJDBC rn = new RnAlunoJDBC();
		ArrayList<Publicacao> publi = new ArrayList<Publicacao>();
		Aluno a = new Aluno();
		a.setMatricula("0123");
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
		
		
		//Publicacao pu = dao2.pesquisar("Isso aqui vai longe");
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
