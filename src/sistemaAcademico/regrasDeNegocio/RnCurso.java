package sistemaAcademico.regrasDeNegocio;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.CursoExistenteException;
import sistemaAcademico.exceptions.CursoInexistenteException;
import sistemaAcademico.classesBasicas.Curso;
import sistemaAcademico.daoJDBC.DaoGenerico;
import sistemaAcademico.daoJDBC.IDaoGenerico;

public class RnCurso {
	
	IDaoGenerico dao = new DaoGenerico();
		
	
	public boolean montarScriptCadastrarCurso(Curso curso)throws CursoExistenteException, ClassNotFoundException, SQLException, ConexaoException{
		
		boolean sucesso=false;
		
		if(curso!=null){
			java.util.Date dataUtil = curso.getData(); 
			java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
			
			String insert=("INSERT INTO CURSO ( NOME,DATA) VALUES ('"+curso.getNome()+"','"+dataSql+"')") ;
			
			/*
			 * não foi necessario uma verificação se o nome do curso já está cadastrado ,
			 *  pois o atributo NOME estar como UNIQUE no Banco.
			 */
			sucesso=dao.dml(insert);
		}
		
		return sucesso;
	}
	
	public boolean montarScriptExcluirCurso(Curso curso)throws CursoInexistenteException, ClassNotFoundException, SQLException, ConexaoException {
		
		boolean sucesso=false;
		
		if(curso!=null){
			String delete="DELETE FROM CURSO WHERE IDCURSO="+curso.getId();
			sucesso=dao.dml(delete);	
		}
		
		return sucesso;
	}
	
	
	public boolean montarScriptAlterarCurso( Curso curso)throws CursoInexistenteException, ClassNotFoundException, SQLException, ConexaoException {
		
		boolean sucesso=false;
		String select= "SELECT * FROM CURSO WHERE NOME='"+curso.getNome()+"'";
		ResultSet rs=dao.dql(select);
		
		if(rs.next()==false){
			String update="UPDATE  CURSO SET NOME='"+curso.getNome()+"' WHERE IDCURSO="+curso.getId();
			sucesso=dao.dml(update);
		}
		
		return sucesso;
	}
	
	public ArrayList<Curso> montarScriptListarCursos() throws ClassNotFoundException, SQLException, ConexaoException{
		
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
		DaoGenerico.daoConDQL.desconectar();
		
		return cursoList;
	}
	
}
