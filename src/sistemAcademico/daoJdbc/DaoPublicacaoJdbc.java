package sistemAcademico.daoJdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import sistemAcademico.exceptions.ConexaoException;
import sistemaAcademico.classesBasicas.Publicacao;
import sistemaAcademico.conexao.Conexao;
import sistemaAcademico.conexao.ConexaoInt;

public class DaoPublicacaoJdbc {
	
	private ConexaoInt conexao = new Conexao();
	
	public void inserer(Publicacao publicao){
		String sql = "INSERT INTO publicacao (nome, conteudo, fk_aluno, fk_professor, meio) VALUES(?,?,?,?,?)";
		try {
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1, publicao.getNome());
			pst.setString(2, publicao.getConteudo());
			pst.setString(3, publicao.getAluno().getMatricula());
			//pst.setInt(4, publicao.getProfessor());
		} catch (SQLException | ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
