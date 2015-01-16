package sistemaAcademico.classesBasicas;

import java.util.ArrayList;

public class Pessoa {
	private int id;
	private String nome;
	private String cpf;
	private char sexo;
	private Endereco endereco;
	private ArrayList<Fone> fones;

	public Pessoa(int id, String nome, String cpf, char sexo,
			Endereco endereco, ArrayList<Fone> fones) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.endereco = endereco;
		this.fones = fones;
	}
	
	public Pessoa(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public ArrayList<Fone> getFones() {
		return fones;
	}
	public void setFones(ArrayList<Fone> fones) {
		this.fones = fones;
	}
	
	static public boolean CPF (String strCpf )
	   {
	      int     d1, d2;
	      int     digito1, digito2, resto;
	      int     digitoCPF;
	      String  nDigResult;

	      d1 = d2 = 0;
	      digito1 = digito2 = resto = 0;

	      for (int nCount = 1; nCount < strCpf.length() -1; nCount++)
	      {
	         digitoCPF = Integer.valueOf (strCpf.substring(nCount -1, nCount)).intValue();

	         //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
	         d1 = d1 + ( 11 - nCount ) * digitoCPF;

	         //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
	         d2 = d2 + ( 12 - nCount ) * digitoCPF;
	      };

	      //Primeiro resto da divisão por 11.
	      resto = (d1 % 11);

	      //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
	      if (resto < 2)
	         digito1 = 0;
	      else
	         digito1 = 11 - resto;

	      d2 += 2 * digito1;

	      //Segundo resto da divisão por 11.
	      resto = (d2 % 11);

	      //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
	      if (resto < 2)
	         digito2 = 0;
	      else
	         digito2 = 11 - resto;

	      //Digito verificador do CPF que está sendo validado.
	      String nDigVerific = strCpf.substring (strCpf.length()-2, strCpf.length());

	      //Concatenando o primeiro resto com o segundo.
	      nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

	      //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
	      return nDigVerific.equals(nDigResult);
	   }

	   /* Use este trecho para testar a classe
	   public static void main(String[] args) {
	      System.out.println( CPF("04624193806") );
	   }
	   */
}
