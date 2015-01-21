package sistemaAcademico.regrasDeNegocio;




import java.sql.ResultSet;
import java.sql.SQLException;

import sistemAcademico.exceptions.CursoExistenteException;
import sistemAcademico.exceptions.CursoInexistenteException;
import sistemaAcademico.classesBasicas.Curso;
import sistemaAcademico.dao.DaoCurso;
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
		if(rs.getRow()==0){
			dao.cadastrar(curso);
			sucesso=true;
		}
		
		
		return sucesso;
	}
	
	/*public boolean verificacaoExcluirCurso(String nomeCurso)throws CursoInexistenteException {
		
		boolean sucesso=false;
		if(dao.consultarTudo().size()==0){
			throw new  CursoInexistenteException();
		}else{
			
			for(int i =0; i<dao.consultarTudo().size();i++){
				if(nomeCurso.equals(dao.consultarTudo().get(i).getNome())){
					dao.excluir(dao.consultarTudo().get(i));
					sucesso=true;
				}
			}
			if(sucesso==false){
				throw new  CursoInexistenteException();
			}
		}
		
		return sucesso;
		
	}
	
	
	public boolean verificacaoAlterarCurso(String nomeOld, String nomeNew)throws CursoInexistenteException {
		
		boolean sucesso=false;
		if(dao.consultarTudo().size()==0){
			throw new  CursoInexistenteException();
		}else{
			
			for(int i =0; i<dao.consultarTudo().size();i++){
				
				if(nomeOld.equals(dao.consultarTudo().get(i).getNome())){
					
					dao.consultarTudo().get(i).setNome(nomeNew);
					dao.alterar(i,dao.consultarTudo().get(i));
					sucesso=true;
				}
			}
			if(sucesso==false){
				throw new  CursoInexistenteException();
			}
		}
		
		return sucesso;
	}
	*/
}
