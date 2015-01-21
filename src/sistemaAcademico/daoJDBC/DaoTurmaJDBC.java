package sistemaAcademico.daoJDBC;

import java.net.ConnectException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.Disciplina;
import sistemaAcademico.classesBasicas.Professor;
import sistemaAcademico.classesBasicas.Turma;
import sistemaAcademico.dao.DaoTurmaInt;
import sistemaAcademico.enuns.Turno;

public class DaoTurmaJDBC implements DaoTurmaInt{

	
	private DaoConexao conexao = new DaoConexao();
	private PreparedStatement pst;
	private String SQLDelete = "Delete FROM Turma WHERE nomeTurma = ? ";
	private String SQLInsert = "INSERT INTO Turma (nomeTurma,professorTurma,turnoTurma,periodoTurma)values (?,?,?,?)";
	private String SQLSelectOne = "SELECT * FROM Turma where nomeTurma = ?";
	private String SQLSelectAll = "SELECT * FROM Turma";
	private String SQLUpdate = "UPDATE Turma SET nomeTurma = ?,professorTurma = ?, turnoTurma = ?, periodoTurma = ?";
	@Override
	public void cadastrarTurma(List<Aluno> a, String nome, Professor p,
			List<Disciplina> d, String periodo, Turno t) {
		
		try {
			pst = conexao.conectar().prepareStatement(SQLInsert);
			System.out.println("Conectou");
			pst.setString(1, nome);
			pst.setString(2,p.getProf());
			pst.setString(3, t.name());
			pst.setString(4, periodo);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//System.out.println("audyvysa");
			//System.out.println(e.getMessage());
		} 
		
		finally {
			
			try {
				
				conexao.desconectar();
			} catch (SQLException e){
				
				
					}
		}
	}

	@Override
	public void removerTurma(Turma turmaRemovida) {
		
		try {
			pst = conexao.conectar().prepareStatement(SQLDelete);
			pst.setString(1, turmaRemovida.getNomeDaTurma());
			pst.executeUpdate();
			
		}  catch (SQLException e) {
		
			System.out.println(e.getMessage());
		}
		
		finally {
			
			try {
				
				conexao.desconectar();
			} catch (SQLException e){
				System.out.println(e.getMessage());
		}

		}
	}
		
		

	@Override
	public Turma consultarTurma(String nome) {
		
		Turma turmaResultante = new Turma();
		
		try {
			
			pst = conexao.conectar().prepareStatement(SQLSelectOne);
			pst.setString(1, nome);
			ResultSet s = pst.executeQuery();
			if (s.next()){
			System.out.println(s.getString("nomeTurma"));
			turmaResultante.setNomeDaTurma(s.getString("nomeTurma"));
			//turmaResultante.setProfessorDaTurma((Professor) s.getObject("nomeProfessor"));
			turmaResultante.setPeriodoAtual(s.getString("periodoTurma"));
			//turmaResultante.setTurnoDaTurma((Turno) s.getObject("turnoTurma"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			
		} 
		
		finally {
			
			try {
				
				conexao.desconectar();
			} catch (SQLException e){

				System.out.println(e.getMessage());
		}
			
		}
		return turmaResultante;
	}

	@Override
	public List<Turma> consultarTurmas() {
		// TODO Auto-generated method stub
		ResultSet st = null;
		List<Turma> t = new ArrayList();
		Turma turma = new Turma();	
		try {
			pst = conexao.conectar().prepareStatement(SQLSelectAll);
			//pst.setString(1, turmaRemovida.getNomeDaTurma());
			st = pst.executeQuery();
			
		}  catch (SQLException e) {
		
			System.out.println(e.getMessage());
		}
		
		finally {
			
			try {
				
				conexao.desconectar();
			} catch (SQLException e){
				System.out.println(e.getMessage());
		}

		}
		
		try {
			int i =0;
			while (st.next()) {
				
				turma.setNomeDaTurma(st.getString("nomeTurma"));
				turma.setPeriodoAtual(st.getString("periodoTurma"));
				//turma.setProfessorDaTurma(st.get);
				//t.set(i, element);
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public void alterarTurma(Turma turmaAtual, String novoNome,
			Professor novoProfessor, Turno novoTurno, String novoPeriodo) {
		// TODO Auto-generated method stub
		//ResultSet st;
		try {
			pst = conexao.conectar().prepareStatement(SQLUpdate);
			//pst.setString(1, turmaRemovida.getNomeDaTurma());
			pst.executeQuery();
			
		}  catch (SQLException e) {
		
			System.out.println(e.getMessage());
		}
		
		finally {
			
			try {
				
				conexao.desconectar();
			} catch (SQLException e){
				System.out.println(e.getMessage());
		}
		
		if (novoNome != null) {
			
			try {
				pst.setString(1, turmaAtual.getNomeDaTurma());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			
			try {
				pst.setString(1, novoNome);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (novoProfessor != null) {
				
				try {
					pst.setString(2, turmaAtual.getProfessorDaTurma().getProf());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				
				try {
					pst.setString(2, novoProfessor.getProf());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
			
			
	}

	
	
	}
	
}
