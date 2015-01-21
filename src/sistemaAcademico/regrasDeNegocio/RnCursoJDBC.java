package sistemaAcademico.regrasDeNegocio;




import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sistemAcademico.exceptions.CursoExistenteException;
import sistemAcademico.exceptions.CursoInexistenteException;
import sistemaAcademico.classesBasicas.Curso;
import sistemaAcademico.classesBasicas.Turma;
import sistemaAcademico.dao.DaoCurso;
import sistemaAcademico.dao.JDBC.DaoConexaoJDBC;
import sistemaAcademico.dao.JDBC.DaoCursoJDBC;
import sistemaAcademico.dao.JDBC.DaoCursoJDBCInt;

public class RnCursoJDBC {
	
	DaoCursoJDBCInt dao = new DaoCursoJDBC();
		
	/*public int quantidadeDeTurmas(String nomeCurso)throws  CursoInexistenteException{
		int qtd=0;
		boolean sucesso=false;
		if(dao.consultarTudo().size()==0){
			throw new  CursoInexistenteException();
		}else{
			for(int i =0; i<dao.consultarTudo().size();i++){
				if(nomeCurso.equals(dao.consultarTudo().get(i).getNome())){
					qtd=dao.consultarTurmas(i, dao.consultarTudo().get(i)).size();
					sucesso=true;
				}
			}
			if(sucesso==false){
				throw new  CursoInexistenteException();
			}
		}
		return qtd;
	}
	*/
	
	public boolean verificacaoCadastrarCurso(Curso curso)throws CursoExistenteException, ClassNotFoundException, SQLException{
		boolean sucesso=false;
		String sql= "SELECT * FROM CURSO WHERE NOME='"+curso.getNome()+"'";
		ResultSet rs=dao.consultar(sql);
		
		if(rs.next()==false){
			dao.cadastrar(curso);
			sucesso=true;
			DaoConexaoJDBC.fecharConexao();
		}
		
		
		return sucesso;
	}
	
	public boolean verificacaoExcluirCurso(String nomeCurso)throws CursoInexistenteException, ClassNotFoundException, SQLException {
		
		boolean sucesso=false;
		
		if(dao.excluir(nomeCurso)){
			sucesso=true;
		}
		
		return sucesso;
		
	}
	
	
	public boolean verificacaoAlterarCurso(String cursoNomeOld, String cursoNomeNew)throws CursoInexistenteException, ClassNotFoundException, SQLException {
		
		boolean sucesso=false;
		
		if(dao.alterar(cursoNomeOld, cursoNomeNew)){
			sucesso=true;
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
				c.setId(rs.getInt("ID"));
				c.setTurma(new ArrayList<Turma>());
				c.setData( new java.util.Date (rs.getDate("DATA").getTime()));
				cursoList.add(c);
		}
		DaoConexaoJDBC.fecharConexao();
		
		return cursoList;
	}
	
}
