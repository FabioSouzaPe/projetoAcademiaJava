package sistemaAcademico.dao;

import java.util.ArrayList;
import sistemaAcademico.classesBasicas.Pessoa;

public class DaoPessoa {
	
	private ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();

	public ArrayList<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(ArrayList<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}
}
