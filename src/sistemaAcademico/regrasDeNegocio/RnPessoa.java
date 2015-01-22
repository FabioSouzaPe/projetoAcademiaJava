package sistemaAcademico.regrasDeNegocio;

import java.sql.SQLException;
import java.util.List;

import sistemAcademico.exceptions.PessoaInexistenteException;
import sistemaAcademico.classesBasicas.Endereco;
import sistemaAcademico.classesBasicas.Fone;
import sistemaAcademico.classesBasicas.Pessoa;
import sistemaAcademico.dao.DaoPessoa;
import sistemaAcademico.dao.DaoPessoaInt;
import sistemaAcademico.daoJDBC.DaoPessoaIntJDBC;
import sistemaAcademico.daoJDBC.DaoPessoaJDBC;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class RnPessoa {
	
	static DaoPessoaInt daoPessoa = new DaoPessoa();
	static DaoPessoaIntJDBC daoPessoaJDBC = new DaoPessoaJDBC();

	public RnPessoa() {}

	// Metodo para validar o CPF
	public static boolean cpfValido(String strCpf) {
		int d1, d2;
		int digito1, digito2, resto;
		int digitoCPF;
		String nDigResult;

		d1 = d2 = 0;
		digito1 = digito2 = resto = 0;

		for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
			digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount))
					.intValue();

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

		String nDigVerific = strCpf.substring(strCpf.length() - 2,
				strCpf.length());

		nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

		return nDigVerific.equals(nDigResult);
	}

	public static boolean sePessoaExiste(String strCpf) throws ClassNotFoundException, SQLException {
		
		return daoPessoaJDBC.verificaSeCadastrado(strCpf);
	}
	
	public static int adicionaPessoa(Pessoa pessoa) throws SQLException, ClassNotFoundException, MySQLIntegrityConstraintViolationException{
		int id = 0;
		
		//Verifica se a pessoa já existe no banco de dados, se não exister, invoca o método de adicionar pessoa;
		if (!sePessoaExiste(pessoa.getCpf())){
			id = daoPessoaJDBC.addPessoa(pessoa);
		} 
		return id;
	}

	public static List<Pessoa> consultarPessoas() throws SQLException, ClassNotFoundException {
		
		//Retorna a lista de pessoas cadastradas
		return daoPessoaJDBC.getListaPessoas();
	}
	
	public static Pessoa pesquisarPessoa(String cpf) throws ClassNotFoundException, SQLException {

		Pessoa pessoaAchada = null;

		pessoaAchada = daoPessoaJDBC.buscaPorCpf(cpf);
		if (pessoaAchada.getId() != 0){
			return pessoaAchada;
		} else {
			throw new PessoaInexistenteException();
		}
	}

	public static void alterarPessoa(Pessoa p, Endereco en) throws ClassNotFoundException, SQLException {
		daoPessoaJDBC.alterarEnderecoPessoa(p, en);
	}
	public static void alterarPessoa(Pessoa p, Fone f) throws ClassNotFoundException, SQLException {
		daoPessoaJDBC.alterarFonePessoa(p, f);
	}
}
