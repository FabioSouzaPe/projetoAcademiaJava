package sistemaAcademico.daoJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.Turma;
import sistemaAcademico.conexao.Conexao;
import sistemaAcademico.enuns.Turno;
import sistemaAcademico.exceptions.ConexaoException;

public class DaoTurmaJDBC implements DaoTurmaJDBCInt{


	private Conexao conexao = new Conexao();
	private PreparedStatement pst;
	private String SQLDelete = "Delete FROM Turma WHERE nome = ? ";
	private String SQLInsert = "INSERT INTO Turma (idCurso,nome,turno,periodo)values (?,?,?,?)";
	private String SQLSelectOne = "SELECT * FROM Turma where nome = ?";
	private String SQLUpdate = "UPDATE Turma SET nome = ?, turno = ?, periodo = ? where idTurma = ?";
	private String SQLSelectAllALuno = "SELECT nome,matricula,CPF,Sexo FROM ALUNO a INNER JOIN PESSOA p on a.idPessoa = p.idPessoa";
	//private String SQLUpdateTurnoTurma = "UPDATE Turma SET turno = ?";
	private String SQLInsertAluno = "UPDATE ALUNO set idTurma = ? WHERE matricula = ?";
	private String SQLDeleteAluno = "UPDATE Aluno set idTurma = ? WHERE matricula = ? ";

	@Override
	public void cadastrarTurma(Turma turma) throws ConexaoException, SQLException {

		pst = conexao.conectar().prepareStatement(SQLInsert);

		//System.out.println(turma.getNomeDaTurma());
		pst.setInt(1, turma.getCurso().getId());
		//pst.setString(2,turma.getProfessorDaTurma().getMatricula());
		pst.setString(2, turma.getNomeDaTurma());
		pst.setString(3, turma.getTurnoDaTurma().name());
		pst.setString(4, turma.getPeriodoAtual());
		pst.executeUpdate();

		try {
			conexao.desconectar();
		} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			throw new ConexaoException();
		} 
	}


	@Override
	public void removerTurma(Turma turmaRemovida) throws ConexaoException, SQLException{

		try {
			try {
				pst = conexao.conectar().prepareStatement(SQLDelete);

			} catch (ConexaoException e) {
				// TODO Auto-generated catch block
				throw new ConexaoException();
			}

			pst.setString(1, turmaRemovida.getNomeDaTurma());
			pst.executeUpdate();

		}  catch (SQLException e) {

			throw new SQLException();
		}

		finally {

			try {
				conexao.desconectar();
			} catch (ConexaoException e) {
				// TODO Auto-generated catch block
				throw new ConexaoException();
			}
		}
	}

	@Override
	public Turma consultarTurma(String nome) throws ConexaoException, SQLException {

		Turma turmaResultante = new Turma();
		Turno aux;
		try {

			try {
				pst = conexao.conectar().prepareStatement(SQLSelectOne);
			} catch (ConexaoException e) {
				// TODO Auto-generated catch block
				throw new ConexaoException();
			}

			pst.setString(1, nome);
			ResultSet s = pst.executeQuery();
			
			if (s.next()){
				turmaResultante.setId(s.getInt("IdTurma"));
				turmaResultante.setNomeDaTurma(s.getString("nome"));
				turmaResultante.setPeriodoAtual(s.getString("periodo"));
				turmaResultante.setIdCurso(s.getInt("Idcurso"));
				
				if (s.getString("turno").equalsIgnoreCase("manhã")) {
					turmaResultante.setTurnoDaTurma(Turno.MANHÃ);
				}
				if (s.getString("turno").equalsIgnoreCase("tarde")) {
					turmaResultante.setTurnoDaTurma(Turno.TARDE);
				}
				if (s.getString("turno").equalsIgnoreCase("noite")) {
					turmaResultante.setTurnoDaTurma(Turno.NOITE);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException();

		} 	
		finally {

			try {

				conexao.desconectar();
			} catch (ConexaoException e){

				throw new ConexaoException();
			}

		}
		return turmaResultante;
	}

	@Override
	public void alterarTurma(Turma turmaAtual) throws SQLException, ConexaoException {
		// TODO Auto-generated method stub
		Connection c;
		
		try {
			pst = conexao.conectar().prepareStatement(SQLUpdate);
			pst.setString(1, turmaAtual.getNomeDaTurma());
			pst.setString(2, turmaAtual.getTurnoDaTurma().name());
			pst.setString(3, turmaAtual.getPeriodoAtual());
			pst.setInt(4, turmaAtual.getId());
			pst.executeUpdate();
		} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			throw new ConexaoException();
		}

		try {
			conexao.desconectar();
		} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			throw new ConexaoException();
		} 
	}

	@Override
	public void matricularAluno(Aluno aluno,Turma turma) throws ConexaoException, SQLException {
		// TODO Auto-generated method stub	

		try {
			try {
				pst = conexao.conectar().prepareStatement(SQLInsertAluno);

			} catch (ConexaoException e) {
				// TODO Auto-generated catch block
				throw new ConexaoException();
			}
			
			pst.setInt(1, turma.getId());
			pst.setString(2, aluno.getMatricula());;
			pst.executeUpdate();

		}  catch (SQLException e) {

			throw new SQLException();
		}
			
		//turma.setAlunosDaTurma(aluno);
		
		finally {

			try {
				conexao.desconectar();
			} catch (ConexaoException e) {
				// TODO Auto-generated catch block
				throw new ConexaoException();
			}


		}

	}

	@Override
	public void removerAluno(Aluno aluno, Turma turma) throws SQLException, ConexaoException {
		// TODO Auto-generated method stub

		try {
			try {
				pst = conexao.conectar().prepareStatement(SQLDeleteAluno);

			} catch (ConexaoException e) {
				// TODO Auto-generated catch block
				throw new ConexaoException();
			}

			pst.setString(1, null);
			pst.setString(2, aluno.getMatricula());;
			pst.executeUpdate();

		}  catch (SQLException e) {

			throw new SQLException();
		}

		finally {

			try {
				conexao.desconectar();
			} catch (ConexaoException e) {
				// TODO Auto-generated catch block
				throw new ConexaoException();
			}


		}

	}

	public List<HashMap> listarAlunosDaturma (Turma turma) throws SQLException, ConexaoException {
		
		//String SQLAux; 
		
		
		List<HashMap> alunos = new ArrayList();
		Aluno aluno = new Aluno();
		
		
		pst = conexao.conectar().prepareStatement(SQLSelectAllALuno);
		//pst.setInt(1, turma.getIdTurma());
		//System.out.println("dada");
		ResultSet s = pst.executeQuery();
		
		while (s.next()) {
	
		HashMap<Integer, String> mapa = new HashMap();
		
		mapa.put(1, s.getString("nome"));
		mapa.put(2, s.getString("matricula"));
		mapa.put(3, s.getString("CPF"));
		mapa.put(4, s.getString(("Sexo")));
		
		alunos.add(mapa);
		}
		
		return alunos;
		
	}
	

}
