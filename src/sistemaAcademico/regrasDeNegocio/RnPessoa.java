package sistemaAcademico.regrasDeNegocio;

import java.sql.SQLException;
import java.util.List;

import javax.swing.text.html.Option;

import sistemAcademico.exceptions.PessoaInexistenteException;
import sistemaAcademico.classesBasicas.Endereco;
import sistemaAcademico.classesBasicas.Fone;
import sistemaAcademico.classesBasicas.Pessoa;
import sistemaAcademico.dao.DaoPessoa;
import sistemaAcademico.dao.DaoPessoaInt;
import sistemaAcademico.daoJDBC.DaoPessoaIntJDBC;
import sistemaAcademico.daoJDBC.DaoPessoaJDBC;
import sistemaAcademico.exceptions.ConexaoException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.sun.corba.se.impl.io.OptionalDataException;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class RnPessoa {
	
	static DaoPessoaInt daoPessoa = new DaoPessoa();
	static DaoPessoaIntJDBC daoPessoaJDBC = new DaoPessoaJDBC();

	public RnPessoa() {}

	// Metodo para validar o CPF
	public static boolean cpfValido(String cpf) {
		int d1, d2;
		int digito1, digito2, resto;
		int digitoCPF;
		String nDigResult;
		
		cpf = cpf.replaceAll("[^0-9]", "");
		
		if (cpf.length() < 11) {
			return false;
		} 
		
		else {
			d1 = d2 = 0;
			digito1 = digito2 = resto = 0;

			for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
				digitoCPF = Integer.valueOf(
						cpf.substring(nCount - 1, nCount)).intValue();

				d1 = d1 + (11 - nCount) * digitoCPF;

				d2 = d2 + (12 - nCount) * digitoCPF;
			}

			resto = (d1 % 11);

			if (resto < 2) {
				digito1 = 0;
			} else {
				digito1 = 11 - resto;
			}

			d2 += 2 * digito1;

			resto = (d2 % 11);

			if (resto < 2) {
				digito2 = 0;
			} else {
				digito2 = 11 - resto;
			}

			String nDigVerific = cpf.substring(cpf.length() - 2,
					cpf.length());

			nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

			return nDigVerific.equals(nDigResult);
		}
	}
	
	public static int adicionaPessoa(Pessoa pessoa) throws SQLException, ClassNotFoundException, MySQLIntegrityConstraintViolationException, ConexaoException{
		int id = 0;
		
		/*Tenta encontrar a pessoa pelo cpf, se não encontrar
		 * abre uma exception e pula a parte de inserção no banco
		 * 
		*/ 
		
		try {
			boolean b = pesquisarPessoa(pessoa.getCpf()) == null;
		} catch (PessoaInexistenteException e) {
			id = daoPessoaJDBC.addPessoa(pessoa);
		}
		
		/*
		 * Se a pessoa foi cadastrada com sucesso, retorna o ID em que ela foi inserida no banco, para que seja
		 * passada como atribudo para Aluno ou Professor.
		 * 
		 * Se não entrar no método, significa que o ID retornado vai ser 0, que é o valor padrão da variavel
		 * e indica que a pessoa não foi cadastrada.
		*/
		
		return id;
	}

	public static List<Pessoa> consultarPessoas() throws SQLException, ClassNotFoundException, ConexaoException {
		
		/*
		 * Chama o DAO que faz uma lista das pessoas cadastradas, que retorna para esse métido
		 * que por sua vez retorna para quem o chamou
		 */
		return daoPessoaJDBC.getListaPessoas();
	}
	
	public static Pessoa pesquisarPessoa(String cpf) throws ClassNotFoundException, SQLException, ConexaoException {

		Pessoa pessoaAchada = null;

		pessoaAchada = daoPessoaJDBC.buscaPorCpf(cpf);
		if (pessoaAchada.getId() != 0){
			return pessoaAchada;
		} else {
			throw new PessoaInexistenteException();
		}
	}

	public static void alterarPessoa(Endereco endereco) throws ClassNotFoundException, SQLException, ConexaoException {
		daoPessoaJDBC.alterarPessoa(endereco);
	}
	public static void alterarPessoa(Fone fone) throws ClassNotFoundException, SQLException, ConexaoException {
		daoPessoaJDBC.alterarPessoa(fone);
	}
	public static void adicionaFone(Fone fone, int idPessoa) throws ClassNotFoundException, SQLException, ConexaoException{
		daoPessoaJDBC.adicionaFone(fone, idPessoa);
	}

	public static void removerPessoa(int idPessoa, int idEndereco) throws ClassNotFoundException, SQLException, ConexaoException {
		daoPessoaJDBC.removerPessoa(idPessoa, idEndereco);
	}
}
