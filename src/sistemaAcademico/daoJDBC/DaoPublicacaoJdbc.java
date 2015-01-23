package sistemaAcademico.daoJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.Publicacao;
import sistemaAcademico.conexao.Conexao;
import sistemaAcademico.conexao.ConexaoInt;
import sistemaAcademico.enuns.Meio;

public class DaoPublicacaoJdbc {
	
	private ConexaoInt conexao = new Conexao();
	
	
	public void inserir(Publicacao publicao){
		String sql = "INSERT INTO publicacao (nome, meiopublicacao, matriculaaluno, resumo, matriculaprofessor) VALUES(?,?,?,?,?)";
		try {
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1, publicao.getNome());
			pst.setString(2, publicao.getConteudo());
			pst.setString(3, publicao.getAluno().getMatricula());
			pst.setString(4, publicao.getProfessor().getMatricula());
			pst.setInt(5, publicao.getMeioDeComunicacao().getValor());
			pst.executeUpdate();
		} catch (SQLException | ConexaoException e) {
			e.printStackTrace();
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException e){
				System.out.println(e.getMessage());
			}
		}
	}
	
	public Publicacao pesquisar(String nome){
		String sql = "SELECT * FROM publicacao WHERE nome=?";
		Publicacao publicacao;
		Aluno aluno;
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1, nome);
			ResultSet rs= pst.executeQuery();
			if(rs.next()){
				aluno = new Aluno();
				publicacao = new Publicacao();
				publicacao.setId(rs.getInt("idnome"));
				publicacao.setNome(rs.getString("nome"));
				publicacao.setConteudo(rs.getString("resumo"));
				//publicacao.setMeioDeComunicacao(rs.getInt("meiopublicacao"));
				aluno.setMatricula(rs.getString("matriculaaluno"));
				
			}
		} catch (SQLException | ConexaoException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void alterar(Publicacao publicacao){
		String sql = "UPDATE Publicacao set nome=?, meiopublicacao=?, matriculaaluno=?"
				+ ",resumo=?, matriculaprofesso? WHERE idnome=?";
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1, publicacao.getNome());
			pst.setInt(2, publicacao.getMeioDeComunicacao().getValor());
			pst.setString(3, publicacao.getAluno().getMatricula());
			pst.setString(4, publicacao.getConteudo());
			pst.setString(5, publicacao.getProfessor().getMatricula());
			pst.executeUpdate();
		}catch(ConexaoException e){
			System.out.println(e.getMessage());
		} catch (SQLException ec) {
			System.out.println(ec.getMessage());
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException e){
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	public void remover(Publicacao publicacao){
		String sql = "DELET FROM publicacao WHERE IdNome=?";
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setInt(1, publicacao.getId());
			pst.executeUpdate();
		}catch(ConexaoException e){
			System.out.println(e.getMessage());
		} catch (SQLException ec) {
			System.out.println(ec.getMessage());
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException e){
				System.out.println(e.getMessage());
			}
		}
		
	}
	
}
