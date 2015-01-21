package sistemAcademico.daoJdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sistemAcademico.exceptions.ConexaoException;
import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.Pessoa;
import sistemaAcademico.classesBasicas.Publicacao;
import sistemaAcademico.conexao.Conexao;
import sistemaAcademico.conexao.ConexaoInt;

public class DaoAlunoJdbc {
	
	private ConexaoInt conexao;
	
	public DaoAlunoJdbc(){
		conexao = new Conexao();
	}
	
	public void inserir(Aluno aluno, int chave){
		java.util.Date data = aluno.getData();
		Date sqldata = new Date(data.getTime());
		String sql = "INSERT INTO aluno(matricula, data, fk_pessoa) VALUES(?,?,?)";
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1, aluno.getMatricula());
			pst.setInt(3, chave);
			pst.setDate(2, sqldata);
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
	
	public void remover(Aluno aluno){
		
		String sql = "DELETE FROM aluno WHERE matricula=?";
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1, aluno.getMatricula());
			pst.executeUpdate();
		} catch (ConexaoException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException e){
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	public Aluno pesquisar(String matricula) throws ConexaoException{
		Aluno aluno = null;
		ArrayList<Publicacao> publicacoes = new ArrayList<Publicacao>();
		String sqlPubli = "SELECT nome FROM publicacao p "
				+ "INNER JOIN aluno a ON p.chave = a.matricula WHERE matricula=?";
		
		String sqlAluno = "SELCT + FROM aluno where matricula=?";
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
			throw new NullPointerException();
		}catch(ConexaoException e){
			throw new ConexaoException();
		}catch(SQLException e){
			throw new ConexaoException();
		}
	}
	

}
