package sistemaAcademico.classesBasicas;

public class Endereco {
	private int id;
	private String cep;
	private String logradouro;
	private String bairro;
	private String numero;
	private String cidade;
	private String uf;
	
	public Endereco(){}
	
	public Endereco(int id, String cep, String logradouro, String bairro, String numero,
			String cidade, String uf) {
		super();
		this.id = id;
		this.setCep(cep);
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.numero = numero;
		this.cidade = cidade;
		this.uf = uf;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
}
