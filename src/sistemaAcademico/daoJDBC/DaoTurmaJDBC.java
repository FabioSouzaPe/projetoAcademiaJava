package sistemaAcademico.daoJDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.Disciplina;
import sistemaAcademico.classesBasicas.Professor;
import sistemaAcademico.classesBasicas.Turma;
import sistemaAcademico.dao.DaoTurmaInt;
import sistemaAcademico.enuns.Turno;

public class DaoTurmaJDBC implements DaoTurmaInt{

	
	private DaoConexao conexao = new DaoConexao();
	PreparedStatement pst;
	//String SQL1 = "SELECT nomeProfessor FROM Professor WHERE ";
	String SQL2 = "INSERT INTO Turma (nomeTurma,ProfessorTurma,TurnoTurma,periodoAtual)values (?,?,?,?)";
	
	@Override
	public void cadastrarTurma(List<Aluno> a, String nome, Professor p,
			List<Disciplina> d, String periodo, Turno t) {
		// TODO Auto-generated method stub
		
		//pst = conexao.conectar().prepareStatement(SQL1);
		
		//String nomeProfessor = pst.
		
		
		try {
			pst = conexao.conectar().prepareStatement(SQL2);
			
			pst.setString(1, nome);
			pst.setString(2,p.getNome());
			pst.setString(3, t.name());
			pst.setString(4, periodo);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				
				conexao.desconectar();
			} catch (SQLException e){
				
				throw new SQLException();
		}

		
		}
	}

	@Override
	public void removerTurma(Turma TurmaRemovida) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Turma consultarTurma(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Turma> consultarTurmas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alterarTurma(Turma turmaAtual, String novoNome,
			Professor novoProfessor, Turno novoTurno, String novoPeriodo) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
