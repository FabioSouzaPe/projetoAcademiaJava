package sistemaAcademico.daoJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.Disciplina;
import sistemaAcademico.classesBasicas.HistoricoEscolar;
import sistemaAcademico.conexao.Conexao;
import sistemaAcademico.conexao.ConexaoInt;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.HistoricoInexistenteException;

public class DaoHistoricoEscolar implements DaoHistoricoEscolarInt {
	
	private ConexaoInt conexao = new Conexao();
	
	public void inserir(HistoricoEscolar historico, int chaveDis) throws ConexaoException, SQLException{
		String sql = "INSERT INTO historicoescolar (observacoes, coeficientederedimento, situacao,"
				+ "IdDisciplina) VALUES (?,?,?,?)";
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1, historico.getObs());
			pst.setDouble(2, historico.getConficienteRedimento());
			pst.setInt(3, historico.getSituacao().getValor());
			pst.setInt(4, historico.getDisciplina().getId());
		}catch(ConexaoException e){
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
	
	public void remover(HistoricoEscolar historico) throws ConexaoException, SQLException{
		String sql = "DELET FROM HistoricoEscolar WHERE IdHistorico=?";
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setInt(1, historico.getId());
			pst.executeUpdate();
		}catch(ConexaoException e){
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
	
	public void alterar(HistoricoEscolar historico) throws ConexaoException, SQLException{
		String sql = "UPDATE HistoricoEscolar set Situacao=?, Observacoes=?, CoeficienteDeRendimento=?,"
				+ "IdDisciplina=? where IdHistorico=?";
		
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setInt(1, historico.getSituacao().getValor());
			pst.setString(2, historico.getObs());
			pst.setDouble(3, historico.getConficienteRedimento());
			pst.setInt(4, historico.getDisciplina().getId());
			pst.setInt(5, historico.getId());
		}catch(ConexaoException e){
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
	
	public HistoricoEscolar pesquisar(String matricula) throws ConexaoException, SQLException, HistoricoInexistenteException{
		HistoricoEscolar historico;
		String sql = "SELECT * FROM historicoescolar INNER JOIN Aluno a ON a.Matricula=?";
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1, matricula);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				Aluno aluno = new Aluno();
				historico = new HistoricoEscolar();
				aluno.setMatricula(rs.getString("Matricula"));
				historico.setObs(rs.getString("Observacoes"));
				historico.setId(rs.getInt("IdHistorico"));
				historico.setConficienteRedimento(rs.getDouble("CoeficienteDeRendimento"));
				historico.setAluno(aluno);
				return historico;
			}
			throw new HistoricoInexistenteException();
		}catch(ConexaoException e){
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
