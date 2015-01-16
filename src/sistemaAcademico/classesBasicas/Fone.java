package sistemaAcademico.classesBasicas;

public class Fone {
	private int id;
	private String ddd;
	private String fone;
	
	public Fone (){}

	public Fone(int id, String ddd, String fone) {
		super();
		this.id = id;
		this.ddd = ddd;
		this.fone = fone;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	
	
}
