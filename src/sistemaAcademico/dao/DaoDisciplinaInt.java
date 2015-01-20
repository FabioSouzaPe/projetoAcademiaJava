package sistemaAcademico.dao;

import sistemaAcademico.classesBasicas.Disciplina;


public interface DaoDisciplinaInt {

	
	public abstract void cadastrarDisciplina(String novoNome);
	public abstract void removerDisciplina(Disciplina disciplinaRemovida);
	public abstract void alterarDisciplina (String novoNome, Disciplina d);
	public abstract Disciplina pesquisarDisciplina(String nome);
}
