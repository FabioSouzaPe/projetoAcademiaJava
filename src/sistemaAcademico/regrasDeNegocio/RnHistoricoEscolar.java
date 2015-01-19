package sistemaAcademico.regrasDeNegocio;

import sistemaAcademico.classesBasicas.Aluno;
import sistemaAcademico.classesBasicas.HistoricoEscolar;
import sistemaAcademico.dao.DaoHistoricoEscolarInt;
import sistemaAcademico.dao.DaoPublicacao;

public class RnHistoricoEscolar {
	
	public DaoHistoricoEscolarInt dao;
	
	public void cadastrar(HistoricoEscolar historico){
		if(pesquisar(historico.getAluno()) == null){
			dao.inserir(historico);
		}else{
			System.out.print("Historico Existente");
		}
	}
	
	public HistoricoEscolar pesquisar(Aluno aluno){
		return dao.pequisar(aluno);
	}

}
