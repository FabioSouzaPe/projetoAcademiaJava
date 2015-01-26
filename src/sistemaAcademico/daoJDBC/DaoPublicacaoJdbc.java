package sistemaAcademico.daoJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.ArrayList;

import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.PublicacaoInexistenteException;
import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.Pessoa;
import sistemaAcademico.classesBasicas.Professor;
import sistemaAcademico.classesBasicas.Publicacao;
import sistemaAcademico.conexao.Conexao;
import sistemaAcademico.conexao.ConexaoInt;
import sistemaAcademico.enuns.Meio;

public class DaoPublicacaoJdbc implements DaoPublicacaoJDBCInt{
	
	private ConexaoInt conexao = new Conexao();
	
	
	public void inserir(Publicacao publicao) throws SQLException, ConexaoException{
		String sql = "INSERT INTO publicacao (Nome, MeioPublicacao, matriculaaluno, resumo, matriculaprofessor) VALUES(?,?,?,?,?)";
		try {
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1, publicao.getNome());
			pst.setString(4, publicao.getConteudo());
			pst.setString(3, publicao.getAluno().getMatricula());
			pst.setString(5, publicao.getProfessor().getMatricula());
			pst.setInt(2, publicao.getMeioDeComunicacao().getValor());
			pst.executeUpdate();
		}catch(SQLException e){
			throw new SQLException(e);
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException e){
				System.out.println(e.getMessage());
			}
		}
	}
	
	public Publicacao pesquisar(String nome) throws PublicacaoInexistenteException, ConexaoException,SQLException{
		String sql = "SELECT * FROM publicacao WHERE nome=?";
		Publicacao publicacao;
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1, nome);
			ResultSet rs= pst.executeQuery();
			if(rs.next()){
				publicacao = new Publicacao();
				publicacao.setId(rs.getInt("idPublicacao"));
				publicacao.setNome(rs.getString("nome"));
				publicacao.setConteudo(rs.getString("resumo"));
				for(Meio meio : Meio.values()){
					if(meio.getValor() == rs.getInt("MeioPublicacao")){
						publicacao.setMeioDeComunicacao(meio.setMeio(rs.getInt("MeioPublicacao")));
					}
				}
			return publicacao;	
			}
			throw new PublicacaoInexistenteException();
		}catch(SQLException e){
			throw new SQLException(e);
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException ee){
				throw new ConexaoException();
			}
		}
	}
	
 	
	public void alterar(Publicacao publicacao) throws ConexaoException, SQLException{
		String sql = "UPDATE Publicacao set nome=?, meiopublicacao=?, matriculaaluno=?"
				+ ",resumo=?, matriculaprofesso? WHERE idPublicacao=?";
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1, publicacao.getNome());
			pst.setInt(2, publicacao.getMeioDeComunicacao().getValor());
			pst.setString(3, publicacao.getAluno().getMatricula());
			pst.setString(4, publicacao.getConteudo());
			pst.setString(5, publicacao.getProfessor().getMatricula());
			pst.executeUpdate();
		}catch(SQLException e){
			throw new SQLException(e);
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException e){
				throw new ConexaoException();
			}
		}
		
	}
	
	public void remover(Publicacao publicacao) throws ConexaoException, SQLException{
		String sql = "DELETE FROM publicacao WHERE IdPublicacao=?";
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setInt(1, publicacao.getId());
			pst.executeUpdate();
		}catch(SQLException e){
			throw new SQLException(e);
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException e){
				throw new ConexaoException();
			}
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see sistemaAcademico.daoJDBC.DaoPublicacaoJDBCInt#listar()
	 * Usado para gerar um relatorio de todos as publicações de todos os alunos.
	 */
	public ArrayList<Publicacao> listar() throws ConexaoException, SQLException{
		String sql = "SELECT p.IdPublicacao, p.nome, pp.matricula, pp.departamento, a.matricula, p.meiopublicacao, p.resumo, pa.Nome FROM publicacao p "
				+ "INNER JOIN Aluno a ON a.Matricula = p.MatriculaAluno "
				+ "INNER JOIN Professor pp ON p.MatriculaProfessor = pp.Matricula "
				+ "INNER JOIN Pessoa pa ON a.IdPessoa = pa.IdPessoa"; 
		ArrayList<Publicacao> publicacoes = new ArrayList<Publicacao>();
		Publicacao publicacao;
		Professor professor;
		Aluno aluno;
		Pessoa pessoa;
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				publicacao = new Publicacao();
				professor = new Professor();
				aluno = new Aluno();
				pessoa = new Pessoa();
				publicacao.setId(rs.getInt("idPublicacao"));
				publicacao.setNome(rs.getString("nome"));
				publicacao.setConteudo(rs.getString("resumo"));
				for(Meio meio : Meio.values()){
					if(meio.getValor() == rs.getInt("MeioPublicacao")){
						publicacao.setMeioDeComunicacao(meio.setMeio(rs.getInt("MeioPublicacao")));
					}
				}
				pessoa.setNome(rs.getString("pa.Nome"));
				aluno.setMatricula(rs.getString("a.Matricula"));
				professor.setMatricula(rs.getString("pp.Matricula"));
				professor.setDepartamento(rs.getString("Departamento"));
				aluno.setPessoa(pessoa);
				publicacao.setProfessor(professor);
				publicacao.setAluno(aluno);
				publicacoes.add(publicacao);
			}
			return publicacoes;
		}catch(SQLException e){
			throw new SQLException(e);
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException e){
				throw new ConexaoException();
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see sistemaAcademico.daoJDBC.DaoPublicacaoJDBCInt#listarPorMatricula(java.lang.String)
	 * Usado para gerar um relatoria das publicações de um aluno, usando como paramentro da pesquisa
	 * a matricula do mesmo.
	 */
	public ArrayList<Publicacao> listarPorMatricula(String matricula) throws ConexaoException, SQLException{
		String sql = "SELECT p.IdPublicacao, p.nome, pp.matricula, pp.departamento, a.matricula, p.meiopublicacao, p.resumo FROM publicacao p "
				+ "INNER JOIN Aluno a ON a.Matricula = p.MatriculaAluno "
				+ "INNER JOIN Professor pp ON p.MatriculaProfessor = pp.Matricula"
				+ "WHERE a.Matricula=?"; 
		ArrayList<Publicacao> publicacoes = new ArrayList<Publicacao>();
		Publicacao publicacao;
		Professor professor;
		Aluno aluno;
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1, matricula);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				publicacao = new Publicacao();
				professor = new Professor();
				aluno = new Aluno();
				publicacao.setId(rs.getInt("idPublicacao"));
				publicacao.setNome(rs.getString("nome"));
				publicacao.setConteudo(rs.getString("resumo"));
				for(Meio meio : Meio.values()){
					if(meio.getValor() == rs.getInt("MeioPublicacao")){
						publicacao.setMeioDeComunicacao(meio.setMeio(rs.getInt("MeioPublicacao")));
					}
				}
				aluno.setMatricula(rs.getString("a.Matricula"));
				professor.setMatricula(rs.getString("pp.Matricula"));
				professor.setDepartamento(rs.getString("Departamento"));
				publicacao.setProfessor(professor);
				publicacao.setAluno(aluno);
				publicacoes.add(publicacao);
			}
			return publicacoes;
		}catch(SQLException e){
			throw new SQLException(e);
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException e){
				throw new ConexaoException();
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see sistemaAcademico.daoJDBC.DaoPublicacaoJDBCInt#listarPorAluno()
	 * Usado para o aluno pesquisar todas as publicações que possiu, assim que logado no sistema.
	 */
	public ArrayList<Publicacao> listarPorAluno() throws ConexaoException, SQLException{
		String sql = "SELECT p.IdPublicacao, p.nome, pp.matricula, pp.departamento, p.meiopublicacao, p.resumo FROM publicacao p "
				+ "INNER JOIN Professor pp ON p.MatriculaProfessor = pp.Matricula"; 
		ArrayList<Publicacao> publicacoes = new ArrayList<Publicacao>();
		Publicacao publicacao;
		Professor professor;
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				publicacao = new Publicacao();
				professor = new Professor();
				publicacao.setId(rs.getInt("idPublicacao"));
				publicacao.setNome(rs.getString("nome"));
				publicacao.setConteudo(rs.getString("resumo"));
				for(Meio meio : Meio.values()){
					if(meio.getValor() == rs.getInt("MeioPublicacao")){
						publicacao.setMeioDeComunicacao(meio.setMeio(rs.getInt("MeioPublicacao")));
					}
				}
				professor.setMatricula(rs.getString("pp.Matricula"));
				professor.setDepartamento(rs.getString("Departamento"));
				publicacao.setProfessor(professor);
				publicacoes.add(publicacao);
			}
			return publicacoes;
		}catch(SQLException e){
			throw new SQLException(e);
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException e){
				throw new ConexaoException();
			}
		}
	}
	
}
