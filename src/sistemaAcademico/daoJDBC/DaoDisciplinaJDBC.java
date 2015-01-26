package sistemaAcademico.daoJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sistemaAcademico.classesBasicas.Disciplina;
import sistemaAcademico.classesBasicas.Turma;
import sistemaAcademico.conexao.Conexao;
import sistemaAcademico.exceptions.ConexaoException;

public class DaoDisciplinaJDBC implements DaoDisciplinaJDBCInt{

	
	private Conexao conexao = new Conexao();
	private PreparedStatement pst;
	private String SQLDelete = "Delete FROM Disciplina WHERE nomeDisciplina = ? ";
	private String SQLInsert = "INSERT INTO Disciplina (CargaHoraria,nome)values (?,?)";
	private String SQLSelectOne = "SELECT * FROM Disciplina where nomeDisciplina = ?";
	private String SQLUpdateNomeDisciplina = "UPDATE Disciplina SET nomeDisciplina = ?";
	private String SQLUpdateCargaHoraria = "Update Disciplina SET CargaHorariaDisciplina = ?";
	private String SQLDeleteDisciplina = "UPDATE Turma set idDisciplina = ?";
	private String SQLInsertDisciplina = "UPDATE Turma set idDisciplina = ?";
	
	@Override
	public void cadastrarDisciplina(Disciplina disciplina) throws SQLException, ConexaoException {
		// TODO Auto-generated method stub
		
		try {
			try {
				pst = conexao.conectar().prepareStatement(SQLInsert);
				//System.out.println(disciplina.getNome());
			} catch (ConexaoException e) {
				// TODO Auto-generated catch block
				throw new ConexaoException();
			}
			
			pst.setInt(1, disciplina.getCargaHoraria());
			pst.setString(2, disciplina.getNome());
			pst.executeUpdate();
			
		} catch (SQLException e) {
		
				throw new SQLException(e);
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
	public void removerDisciplina(Disciplina disciplinaRemovida) throws ConexaoException, SQLException {
		// TODO Auto-generated method stub
		
		try {
			try {
				pst = conexao.conectar().prepareStatement(SQLDelete);
			
			} catch (ConexaoException e) {
				// TODO Auto-generated catch block
				throw new ConexaoException();
			}
			
			pst.setString(1, disciplinaRemovida.getNome());
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
	public void alterarDisciplina(Disciplina d) throws ConexaoException, SQLException {
		
		// TODO Auto-generated method stub
Connection c = null;
		
		try {
			try {
				c = conexao.conectar();
			} catch (ConexaoException e) {
				// TODO Auto-generated catch block
				throw new ConexaoException();
			}
			
			if (d.getNome() != null) {
			c.prepareStatement(SQLUpdateNomeDisciplina);
			pst.setString(1, d.getNome());
			
			}
			
			if (d.getCargaHoraria()!= 0) {
				c.prepareStatement(SQLUpdateCargaHoraria);
				pst.setInt(2, d.getCargaHoraria());
				
			}
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
		
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
	public Disciplina pesquisarDisciplina(String nome) throws SQLException, ConexaoException {
		// TODO Auto-generated method stub
	
		Disciplina resultante = new Disciplina();
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
			resultante.setId(s.getInt("IdDisciplina"));
			resultante.setNome(s.getString("nomeDisciplina"));
			resultante.setCargaHoraria(s.getInt("CargaHoraria"));
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
		return resultante;
		
	}

	@Override
	public void inserDisciplinaEmTurma(Disciplina novaDisciplina,Turma turma) throws ConexaoException, SQLException {
		// TODO Auto-generated method stub
		
			
		pst = conexao.conectar().prepareStatement(SQLInsertDisciplina);
		pst.setInt(1, novaDisciplina.getId());
		pst.executeUpdate();
		conexao.desconectar();
			
	}

	@Override
	public void removerDisciplinaDeTurma(Turma turma) throws SQLException, ConexaoException {
		// TODO Auto-generated method stub
		
	
			pst = conexao.conectar().prepareStatement(SQLDeleteDisciplina);
			pst.setString(1, null);
			pst.executeUpdate();
			
			conexao.desconectar();
			
		}	
		
	}


