package sistemaAcademico.daoJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.Disciplina;
import sistemaAcademico.classesBasicas.HistoricoEscolar;
import sistemaAcademico.classesBasicas.Pessoa;
import sistemaAcademico.conexao.Conexao;
import sistemaAcademico.conexao.ConexaoInt;
import sistemaAcademico.enuns.SituacaoAluno;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.ErroSQLException;
import sistemaAcademico.exceptions.HistoricoInexistenteException;

public class DaoHistoricoEscolarJDBC implements DaoHistoricoEscolarJDBCInt {
	
	private ConexaoInt conexao = new Conexao();
	
	public int inserir(HistoricoEscolar historico) throws ConexaoException, ErroSQLException{
		String sql = "INSERT INTO historicoescolar (observacoes, coeficientederedimento, situacao) VALUES (?,?,?)";
		int id = 0;
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, historico.getObs());
			pst.setDouble(2, historico.getConficienteRedimento());
			pst.setInt(3, historico.getSituacao().getValor());
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
			return id;
		}catch(SQLException e){
			throw new ErroSQLException();
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException e){
				throw new ConexaoException();
			}
		}
	}
	
	public void remover(HistoricoEscolar historico) throws ConexaoException, ErroSQLException{
		String sql = "DELETE FROM HistoricoEscolar WHERE IdHistorico=?";
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setInt(1, historico.getId());
			pst.executeUpdate();
		}catch(SQLException e){
			throw new ErroSQLException();
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException e){
				throw new ConexaoException();
			}
		}
	}
	
	public void alterar(HistoricoEscolar historico) throws ConexaoException, ErroSQLException{
		String sql = "UPDATE HistoricoEscolar set Situacao=?, Observacoes=?, CoeficienteDeRendimento=? where IdHistorico=?";
		
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setInt(1, historico.getSituacao().getValor());
			pst.setString(2, historico.getObs());
			pst.setDouble(3, historico.getConficienteRedimento());
			pst.setInt(4, historico.getId());
		}catch(SQLException e){
			throw new ErroSQLException();
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException e){
				throw new ConexaoException();
			}
		}
	}
	
	public HistoricoEscolar pesquisar(String matricula) throws ConexaoException, ErroSQLException, HistoricoInexistenteException{
		HistoricoEscolar historico;
		Aluno aluno;
		Pessoa pessoa;
		Disciplina disciplina;
		String sql = "select h.*, a.matricula, p.nome ,d.nome, d.CargaHoraria from historicoescolar h "
					+ "inner join aluno a on h.idhistoricoescolar = a.idHistorico "+
				     " inner join pessoa p on a.idpessoa = p.idpessoa "+
					 " inner join turma t on a.idTurma = t.idTurma "+
				     " inner join disciplina d on t.IdDisciplina = d.IdDisciplina " +
					 "WHERE a.matricula = ?";
		try{
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1, matricula);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				aluno = new Aluno();
				pessoa = new Pessoa();
				historico = new HistoricoEscolar();
				disciplina = new Disciplina();
				aluno.setMatricula(rs.getString("a.Matricula"));
				pessoa.setNome(rs.getString("p.Nome"));
				historico.setObs(rs.getString("h.Observacoes"));
				historico.setId(rs.getInt("h.IdHistoricoEscolar"));
				//historico.setData(rs.getDate("h.data"));
				for(SituacaoAluno situacao : SituacaoAluno.values()){
					if(situacao.getValor() == rs.getInt("h.Situacao")){
						historico.setSituacao(situacao.setSituacao(rs.getInt("Situacao")));
					}
				}
				historico.setConficienteRedimento(rs.getDouble("h.NotaAluno"));
				disciplina.setNome(rs.getString("d.Nome"));
				disciplina.setCargaHoraria(rs.getInt("d.CargaHoraria"));
				aluno.setMatricula(rs.getString("Matricula"));
				aluno.setPessoa(pessoa);
				historico.setDisciplina(disciplina);
				historico.setAluno(aluno);
				return historico;
			}
			throw new HistoricoInexistenteException();
		}catch(SQLException e){
			throw new ErroSQLException();
		}finally{
			try{
				conexao.desconectar();
			}catch(ConexaoException e){
				throw new ConexaoException();
			}
		}
	}

}
