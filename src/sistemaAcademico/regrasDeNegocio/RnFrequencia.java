package sistemaAcademico.regrasDeNegocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.Frequencia;
import sistemaAcademico.classesBasicas.Turma;
import sistemaAcademico.daoJDBC.DaoGenerico;
import sistemaAcademico.daoJDBC.IDaoGenerico;
import sistemaAcademico.exceptions.ConexaoException;

public class RnFrequencia {
	
	IDaoGenerico dao= new DaoGenerico();
	
	//monta o script que irá cadastrar a frequencia de cadas aluno
	public boolean montarScriptCadastrarFrequencia(Frequencia frequencia) throws ClassNotFoundException, SQLException, ConexaoException{
			
			boolean sucesso=false;
			
			java.util.Date dataUtil = frequencia.getData(); 
			java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
	
			String select="SELECT DATA FROM FREQUENCIA WHERE DATA='"+dataSql+"' AND MATRICULAALUNO="+frequencia.getAluno().getMatricula();
			ResultSet rs=dao.dql(select);
			
			//verifica se ja foi realizada alguma frequencia na data que está vindo no obj Frequencia
			if(rs.next()==false){
					
					boolean presenca= frequencia.getPresenca();
					String avaliacao= frequencia.getAvaliacao();
					String matriulaAluno=frequencia.getAluno().getMatricula();
					int idTurma=frequencia.getTurma().getId();
					java.sql.Date converteDataSql = new java.sql.Date(frequencia.getData().getTime()); 
					
					String insert=
						"INSERT INTO FREQUENCIA ( PRESENCA, AVALIACAO, MATRICULAALUNO, IDTURMA, DATA ) VALUES "
						+ "("+presenca+",'"+avaliacao+"','"+matriulaAluno+"',"+idTurma+",'"+converteDataSql+"')" ;
					
					sucesso=dao.dml(insert);
			}
		
		return sucesso;
	}
	
	//monta o script que irá consultar a frequencia de todos os alunos de uma deteminada turma e retorna um Array de Frequencias
	public ArrayList<Frequencia> montarScriptListarFrequencia(Frequencia frequencia, Date data) throws ClassNotFoundException, SQLException, ConexaoException{
		
		ArrayList<Frequencia> listaDeFrequencia=new ArrayList<Frequencia>();
		
		java.util.Date dataUtil = data; 
		java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
		
		String select="SELECT * FROM FREQUENCIA WHERE DATA='"+dataSql+"' AND IDTURMA="+frequencia.getTurma().getId();
		ResultSet rs=dao.dql(select);
		
		while(rs.next()){
			Frequencia f = new Frequencia();
			Aluno a = new Aluno();
			a.setMatricula(rs.getString("MATRICULAALUNO"));
			Turma t= new Turma();
			t.setId(rs.getInt("IDTURMA"));
			
			f.setAluno(a);
			f.setTurma(t);
			f.setData(new java.util.Date (rs.getDate("DATA").getTime()));
			f.setPresenca(rs.getBoolean("PRESENCA"));
			f.setAvaliacao(rs.getString("AVALIACAO"));
			f.setId(rs.getInt("IDTURMA"));
			listaDeFrequencia.add(f);
		}
		//desconecta a conexao aberta em DaoGenerico no metodo dql
		DaoGenerico.daoConDQL.desconectar();
		return listaDeFrequencia;
	}
	
	
	public boolean montarScriptAtualizarFrequencia(Frequencia f) throws ClassNotFoundException, SQLException, ConexaoException{
		
		boolean sucesso=false;
		String sql;
		java.util.Date dataUtil = f.getData(); 
		java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
		
		//se for atualizar somente a presença
		if(f.getAvaliacao()==null && f.getPresenca()!=null){
			 sql="UPDATE  FREQUENCIA SET  PRESENCA="+f.getPresenca()+" WHERE DATA='"+dataSql+"' AND MATRICULAALUNO='"+f.getAluno().getMatricula()+"'";
		}
		//se for atualizar somente a avaliação
		else if(f.getPresenca()==null && f.getAvaliacao()!=null){
			sql="UPDATE  FREQUENCIA SET  AVALIACAO='"+f.getAvaliacao()+"'  WHERE DATA='"+dataSql+"' AND MATRICULAALUNO='"+f.getAluno().getMatricula()+"'";
		}
		//se for atualizar presença e avaliação
		else{
			sql="UPDATE  FREQUENCIA SET  PRESENCA="+f.getPresenca()+" , AVALIACAO='"+f.getAvaliacao()+"' WHERE DATA='"+dataSql+"' AND MATRICULAALUNO='"+f.getAluno().getMatricula()+"'";
		}
		if(dao.dml(sql)){
			sucesso=true;
		}
		
		return sucesso;
	}
}
