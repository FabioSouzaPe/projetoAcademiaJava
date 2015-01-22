package sistemaAcademico.daoJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import sistemaAcademico.classesBasicas.Frequencia;
import sistemaAcademico.classesBasicas.Turma;

public class DaoFrequenciaJDBC implements DaoFrequenciaJDBCInt{

	@Override
	public ResultSet consultar(String select) throws ClassNotFoundException, SQLException {
		
		DaoConexaoIntJDBC daoCon = new DaoConexaoJDBC();
		Connection conexao =  daoCon.conectar();
		PreparedStatement pStmt = conexao.prepareStatement(select) ;
		ResultSet rs = pStmt.executeQuery();
		conexao.commit();
		return rs;
	}
	
	
	@Override
	public boolean cadastrarFrequencia(Frequencia frequencia) throws SQLException, ClassNotFoundException {
		
		boolean sucesso=false;
		DaoConexaoIntJDBC daoCon = new DaoConexaoJDBC();
		Connection conexao =  daoCon.conectar();
		java.util.Date dataUtil = frequencia.getData(); 
		java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
		
		
		PreparedStatement pStmt = conexao.prepareStatement("INSERT INTO FREQUENCIA ( PRESENCA, AVALIACAO, MATRICULAALUNO, IDTURMA, DATA ) VALUES (?,?,?,?,?)") ;
		pStmt.setBoolean(1, frequencia.getPresenca());
		pStmt.setString(2, frequencia.getAvaliacao());
		pStmt.setString(3, frequencia.getAluno().getMatricula());
		pStmt.setInt(4, frequencia.getTurma().getId());
		pStmt.setDate(5,dataSql);
		
		
		int rows = pStmt.executeUpdate();
		conexao.commit();
		daoCon.desconectar();
		if(rows==1){
			sucesso=true;
		}
		
		return sucesso;
	}

	@Override
	public boolean alterarFrequencia(String sql) throws ClassNotFoundException, SQLException {
		boolean sucesso=false;
		DaoConexaoIntJDBC daoCon = new DaoConexaoJDBC();
		Connection conexao =  daoCon.conectar();
		PreparedStatement pStmt = conexao.prepareStatement(sql);
		int rows = pStmt.executeUpdate();
		conexao.commit();
		daoCon.desconectar();
		if(rows==1){
			sucesso=true;
		}
		
		return sucesso;
	}

	@Override
	public boolean ListarFrequencia(Turma turma, Date data) {
		// TODO Auto-generated method stub
		return false;
	}

}
