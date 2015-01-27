package sistemaAcademico.regrasDeNegocio;




import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sistemaAcademico.classesBasicas.Curso;
import sistemaAcademico.conexao.Conexao;
import sistemaAcademico.conexao.ConexaoInt;
import sistemaAcademico.daoJDBC.DaoCursoJDBC;
import sistemaAcademico.daoJDBC.DaoCursoJDBCInt;
import sistemaAcademico.exceptions.ConexaoException;

public class RnCursoJDBC {
	
	DaoCursoJDBCInt dao = new DaoCursoJDBC();
		
	
	
	public boolean verificacaoCadastrarCurso(Curso curso)throws  ClassNotFoundException, SQLException{
		ConexaoInt conexao = new Conexao();
		boolean sucesso=false;
		try{
		
		//String sql= "SELECT * FROM CURSO WHERE NOME='"+curso.getNome()+"'";
		//ResultSet rs=dao.consultar(sql);
		
		//if(rs.next()==false){
			dao.cadastrar(curso);
			sucesso=true;
			conexao.desconectar();
		//}
		}catch (ConexaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return sucesso;
	}
	
	public boolean verificacaoExcluirCurso(int id)throws  ClassNotFoundException, SQLException, ConexaoException {
		
		boolean sucesso=false;
		
		if(dao.excluir(id)){
			sucesso=true;
		}
		
		return sucesso;
		
	}
	
	
	public boolean verificacaoAlterarCurso(int id, Curso curso)throws  ClassNotFoundException, SQLException {
		
		boolean sucesso=false;
		try{
		String sql= "SELECT * FROM CURSO WHERE NOME='"+curso.getNome()+"'";
		ResultSet rs=dao.consultar(sql);
		
		if(rs.next()==false){
			if(dao.alterar(id, curso)){
				sucesso=true;
			}
		}else{
			//esse nome ja foi cadastrado
		}}catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return sucesso;
	}
	
	public ArrayList<Curso> listar() throws ClassNotFoundException, SQLException{
		Conexao conexao = new Conexao();
		ArrayList<Curso> cursoList =new ArrayList<Curso>();
		
		String sql= "SELECT * FROM CURSO ";
		
		try{
			ResultSet rs=dao.consultar(sql);
		while(rs.next()){
			    Curso c= new Curso();
				c.setNome(rs.getString("NOME"));
				c.setId(rs.getInt("ID"));
				c.setData( new java.util.Date (rs.getDate("DATA").getTime()));
				cursoList.add(c);
		}
		
		}catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cursoList;
	}
	
}
