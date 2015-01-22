package sistemaAcademico.daoJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import sistemAcademico.exceptions.ProfessorInexistenteException;
import sistemaAcademico.classesBasicas.Professor;
import sistemaAcademico.conexao.Conexao;
import sistemaAcademico.conexao.ConexaoInt;
import sistemaAcademico.enuns.Titulo;
import sistemaAcademico.exceptions.ConexaoException;



public class DaoProfessorJDBC implements DaoProfessorIntJDBC {
	
		
	 ArrayList< Professor> listaprof = new ArrayList<Professor>();
	
	
	@Override
	public void cadastrarProfessor(Professor professor){
		 ConexaoInt conexao = new Conexao();
		String sql="insert into professor(MatriculaProfessor,Admissao,Departamento,Instituicao,Titulo) values(?,?,?,?,?);";
		
		//Comando para poder set a data de Admissão
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
		}catch (ConexaoException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void alterar(Professor professor) {
		 ConexaoInt conexao = new Conexao();
		String sql="update Professor  set Departamento=? , set Instituicao=?,set Titulo= ? where MatriculaProfessor = ?";
		
		//Comando para poder set a data de Admissão
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
		}catch (ConexaoException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void remover(Professor professor) {
		 ConexaoInt conexao = new Conexao();
		String sql="DELETE FROM Professor WHERE MatriculaProfessor=?";
		
		
		try {
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			
			pst.setString(1,professor.getMatricula());
			
		    pst.executeUpdate();
		    System.out.println("Removido com sucesso");
			conexao.desconectar();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch (ConexaoException e) {
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
                p.setMatricula(rs.getString("MatriculaProfessor"));               
                p.setAdmissao(new java.util.Date (rs.getDate("Admissao").getTime()));
                p.setDepartamento(rs.getString("Departamento"));
                p.setInstituicao(rs.getString("Instituicao"));
                //Para pode listar os Titulos. realizando uma veirificação
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
		}catch (ConexaoException e) {
			System.out.println(e.getMessage());
		}
	
	}

	@Override
	public Professor pesquisarprofessor(String matricula)throws ProfessorInexistenteException {
		    
		ConexaoInt conexao = new Conexao();
			ArrayList<Professor> retorno = new ArrayList<Professor>();
			 String sql = "select * from Professor where MatriculaProfessor = ?";

			
			try {
				PreparedStatement pst = conexao.conectar().prepareStatement(sql);
				
				ResultSet rs = pst.executeQuery();
	            while (rs.next()) {
	                Professor p = new Professor();              			
	                p.setMatricula(rs.getString("MatriculaProfessor"));               
	                p.setAdmissao(new java.util.Date (rs.getDate("Admissao").getTime()));
	                p.setDepartamento(rs.getString("Departamento"));
	                p.setInstituicao(rs.getString("Instituicao"));
	                //Para pode listar os Titulos. realizando uma veirificação
	                for (Titulo tituloAux : Titulo.values()) {
	                	
	                	if (tituloAux.getcodigo() == rs.getInt("titulo")) {
							
	                		p.setTitulo(tituloAux.getName((rs.getInt("titulo"))));
						}
	                	
						
					}
	                retorno.add(p);
	            }
							
				
				
				conexao.desconectar();
				
			
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}catch (ConexaoException e) {
				System.out.println(e.getMessage());
			}
			
	
	}
	
	
	

}
