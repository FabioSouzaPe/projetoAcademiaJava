package sistemaAcademico.classesBasicas;

import java.util.Date;

import sistemAcademico.daoJdbc.DaoAlunoJdbc;

public class Teste {
	
	public static void main(String[] args){
		DaoAlunoJdbc dao = new DaoAlunoJdbc();
		Aluno a = new Aluno();
		a.setMatricula("0123");
		Date data = new Date();
		a.setData(data);
		
		dao.inserir(a, 0);
	}

}
