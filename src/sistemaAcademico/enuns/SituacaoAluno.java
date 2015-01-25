package sistemaAcademico.enuns;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

public enum SituacaoAluno {
	
	ANDAMENTO(1),
	CONCLUIDO(2),
	TRANCADO(3);
	
	private int valor;
	
	SituacaoAluno(int valorOp){
		valor = valorOp;
	}
	
	public int getValor(){
		return valor;
	}
	
	public SituacaoAluno setSituacao(int i){
		SituacaoAluno situacao = null;
		switch (i) {
		case 1:
			return situacao.ANDAMENTO;
		case 2:
			return situacao.CONCLUIDO;
		case 3:
			return situacao.TRANCADO;
		}
		return situacao;
	}

}
