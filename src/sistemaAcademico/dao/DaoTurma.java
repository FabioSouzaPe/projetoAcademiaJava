package sistemaAcademico.dao;

import java.util.ArrayList;
import java.util.List;

import sistemaAcademico.classesBasicas.*;
import sistemaAcademico.enuns.*;
public class DaoTurma implements DaoTurmaInt{

	
	public static List <Turma> minhasTurmas = new ArrayList <Turma>();
	
	public void cadastrarTurma (List <Aluno> a,String nome, Professor p, List <Disciplina> d, String periodo, Turno t) {
		
		Turma novaTurma = new Turma();
		//Falta exceção
			
		novaTurma.setAlunosDaTurma(a);
		//novaTurma.setDisciplinasDaTurma(d);
		novaTurma.setNomeDaTurma(nome);
		novaTurma.setProfessorDaTurma(p);
		novaTurma.setTurnoDaTurma(t.MANHÃ);
		minhasTurmas.add(novaTurma);
	}
	
	public void removerTurma (Turma turmaRemovida){
		
			minhasTurmas.remove(turmaRemovida);		
	}
		
	public Turma consultarTurma (String nome) {
		
		int j =0;
		boolean turmaExiste = false;
		while (minhasTurmas.get(j) != null) {
			
			if (minhasTurmas.get(j).getNomeDaTurma().equalsIgnoreCase(nome)) {
				
				turmaExiste = true;
				return minhasTurmas.get(j);
			}
			j++;
		}
		return null;
	}
	
	public List <Turma> consultarTurmas() {
		
		return minhasTurmas;
		
	}

	public void alterarTurma(Turma turmaAtual, String novoNome, Professor novoProfessor, Turno novoTurno, String novoPeriodo){
		
		if (novoNome != null) {
			
			turmaAtual.setNomeDaTurma(novoNome);
			
		}
		
		if (novoProfessor != null) {
			
			turmaAtual.setProfessorDaTurma(novoProfessor);
		}
	
		if (novoPeriodo != null) {
			
			turmaAtual.setPeriodoAtual(novoPeriodo);
			
		}
	
	}
	

	
}
