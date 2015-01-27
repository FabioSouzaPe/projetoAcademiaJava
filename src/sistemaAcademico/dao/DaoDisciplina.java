package sistemaAcademico.dao;

import java.util.ArrayList;
import java.util.List;


import sistemaAcademico.classesBasicas.Disciplina;

public class DaoDisciplina implements DaoDisciplinaInt{

	public static List <Disciplina> disciplinas = new ArrayList <Disciplina>();
	
	@Override
	public void cadastrarDisciplina(String novoNome) {
		Disciplina novaDisciplina = new Disciplina();
		
			novaDisciplina.setNome(novoNome);
		
		
	}

	@Override
	public void removerDisciplina(Disciplina disciplinaRemovida) {
		disciplinas.remove(disciplinaRemovida);
		
	}

	@Override
	public Disciplina pesquisarDisciplina(String nome) {
		// TODO Auto-generated method stub
		
		int j =0;
		while (disciplinas.get(j) != null) {
			
			if (disciplinas.get(j).getNome().equalsIgnoreCase(nome)) {
				
				return disciplinas.get(j);
			}
			j++;
		}
		return null;
	}
		
	
	public void alterarDisciplina (String novoNome,Disciplina disciplina) {
		
		
		int j =0;
		boolean existe = false;
		while (disciplinas.get(j) != null) {
			
			if (disciplinas.get(j).getNome().equalsIgnoreCase(novoNome)) {
				
				existe = true;
			}
			j++;
		}
		
		if (existe == false) {
			
			disciplina.setNome(novoNome);
			
		}
		
	}
	
	
	}

	

	

