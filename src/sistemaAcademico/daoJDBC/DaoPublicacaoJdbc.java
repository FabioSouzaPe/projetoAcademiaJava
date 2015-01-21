package sistemaAcademico.daoJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.classesBasicas.Publicacao;
import sistemaAcademico.conexao.Conexao;
import sistemaAcademico.conexao.ConexaoInt;
import sistemaAcademico.enuns.Meio;

public class DaoPublicacaoJdbc {
	
	private ConexaoInt conexao = new Conexao();
	
	
	public void inserir(Publicacao publicao){
		String sql = "INSERT INTO publicacao (nome, conteudo, fk_aluno, fk_professor, meio) VALUES(?,?,?,?,?)";
		try {
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1, publicao.getNome());
			pst.setString(2, publicao.getConteudo());
			pst.setString(3, publicao.getAluno().getMatricula());
			//pst.setInt(4, publicao.getProfessor());
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
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1, nome);
			ResultSet rs= pst.executeQuery();
			if(rs.next()){
				publicacao = new Publicacao();
				publicacao.setNome(rs.getString("nome"));
			}
		} catch (SQLException | ConexaoException e){
			e.printStackTrace();
		}
		return null;
	}
	
}
