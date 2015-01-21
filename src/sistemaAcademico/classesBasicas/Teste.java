package sistemaAcademico.classesBasicas;

import java.util.Date;

import sistemAcademico.daoJdbc.DaoJdbcAluno;

public class Teste {
	
	public static void main(String[] args){
		DaoJdbcAluno dao = new DaoJdbcAluno();
		Aluno a = new Aluno();
		a.setMatricula("0123");
		Date data = new Date();
		a.setData(data);
		
		dao.inserir(a, 0);
	}

}
