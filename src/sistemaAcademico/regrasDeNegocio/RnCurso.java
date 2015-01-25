package sistemaAcademico.regrasDeNegocio;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.CursoException;
import sistemaAcademico.classesBasicas.Curso;
import sistemaAcademico.daoJDBC.DaoGenerico;
import sistemaAcademico.daoJDBC.IDaoGenerico;

public class RnCurso {
	
	IDaoGenerico dao = new DaoGenerico();
		
	
	public boolean montarScriptCadastrarCurso(Curso curso)throws CursoException, ClassNotFoundException, ConexaoException, SQLException{
		
		boolean sucesso=false;
		
		//verfica de o obje crso está vazio
		if(curso!=null){
			java.util.Date dataUtil = curso.getData(); 
			java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
			
			String insert=("INSERT INTO CURSO ( NOME,DATA) VALUES ('"+curso.getNome()+"','"+dataSql+"')") ;
			
			/*
			 * não foi necessario uma verificação se o nome do curso já está cadastrado ,
			 *  pois o atributo NOME está como UNIQUE no Banco.
			 */
			sucesso=dao.dml(insert);
			if(sucesso==false){
				throw new CursoException("Esse curso já foi cadastrado");
			}
		}else{
			throw new CursoException("Valor nulo passado");
		}
		
		return sucesso;
	}
	
	public boolean montarScriptExcluirCurso(Curso curso)throws CursoException, ClassNotFoundException, ConexaoException, SQLException {
		
		boolean sucesso=false;
		//verfica de o obje crso está vazio
		if(curso!=null){
			String delete="DELETE FROM CURSO WHERE IDCURSO="+curso.getId();
			sucesso=dao.dml(delete);
			if(sucesso==false){
				throw new CursoException("Esse curso não existe");
			}
		}else{
			throw new CursoException("Valor nulo passado");
		}
		
		return sucesso;
	}
	
	
	public boolean montarScriptAlterarCurso( Curso curso)throws CursoException, ClassNotFoundException, ConexaoException, SQLException {
		
		boolean sucesso=false;
		//verfica de o obje crso está vazio
		if(curso!=null){
			/*
			 * não foi necessario uma verificação se já existe o nome do curso passado para ser alterado ,
			 *  pois o atributo NOME está como UNIQUE no Banco.
			 */
			String update="UPDATE  CURSO SET NOME='"+curso.getNome()+"' WHERE IDCURSO="+curso.getId();
			sucesso=dao.dml(update);
			
			if(sucesso==false){
				throw new CursoException("Esse curso não existe");
			}
			
		}else{
			throw new CursoException("Valor nulo passado");
		}
		return sucesso;
	}
	
	public ArrayList<Curso> montarScriptListarCursos() throws ClassNotFoundException, ConexaoException, SQLException{
		
		ArrayList<Curso> cursoList =new ArrayList<Curso>();
		
		String select= "SELECT * FROME CURSO ";
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
	
}
