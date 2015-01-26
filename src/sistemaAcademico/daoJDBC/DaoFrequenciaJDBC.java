package sistemaAcademico.daoJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import sistemaAcademico.classesBasicas.Frequencia;
import sistemaAcademico.classesBasicas.Turma;
import sistemaAcademico.conexao.Conexao;
import sistemaAcademico.conexao.ConexaoInt;
import sistemaAcademico.exceptions.ConexaoException;

public class DaoFrequenciaJDBC implements DaoFrequenciaJDBCInt{

	@Override
	public boolean cadastrarFrequencia(Frequencia frequencia) throws SQLException, ClassNotFoundException {
		
		boolean sucesso=false;
		ConexaoInt conexao = new Conexao();
		java.util.Date dataUtil = frequencia.getData(); 
		java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
		
		try {
		PreparedStatement pStmt =  conexao.conectar().prepareStatement("INSERT INTO CURSO ( DATA, AVALIACAO, TURMA_ID, ALUNO_ID, PRESENCA) VALUES (?,?,?,?,?)") ;
		pStmt.setDate(1,dataSql);
		//pStmt.setString(1,frequencia.getAvaliacao());
		
		int rows = pStmt.executeUpdate();
	
		conexao.desconectar();;
		if(rows==1){
			sucesso=true;
		}} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
