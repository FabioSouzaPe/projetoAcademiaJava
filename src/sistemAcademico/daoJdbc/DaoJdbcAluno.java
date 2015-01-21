package sistemAcademico.daoJdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import sistemAcademico.exceptions.ConexaoException;
import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.conexao.Conexao;
import sistemaAcademico.conexao.ConexaoInt;

public class DaoJdbcAluno {
	
	private ConexaoInt conexao;
	
	public DaoJdbcAluno(){
		conexao = new Conexao();
	}
	
	public void inserir(Aluno aluno){
		String sql = "insert into aluno(matricula, fk_publicacao,data) values(?,?,?)";
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1, aluno.getMatricula());
			pst.setDate(3, (Date) aluno.getData());
			for(int i = 0; i <= aluno.getPublicacoes().size()-1;i++){
				pst.setString(2, aluno.getPublicacoes().get(i).getConteudo());
			}
		}catch(ConexaoException e){
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException e){
				System.out.println(e.getMessage());
			}
		}
	}

}
