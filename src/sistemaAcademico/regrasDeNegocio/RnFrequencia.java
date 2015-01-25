package sistemaAcademico.regrasDeNegocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.Disciplina;
import sistemaAcademico.classesBasicas.Frequencia;
import sistemaAcademico.classesBasicas.Pessoa;
import sistemaAcademico.classesBasicas.Turma;
import sistemaAcademico.daoJDBC.DaoGenerico;
import sistemaAcademico.daoJDBC.IDaoGenerico;
import sistemaAcademico.exceptions.ConexaoException;

public class RnFrequencia {
	
	IDaoGenerico dao= new DaoGenerico();
	
	//monta o script que ir� cadastrar a frequencia de cadas aluno
	public boolean montarScriptCadastrarFrequencia(Frequencia frequencia) throws ClassNotFoundException, SQLException, ConexaoException{
			
			boolean sucesso=false;
			
			java.util.Date dataUtil = frequencia.getData(); 
			java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
	
			String select="SELECT DATA FROM FREQUENCIA WHERE IDDISCIPLINA="+frequencia.getDisciplina().getId()+" AND DATA='"+dataSql+"' AND MATRICULAALUNO="+frequencia.getAluno().getMatricula();
			ResultSet rs=dao.dql(select);
			
			//verifica se ja foi realizada alguma frequencia na data e disciplina que est� vindo no obj Frequencia
			if(rs.next()==false){
					
					boolean presenca= frequencia.getPresenca();
					String avaliacao= frequencia.getAvaliacao();
					String matriulaAluno=frequencia.getAluno().getMatricula();
					int idTurma=frequencia.getTurma().getId();
					int idDisciplina= frequencia.getDisciplina().getId();
					java.sql.Date converteDataSql = new java.sql.Date(frequencia.getData().getTime()); 
					
					String insert=
						"INSERT INTO FREQUENCIA ( PRESENCA, AVALIACAO, MATRICULAALUNO, IDTURMA, DATA, IDDISCIPLINA ) VALUES "
						+ "("+presenca+",'"+avaliacao+"','"+matriulaAluno+"',"+idTurma+",'"+converteDataSql+"',"+idDisciplina+")" ;
					
					sucesso=dao.dml(insert);
			}
		
		return sucesso;
	}
	
