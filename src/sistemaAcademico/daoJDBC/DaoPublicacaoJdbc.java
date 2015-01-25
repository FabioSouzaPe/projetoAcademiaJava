package sistemaAcademico.daoJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.PublicacaoInexistenteException;
import sistemaAcademico.classesBasicas.Aluno;
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
		Aluno aluno;
		
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1, nome);
			ResultSet rs= pst.executeQuery();
			if(rs.next()){
				aluno = new Aluno();
				publicacao = new Publicacao();
				publicacao.setId(rs.getInt("idPublicacao"));
				publicacao.setNome(rs.getString("nome"));
				publicacao.setConteudo(rs.getString("resumo"));
				for(Meio meio : Meio.values()){
					if(meio.getValor() == rs.getInt("MeioPublicacao")){
						publicacao.setMeioDeComunicacao(meio.setMeio(rs.getInt("MeioPublicacao")));
					}
				}
			aluno.setMatricula(rs.getString("matriculaaluno"));
			return publicacao;	
			}
			throw new PublicacaoInexistenteException();
		} catch (SQLException | ConexaoException e){
			throw e;
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException ee){
				throw ee;
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
		}catch(ConexaoException e){
			throw e;
		} catch (SQLException ec) {
			throw ec;
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException e){
				throw e;
			}
		}
		
	}
	
	public void remover(Publicacao publicacao) throws ConexaoException, SQLException{
		String sql = "DELETE FROM publicacao WHERE IdPublicacao=?";
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setInt(1, publicacao.getId());
			pst.executeUpdate();
		}catch(ConexaoException e){
			throw e;
		} catch (SQLException ec) {
			throw ec;
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException e){
				throw e;
			}
		}
		
	}
	
}
