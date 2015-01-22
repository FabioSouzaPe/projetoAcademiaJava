package sistemaAcademico.daoJDBC;

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
		DaoConexaoIntJDBC daoCon = new DaoConexaoJDBC();
		Connection conexao =  daoCon.conectar();
		java.util.Date dataUtil = frequencia.getData(); 
		java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
		
		
		PreparedStatement pStmt = conexao.prepareStatement("INSERT INTO CURSO ( PRESENCA, AVALIACAO, MATRICULAALUNO, IDTURMA, DATA ) VALUES (?,?,?,?,?)") ;
		
		pStmt.setDate(5,dataSql);
		//pStmt.setString(1,frequencia.getAvaliacao());
		
		int rows = pStmt.executeUpdate();
		conexao.commit();
		daoCon.desconectar();
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