	//monta o script que ir� consultar a frequencia de todos os alunos de uma deteminada turma ou disciplina por data e retorna um Array de Frequencias
	public ArrayList<Frequencia> montarScriptListarFrequencia(Frequencia frequencia, Date data) throws ClassNotFoundException, SQLException, ConexaoException{
		
		ArrayList<Frequencia> listaDeFrequencia=new ArrayList<Frequencia>();
	
		java.util.Date dataUtil = data; 
		java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
		
		String select="";
		//verifica se a consuta vai trazer as presen�as por disciplina ou por turma
		if(frequencia.getDisciplina()!=null){
			select="SELECT FREQUENCIA.IDFREQUENCIA,FREQUENCIA.PRESENCA,FREQUENCIA.AVALIACAO,FREQUENCIA.MATRICULAALUNO,PESSOA.NOME,"
			+" FREQUENCIA.IDTURMA,FREQUENCIA.IDDISCIPLINA,DISCIPLINA.NOME,FREQUENCIA.DATA  FROM FREQUENCIA"
			+" INNER JOIN DISCIPLINA"
			+" ON FREQUENCIA.IDDISCIPLINA=DISCIPLINA.IDDISCIPLINA"
			+" INNER JOIN ALUNO"
			+" ON ALUNO.MATRICULA=FREQUENCIA.MATRICULAALUNO"
			+" INNER JOIN PESSOA"
			+" ON ALUNO.IDPESSOA=PESSOA.IDPESSOA "
			+ " WHERE FREQUENCIA.IDDISCIPLINA="+frequencia.getDisciplina().getId()+" AND DATA='"+dataSql+"' AND IDTURMA="+frequencia.getTurma().getId();
			 
		}else{
			select="SELECT FREQUENCIA.IDFREQUENCIA,FREQUENCIA.PRESENCA,FREQUENCIA.AVALIACAO,FREQUENCIA.MATRICULAALUNO,PESSOA.NOME,"
					+" FREQUENCIA.IDTURMA,FREQUENCIA.IDDISCIPLINA,DISCIPLINA.NOME,FREQUENCIA.DATA  FROM FREQUENCIA"
					+" INNER JOIN DISCIPLINA "
					+" ON FREQUENCIA.IDDISCIPLINA=DISCIPLINA.IDDISCIPLINA "
					+" INNER JOIN ALUNO "
					+" ON ALUNO.MATRICULA=FREQUENCIA.MATRICULAALUNO "
					+" INNER JOIN PESSOA "
					+" ON ALUNO.IDPESSOA=PESSOA.IDPESSOA "
					+ " WHERE DATA='"+dataSql+"' AND IDTURMA="+frequencia.getTurma().getId();
		}
		
		ResultSet rs=dao.dql(select);
		
		
		while(rs.next()){
			Frequencia f = new Frequencia();
			Pessoa p=new Pessoa();
			Aluno a = new Aluno();
			Turma t= new Turma();
			Disciplina d= new Disciplina();
			p.setNome(rs.getString(5));//nome pessoa
			a.setMatricula(rs.getString("MATRICULAALUNO"));
			a.setPessoa(p);
			t.setId(rs.getInt("IDTURMA"));
			d.setId(rs.getInt("IDDISCIPLINA"));
			d.setNome(rs.getString(8));//nome disciplina
			f.setDisciplina(d);

			
			f.setAluno(a);
			f.setTurma(t);
			f.setData(new java.util.Date (rs.getDate("DATA").getTime()));
			f.setPresenca(rs.getBoolean("PRESENCA"));
			f.setAvaliacao(rs.getString("AVALIACAO"));
			f.setId(rs.getInt("IDFREQUENCIA"));
			listaDeFrequencia.add(f);
		}
		//desliga a conexao aberta em DaoGenerico no metodo dql
		DaoGenerico.daoConDQL.desconectar();
		return listaDeFrequencia;
	}
	
	
	public boolean montarScriptAtualizarFrequencia(Frequencia frequencia) throws ClassNotFoundException, SQLException, ConexaoException{
		
		boolean sucesso=false;
		String sql;
		java.util.Date dataUtil = frequencia.getData(); 
		java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
		
		//se for atualizar somente a presen�a
		if(frequencia.getAvaliacao()==null && frequencia.getPresenca()!=null){
			 sql="UPDATE  FREQUENCIA SET  PRESENCA="+frequencia.getPresenca()+" WHERE IDDISCIPLINA="+frequencia.getDisciplina().getId()+" AND DATA='"+dataSql+"' AND MATRICULAALUNO='"+frequencia.getAluno().getMatricula()+"'";
		}
		//se for atualizar somente a avalia��o
		else if(frequencia.getPresenca()==null && frequencia.getAvaliacao()!=null){
			sql="UPDATE  FREQUENCIA SET  AVALIACAO='"+frequencia.getAvaliacao()+"'  WHERE IDDISCIPLINA="+frequencia.getDisciplina().getId()+" AND DATA='"+dataSql+"' AND MATRICULAALUNO='"+frequencia.getAluno().getMatricula()+"'";
		}
		//se for atualizar presen�a e avalia��o
		else{
			sql="UPDATE  FREQUENCIA SET  PRESENCA="+frequencia.getPresenca()+" , AVALIACAO='"+frequencia.getAvaliacao()+"' WHERE IDDISCIPLINA="+frequencia.getDisciplina().getId()+" AND DATA='"+dataSql+"' AND MATRICULAALUNO='"+frequencia.getAluno().getMatricula()+"'";
		}
		if(dao.dml(sql)){
			sucesso=true;
		}
		
		return sucesso;
	}
}
