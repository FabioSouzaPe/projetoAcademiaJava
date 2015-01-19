package sistemaAcademico.dao;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.HistoricoEscolar;

public interface DaoHistoricoEscolarInt {
	
	public void inserir(HistoricoEscolar historico);
	public void remover(HistoricoEscolar historico);
	public void alterar(HistoricoEscolar historico);
	public HistoricoEscolar pequisar(Aluno aluno);

}
