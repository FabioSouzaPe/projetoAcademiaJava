package sistemaAcademico.daoJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import sistemaAcademico.classesBasicas.Pessoa;
import sistemaAcademico.classesBasicas.Professor;
import sistemaAcademico.conexao.Conexao;
import sistemaAcademico.conexao.ConexaoInt;
import sistemaAcademico.enuns.Titulo;
import sistemaAcademico.exceptions.ConexaoException;



public class DaoProfessorJDBC implements DaoProfessorIntJDBC {
	
		
	 ArrayList< Professor> listaprof = new ArrayList<Professor>();
	
	
	@Override
	public void cadastrarProfessor(Professor professor) throws ConexaoException{
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
			throw new ConexaoException();
		}catch (ConexaoException e) {
			throw new ConexaoException();
		}
	}

	@Override
	public void alterar(Professor professor) throws ConexaoException {
		 ConexaoInt conexao = new Conexao();
		String sql="update Professor  set Departamento=?,Instituicao=?,Titulo= ? where MatriculaProfessor = ? ";
		
		//Comando para poder set a data de Admissão
		//java.util.Date dataUtil = professor.getAdmissao(); 
		//java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime()); 
		
		try {
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			
			
			pst.setString(1,professor.getDepartamento());
			pst.setString(2,professor.getInstituicao());
			
			pst.setInt(3,professor.getTitulo().getcodigo());			
			pst.setString(4,professor.getMatricula());	            
						
			pst.executeUpdate();
			System.out.println("Alterado com sucesso");
			conexao.desconectar();
			
		} catch (SQLException e) {
			throw new ConexaoException();
		}catch (ConexaoException e) {
			throw new ConexaoException();
		}
	}

	@Override
	public void remover(Professor professor) throws ConexaoException {
		 ConexaoInt conexao = new Conexao();
		String sql="DELETE FROM Professor WHERE MatriculaProfessor=?";
		
		
		try {
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);
			
			pst.setString(1,professor.getMatricula());
			
		    pst.executeUpdate();
		    System.out.println("Removido com sucesso");
			conexao.desconectar();
			
		} catch (SQLException e) {
			throw new ConexaoException();
		}catch (ConexaoException e) {
			throw new ConexaoException();
		}
		
	}

	public ArrayList<Professor> consultarTudo() throws ConexaoException {
		ConexaoInt conexao = new Conexao();
		
		ArrayList<Professor> retorno = new ArrayList<Professor>();
		String sql="select MatriculaProfessor,nome,Admissao,Departamento,Instituicao,titulo from pessoa"
                +" inner join professor on pessoa.IdPessoa = professor.IdPessoa ";
		Professor professor;
		
		try {
			PreparedStatement pst = conexao.conectar().prepareStatement(sql);

			ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {
                professor = new Professor();              			
                professor.setMatricula(rs.getString("MatriculaProfessor"));               
                professor.setAdmissao(new java.util.Date (rs.getDate("Admissao").getTime()));
                professor.setDepartamento(rs.getString("Departamento"));
                professor.setInstituicao(rs.getString("Instituicao"));
                Pessoa pessoa= new Pessoa();
                pessoa.setNome((rs.getString("Nome")));
                professor.setPessoa(pessoa);
               
                //Para pode listar os Titulos. realizando uma veirificação
                for (Titulo tituloAux : Titulo.values()) {
                	
                	if (tituloAux.getcodigo() == rs.getInt("titulo")) {
						
                		professor.setTitulo(tituloAux.getName((rs.getInt("titulo"))));
					}
                	
					
				}
                retorno.add(professor);
            }
						
			
			
			conexao.desconectar();
		
		
			
		} catch (SQLException e) {
			throw new ConexaoException();
		}catch (ConexaoException e) {
			throw new ConexaoException();
		}
		
		return retorno;
	
	}

	@Override
	public Professor pesquisarprofessor(String matricula)throws ConexaoException {
	 
	    Professor professor  = new Professor(); 
		ConexaoInt conexao = new Conexao();
			//ArrayList<Professor> retorno = new ArrayList<Professor>();
			 String sql = "select * from Professor where MatriculaProfessor = ?";

			 try {
				PreparedStatement pst = conexao.conectar().prepareStatement(sql);
				pst.setString(1, matricula);
				ResultSet rs = pst.executeQuery();
	            while (rs.next()) {
	            	             			
	            	professor.setMatricula(rs.getString("MatriculaProfessor"));               
	            	professor.setAdmissao(new java.util.Date (rs.getDate("Admissao").getTime()));
	            	professor.setDepartamento(rs.getString("Departamento"));
	                professor.setInstituicao(rs.getString("Instituicao"));
	                //Para pode listar os Titulos. realizando uma veirificação
	                for (Titulo tituloAux : Titulo.values()) {
	                	
	                	if (tituloAux.getcodigo() == rs.getInt("titulo")) {
							
	                		professor.setTitulo(tituloAux.getName((rs.getInt("titulo"))));
						}
	                	 				
					}
	           
	            }		
	            
				conexao.desconectar();	
							
			} catch (SQLException e) {
				throw new ConexaoException();
			}catch (ConexaoException e) {
				throw new ConexaoException();
			}
			return professor;
		
			
		 
	}
	
	
	

}
