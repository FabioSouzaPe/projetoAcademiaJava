package sistemaAcademico.regrasDeNegocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.Frequencia;
import sistemaAcademico.classesBasicas.Turma;
import sistemaAcademico.daoJDBC.DaoFrequenciaJDBC;
import sistemaAcademico.daoJDBC.DaoFrequenciaJDBCInt;

public class RnFrequencia {
	DaoFrequenciaJDBCInt dao= new DaoFrequenciaJDBC();
	
	public boolean verificarCadastrarFrequencia(ArrayList<Frequencia> f) throws ClassNotFoundException, SQLException{
		boolean sucesso=false;
		
		if(f.size()>=1){
			
			java.util.Date dataUtil = f.get(0).getData(); 
			java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
			
			String select="SELECT DATA FROM FREQUENCIA WHERE DATA='"+dataSql+"'";
			ResultSet rs=dao.consultar(select);
			
			if(rs.next()==false){//verifica se ja foi realizada a frequencia na data que está vindo no obj Frequencia
				for(int i=0; i< f.size();i++){
					sucesso=dao.cadastrarFrequencia(f.get(i));
				}
			}
		}else{
			System.out.println("nunhuma freqencia passada");
		}
		
		return sucesso;
	}
	
	public ArrayList<Frequencia> verificarListarFrequencia(Date d) throws ClassNotFoundException, SQLException{
		
		ArrayList<Frequencia> listaDeFrequencia=new ArrayList<Frequencia>();
		
		
		java.util.Date dataUtil = d; 
		java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
		
		String select="SELECT * FROM FREQUENCIA WHERE DATA='"+dataSql+"'";
		ResultSet rs=dao.consultar(select);
		
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
		return listaDeFrequencia;
	}
	
	
	public boolean verificarAtualizarFrequencia(Frequencia f) throws ClassNotFoundException, SQLException{
		
		boolean sucesso=false;
		String sql;
		java.util.Date dataUtil = f.getData(); 
		java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
		
		if(f.getAvaliacao()==null && f.getPresenca()!=null){
			 sql="UPDATE  FREQUENCIA SET  PRESENCA="+f.getPresenca()+" WHERE DATA='"+dataSql+"' AND MATRICULAALUNO='"+f.getAluno().getMatricula()+"'";
		}else if(f.getPresenca()==null && f.getAvaliacao()!=null){
			sql="UPDATE  FREQUENCIA SET  AVALIACAO='"+f.getAvaliacao()+"'  WHERE DATA='"+dataSql+"' AND MATRICULAALUNO='"+f.getAluno().getMatricula()+"'";
		}
		else{
			sql="UPDATE  FREQUENCIA SET  PRESENCA="+f.getPresenca()+" , AVALIACAO='"+f.getAvaliacao()+"' WHERE DATA='"+dataSql+"' AND MATRICULAALUNO='"+f.getAluno().getMatricula()+"'";
		}
		if(dao.alterarFrequencia(sql)){
			sucesso=true;
		}
		
		return sucesso;
	}
}
