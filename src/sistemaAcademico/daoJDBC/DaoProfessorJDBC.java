package sistemaAcademico.daoJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sistemAcademico.exceptions.ErroConexaoException;
import sistemAcademico.exceptions.ProfessorInexistenteException;
import sistemaAcademico.classesBasicas.Professor;
import sistemaAcademico.conexao.Conexao;
import sistemaAcademico.conexao.ConexaoInt;
import sistemaAcademico.enuns.Titulo;



public class DaoProfessorJDBC implements DaoProfessorIntJDBC {
	
	
	
	 ArrayList< Professor> listaprof = new ArrayList<Professor>();
	
	/*
	public Titulo reconverter(int a){
		
		switch (a) {
		case 1:
			return Titulo.POSGRADUACAO ;
		case 2:
			return Titulo.MESTRADO;
		case 3:
			return Titulo.DOUTORADO;
				
		}
		 
		 return null;
	}
	 
	 
	 public int convertetitulo(Titulo titulo){
		 
		switch (titulo) {
		case POSGRADUACAO:
			return 1;
		case MESTRADO:
			return 2;
		case DOUTORADO:
			return 3;
				
		}
		 
		 return 0;
	 }
	 */
  
	@Override
	public void cadastrarProfessor(Professor professor){
		 ConexaoInt conexao = new Conexao();
		String sql="insert into professor(Matricula,Admissao,Departamento,Instituicao,Titulo) values(?,?,?,?,?);";
		
		//Comando para poder set a data de Admiss�o
				java.util.Date dataUtil = professor.getAdmissao(); 
				java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
		try {
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			pst.setString(1,professor.getMatricula());
			pst.setDate(2,dataSql);
			pst.setString(3,professor.getDepartamento());
			pst.setString(4,professor.getInstituicao());
			pst.setInt(5,professor.getTitulo().getcodigo());
						
			pst.executeUpdate();
			System.out.println("Cadastrado com sucesso");
			conexao.desconectar();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch (ErroConexaoException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void alterar(Professor professor) {
		 ConexaoInt conexao = new Conexao();
		String sql="update Professor  set Departamento=? , set Instituicao=?,set Titulo= ? where Matricula = ?";
		
		//Comando para poder set a data de Admiss�o
		//java.util.Date dataUtil = professor.getAdmissao(); 
		//java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
		
		try {
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			
			
			pst.setString(2,professor.getDepartamento());
			pst.setString(3,professor.getInstituicao());
		    //pst.setInt(4,convertetitulo(professor.getTitulo()));
			pst.setString(4,professor.getTitulo().name());
			pst.setString(5,professor.getMatricula());
						
			pst.executeUpdate();
			
			conexao.desconectar();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch (ErroConexaoException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void remover(Professor professor) {
		 ConexaoInt conexao = new Conexao();
		String sql="DELETE FROM Professor WHERE Matricula=?";
		
		
		try {
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			
			pst.setString(1,professor.getMatricula());
			
		    pst.executeUpdate();
		    System.out.println("Removido com sucesso");
			conexao.desconectar();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch (ErroConexaoException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public ArrayList<Professor> consultarTudo() {
		ConexaoInt conexao = new Conexao();
		
		ArrayList<Professor> retorno = new ArrayList<Professor>();
		String sql="Select * from Professor";

		
		try {
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Professor p = new Professor();              			
                p.setMatricula(rs.getString("Matricula"));               
                p.setAdmissao(new java.util.Date (rs.getDate("Admissao").getTime()));
                p.setDepartamento(rs.getString("Departamento"));
                p.setInstituicao(rs.getString("Instituicao"));
                //Para pode listar os Titulos. realizando uma veirifica��o
                for (Titulo tituloAux : Titulo.values()) {
                	
                	if (tituloAux.getcodigo() == rs.getInt("titulo")) {
						
                		p.setTitulo(tituloAux.getName((rs.getInt("titulo"))));
					}
                	
					
				}
                retorno.add(p);
            }
						
			
			
			conexao.desconectar();
			return retorno;
		
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch (ErroConexaoException e) {
			System.out.println(e.getMessage());
		}
		return listaprof;
	}

	@Override
	public Professor pesquisarprofessor(String matricula)
			throws ProfessorInexistenteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}