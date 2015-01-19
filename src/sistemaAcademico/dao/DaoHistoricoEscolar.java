package sistemaAcademico.dao;

import java.util.ArrayList;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.HistoricoEscolar;

public class DaoHistoricoEscolar implements DaoHistoricoEscolarInt{
	
	public ArrayList<HistoricoEscolar> lista = new ArrayList<HistoricoEscolar>();
	
	 public DaoHistoricoEscolar() {
		lista.add(null);
	}

	@Override
	public void inserir(HistoricoEscolar historico) {
		lista.add(historico);
	}

	@Override
	public void remover(HistoricoEscolar historico) {
		for(int i = 0; i<lista.size()-1; i ++ ){
			if(lista.get(i).getId() == historico.getId()){
				lista.remove(i);
			}
		}
	}

	@Override
	public void alterar(HistoricoEscolar historico) {
		for(int i = 0; i<lista.size()-1; i ++ ){
			if(lista.get(i).getId() == historico.getId()){
				lista.add(historico);
			}
		}
	}

	@Override
	public HistoricoEscolar pequisar(Aluno aluno) {
		for(int i = 0; i < lista.size()-1; i++){
			if(lista.get(i).getAluno().getMatricula().equals(aluno.getMatricula())){
				return lista.get(i);
			}
		}
		return null;
	}

}
