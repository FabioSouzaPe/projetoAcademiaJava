package sistemaAcademico.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import sistemAcademico.exceptions.ConexaoException;

public class Conexao implements ConexaoInt{

	private String url;
    private String user;
    private String pass;
    private Connection c;
    
    public Conexao(){
    	url = "jdbc:mysql://localhost:3306/geekstoredb";
    	user = "root";
    	pass = "root";
    }
	
	@Override
	public Connection conectar() throws ConexaoException {
		try{
	        Class.forName("com.mysql.jdbc.Driver");
	            c = DriverManager.getConnection(url,user,pass);
	        }catch(ClassNotFoundException e){
	           throw new ConexaoException();
	        }catch(SQLException e){
	            throw new ConexaoException();
	        }
	   return c;
	}

	@Override
	public void desconectar() throws ConexaoException {
		try{
			c.close();
        } catch (SQLException ex) {
            throw new ConexaoException();
        } catch (NullPointerException ex) {
            throw new ConexaoException();
        }
         c = null;
	}

}
