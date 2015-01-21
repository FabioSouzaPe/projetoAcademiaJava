package sistemaAcademico.dao.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import sistemaAcademico.classesBasicas.Frequencia;
import sistemaAcademico.classesBasicas.Turma;

public class DaoFrequenciaJDBC implements DaoFrequenciaJDBCInt{

	@Override
	public boolean cadastrarFrequencia(Frequencia frequencia) throws SQLException, ClassNotFoundException {
		
		boolean sucesso=false;
		Connection conexao =  DaoConexaoJDBC.abrirConexao();
		java.util.Date dataUtil = frequencia.getData(); 
		java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
		
		
		PreparedStatement pStmt = conexao.prepareStatement("INSERT INTO CURSO ( DATA, AVALIACAO, TURMA_ID, ALUNO_ID, PRESENCA) VALUES (?,?,?,?,?)") ;
		pStmt.setDate(1,dataSql);
		//pStmt.setString(1,frequencia.getAvaliacao());
		
		int rows = pStmt.executeUpdate();
		conexao.commit();
		DaoConexaoJDBC.fecharConexao();
		if(rows==1){
			sucesso=true;
		}
		
	return sucesso;
	}

	@Override
	public boolean alterarFrequencia(Frequencia frequencia) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ListarFrequencia(Turma turma, Date data) {
		// TODO Auto-generated method stub
		return false;
	}

}
