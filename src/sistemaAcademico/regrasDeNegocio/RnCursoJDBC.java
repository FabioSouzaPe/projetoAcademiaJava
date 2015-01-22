package sistemaAcademico.regrasDeNegocio;





import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sistemaAcademico.exceptions.CursoExistenteException;
import sistemaAcademico.exceptions.CursoInexistenteException;
import sistemaAcademico.classesBasicas.Curso;
import sistemaAcademico.daoJDBC.DaoConexaoIntJDBC;
import sistemaAcademico.daoJDBC.DaoConexaoJDBC;
import sistemaAcademico.daoJDBC.DaoCursoJDBC;
import sistemaAcademico.daoJDBC.DaoCursoJDBCInt;

public class RnCursoJDBC {
	
	DaoCursoJDBCInt dao = new DaoCursoJDBC();
		
	
	
	public boolean verificacaoCadastrarCurso(Curso curso)throws CursoExistenteException, ClassNotFoundException, SQLException{
		boolean sucesso=false;
		//String sql= "SELECT * FROM CURSO WHERE NOME='"+curso.getNome()+"'";
		//ResultSet rs=dao.consultar(sql);
		
		//if(rs.next()==false){
			dao.cadastrar(curso);
			sucesso=true;
			DaoConexaoIntJDBC daoCon = new DaoConexaoJDBC();
			daoCon.conectar();
		//}
		
		
		return sucesso;
	}
	
	public boolean verificacaoExcluirCurso(int id)throws CursoInexistenteException, ClassNotFoundException, SQLException {
		
		boolean sucesso=false;
		
		if(dao.excluir(id)){
			sucesso=true;
		}
		
		return sucesso;
		
	}
	
	
	public boolean verificacaoAlterarCurso(int id, Curso curso)throws CursoInexistenteException, ClassNotFoundException, SQLException {
		
		boolean sucesso=false;
		String sql= "SELECT * FROM CURSO WHERE NOME='"+curso.getNome()+"'";
		ResultSet rs=dao.consultar(sql);
		
		if(rs.next()==false){
			if(dao.alterar(id, curso)){
				sucesso=true;
			}
		}else{
			//esse nome ja foi cadastrado
		}
		
		
		return sucesso;
	}
	
	public ArrayList<Curso> listar() throws ClassNotFoundException, SQLException{
		
		ArrayList<Curso> cursoList =new ArrayList<Curso>();
		
		String sql= "SELECT * FROM CURSO ";
		ResultSet rs=dao.consultar(sql);
		
		while(rs.next()){
			    Curso c= new Curso();
				c.setNome(rs.getString("NOME"));
				c.setId(rs.getInt("IDCURSO"));
				c.setData( new java.util.Date (rs.getDate("DATA").getTime()));
				cursoList.add(c);
		}
		DaoConexaoIntJDBC daoCon = new DaoConexaoJDBC();
		daoCon.conectar();
		
		return cursoList;
	}
	
}
