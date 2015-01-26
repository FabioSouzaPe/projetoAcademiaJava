package sistemaAcademico.regrasDeNegocio;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.CursoException;
import sistemaAcademico.classesBasicas.Curso;
import sistemaAcademico.classesBasicas.Turma;
import sistemaAcademico.daoJDBC.DaoGenerico;
import sistemaAcademico.daoJDBC.IDaoGenerico;

public class RnCurso {
	
	IDaoGenerico dao = new DaoGenerico();
		
	
	public boolean montarScriptCadastrarCurso(Curso curso)throws CursoException, ClassNotFoundException, ConexaoException, SQLException{
		
		boolean sucesso=false;
		
		//verfica de o obje crso est� vazio
		if(curso!=null){
			java.util.Date dataUtil = curso.getData(); 
			java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
			
			String insert=("INSERT INTO CURSO ( NOME,DATA) VALUES ('"+curso.getNome()+"','"+dataSql+"')") ;
			
			/*
			 * n�o foi necessario uma verifica��o se o nome do curso j� est� cadastrado ,
			 *  pois o atributo NOME est� como UNIQUE no Banco.
			 */
			sucesso=dao.dml(insert);
			if(sucesso==false){
				throw new CursoException("Esse curso j� foi cadastrado");
			}
		}else{
			throw new CursoException("Valor nulo passado");
		}
		
		return sucesso;
	}
	
	public boolean montarScriptExcluirCurso(Curso curso)throws CursoException, ClassNotFoundException, ConexaoException, SQLException {
		
		boolean sucesso=false;
		//verfica de o obje crso est� vazio
		if(curso!=null){
			String delete="DELETE FROM CURSO WHERE IDCURSO="+curso.getId();
			sucesso=dao.dml(delete);
			if(sucesso==false){
				throw new CursoException("Esse curso n�o existe");
			}
		}else{
			throw new CursoException("Valor nulo passado");
		}
		
		return sucesso;
	}
	
	
	public boolean montarScriptAlterarCurso( Curso curso)throws CursoException, ClassNotFoundException, ConexaoException, SQLException {
		
		boolean sucesso=false;
		//verfica de o obje crso est� vazio
		if(curso!=null){
			/*
			 * n�o foi necessario uma verifica��o se j� existe o nome do curso passado para ser alterado ,
			 *  pois o atributo NOME est� como UNIQUE no Banco.
			 */
			String update="UPDATE  CURSO SET NOME='"+curso.getNome()+"' WHERE IDCURSO="+curso.getId();
			sucesso=dao.dml(update);
			
			if(sucesso==false){
				throw new CursoException("Esse curso n�o existe");
			}
			
		}else{
			throw new CursoException("Valor nulo passado");
		}
		return sucesso;
	}
	
	public ArrayList<Curso> montarScriptListarCursos() throws ClassNotFoundException, ConexaoException, SQLException{
		
		ArrayList<Curso> cursoList =new ArrayList<Curso>();
		
		String select= "SELECT * FROM CURSO ";
		ResultSet rs=dao.dql(select);
		
		while(rs.next()){
			    Curso c= new Curso();
				c.setNome(rs.getString("NOME"));
				c.setId(rs.getInt("IDCURSO"));
				c.setData( new java.util.Date (rs.getDate("DATA").getTime()));
				cursoList.add(c);
		}
		//desliga a conexao aberta em DaoGenerico no metodo dql
		DaoGenerico.daoConDQL.desconectar();
		
		return cursoList;
	}
	
	//metodo para listar todas as turmas por curso
	public Curso  montarScriptListarTurmasPorCurso(Curso curso) throws ClassNotFoundException, ConexaoException, SQLException{
		
		ArrayList<Turma> turmaPorCursoList =new ArrayList<Turma>();
		
		String select= "SELECT TURMA.IDTURMA, TURMA.NOME, CURSO.NOME FROM TURMA INNER JOIN CURSO ON TURMA.IDCURSO=CURSO.IDCURSO WHERE TURMA.IDCURSO="+curso.getId();
		ResultSet rs=dao.dql(select);
		Curso cursoRetorno= new Curso();
		String nomeCurso="";
		while(rs.next()){
			    nomeCurso=rs.getString("CURSO.NOME");
			    Turma t= new Turma();
			    t.setNomeDaTurma(rs.getString("TURMA.NOME"));
			    t.setId(rs.getInt("TURMA.IDTURMA"));
			    turmaPorCursoList.add(t);
		}
		cursoRetorno.setTurma(turmaPorCursoList);
		cursoRetorno.setId(curso.getId());
		cursoRetorno.setNome(nomeCurso);
		//desliga a conexao aberta em DaoGenerico no metodo dql
		DaoGenerico.daoConDQL.desconectar();
		
		return cursoRetorno;
	}
	
}
