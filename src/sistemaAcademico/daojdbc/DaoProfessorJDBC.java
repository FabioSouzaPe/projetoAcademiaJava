package sistemaAcademico.daojdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import sistemAcademico.exceptions.ErroConexaoException;
import sistemAcademico.exceptions.ProfessorInexistenteException;
import sistemaAcademico.classesBasicas.Professor;
import sistemaAcademico.conexao.Conexao;
import sistemaAcademico.conexao.ConexaoInt;


public class DaoProfessorJDBC implements DaoProfessorIntJDBC {
	
	
	
	 ArrayList< Professor> listaprof = new ArrayList<Professor>();
	 ConexaoInt conexao = new Conexao();

	@Override
	public void cadastrarProfessor(Professor professor) throws ErroConexaoException {
		String sql="insert into professor(Matricula,Admissao,Departamento,Instituicao,Titulo) values(?,?,?,?,?);";
		try {
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1,professor.getMatricula());
			//pst.setDate(2,professor.getAdmissao());
			pst.setString(3,professor.getDepartamento());
			pst.setString(4,professor.getInstituicao());
			//pst.setString(5,(professor.getTitulo());
			pst.executeUpdate();
			
			conexao.desconectar();
			
		} catch (SQLException e) {
			throw new ErroConexaoException();
		}
	}

	@Override
	public void alterar(Professor professor) throws ErroConexaoException {
		String sql="update Professor set Admissao=?, set Departamento=? , set Instituicao=?,set Titulo= ? where Matricula = ?";
		try {
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1,professor.getMatricula());
			//pst.setDate(2,professor.getAdmissao());
			pst.setString(3,professor.getDepartamento());
			pst.setString(4,professor.getInstituicao());
			//pst.setString(5,(professor.getTitulo());
			pst.executeUpdate();
			
			conexao.desconectar();
			
		} catch (SQLException e) {
			throw new ErroConexaoException();
		}
	}

	@Override
	public void remover(Professor professor) throws ErroConexaoException {
		String sql="delete * from Professor where Matricula=?";
		try {
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1,professor.getMatricula());
			
			conexao.desconectar();
			
		} catch (SQLException e) {
			throw new ErroConexaoException();
		}
		
	}

	@Override
	public ArrayList<Professor> consultarTudo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Professor pesquisarprofessor(String matricula)
			throws ProfessorInexistenteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
