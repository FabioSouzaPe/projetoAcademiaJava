package sistemaAcademico.daoJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sistemaAcademico.exceptions.AlunoInexistenteException;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.Publicacao;
import sistemaAcademico.conexao.Conexao;
import sistemaAcademico.conexao.ConexaoInt;

public class DaoAlunoJdbc implements DaoAlunoJDBCInt{
	
	private ConexaoInt conexao;
	
	public DaoAlunoJdbc(){
		conexao = new Conexao();
	}
	
	public void inserir(Aluno aluno, int chaveHistorico, int chavePessoa) throws ConexaoException, SQLException{
		java.util.Date data = aluno.getData();
		Date sqldata = new Date(data.getTime());
		String sql = "INSERT INTO aluno(matricula, DataAdmissao, IdPessoa, IdHistorico) VALUES(?,?,?,?)";
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1, aluno.getMatricula());
			pst.setDate(2, sqldata);
			pst.setInt(3, chaveHistorico);
			pst.setInt(4, chaveHistorico);
			pst.executeUpdate();
		}catch(ConexaoException e){
			throw new ConexaoException();
		} catch (SQLException ec) {
			throw new SQLException();
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException e){
				throw new ConexaoException();
			}
		}
	}
	
	public void remover(Aluno aluno) throws ConexaoException, SQLException{
		
		String sql = "DELETE FROM aluno WHERE matricula=?";
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1, aluno.getMatricula());
			pst.executeUpdate();
		} catch (ConexaoException e) {
			throw new ConexaoException();
		} catch (SQLException e) {
			throw new SQLException();
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException e){
				throw new ConexaoException();
			}
		}
		
	}
	
	public Aluno pesquisar(String matricula) throws ConexaoException, AlunoInexistenteException, SQLException{
		Aluno aluno = null;
		ArrayList<Publicacao> publicacoes = new ArrayList<Publicacao>();
		String sqlPubli = "SELECT nome FROM publicacao p "
				+ "INNER JOIN aluno a ON p.chave = a.matricula WHERE matricula=?";
		
		String sqlAluno = "SELCT * FROM aluno where matricula=?";
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sqlAluno);
			pst.setString(1, matricula);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				aluno = new Aluno();
				aluno.setMatricula(rs.getString("matricula"));
				aluno.setData(rs.getDate("data"));
				PreparedStatement pstP = conexao.conectar().prepareStatement(sqlPubli);
				pstP.setString(1, matricula);
				ResultSet rs2 = pstP.executeQuery();
				while(rs2.next()){
					Publicacao p = new Publicacao();
					p.setNome(rs2.getString("nome"));
					publicacoes.add(p);
				}
				aluno.setPublicacoes(publicacoes);
				return aluno;
			}
			throw new AlunoInexistenteException();
		}catch(ConexaoException e){
			throw new ConexaoException();
		}catch(SQLException e){
			throw new SQLException();
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException e){
				throw new ConexaoException();
			}
		}
	}
	
	public void alterar(Aluno aluno) throws ConexaoException, SQLException{
		
		String sql = ("UPDATE aluno set data=? where matricula=? ");
		try{
			java.util.Date data = aluno.getData();
			Date sqldata = new Date(data.getTime());
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setDate(1, sqldata);
			pst.setString(2, aluno.getMatricula());
			pst.executeUpdate();
		} catch (ConexaoException e) {
			throw new ConexaoException();
		} catch (SQLException e) {
			throw new SQLException();
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException e){
				throw new ConexaoException();
			}
		}
		
	}

}
