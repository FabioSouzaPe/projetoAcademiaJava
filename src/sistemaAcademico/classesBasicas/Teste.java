package sistemaAcademico.classesBasicas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sistemaAcademico.enuns.Titulo;
import sistemaAcademico.enuns.Turno;
import sistemaAcademico.exceptions.ConexaoException;
import sistemaAcademico.exceptions.CursoCheioException;
import sistemaAcademico.exceptions.TurmaExistenteException;
import sistemaAcademico.regrasDeNegocio.RnDisciplinaJDBC;
import sistemaAcademico.regrasDeNegocio.RnTurmaJDBC;

public class Teste {
	
	public static void main(String[] args){
		
		RnTurmaJDBC rn = new RnTurmaJDBC();
		RnDisciplinaJDBC dt = new RnDisciplinaJDBC();
		List<HashMap> mapa = new ArrayList();
		
		Disciplina d = new Disciplina();
		
		d.setNome("JAVA");
		d.setCargaHoraria(60);
		d.setId(10);
		
		List<Aluno> al = new ArrayList();
		
		Aluno a = new Aluno ();
		a.setMatricula("12");
		
		
		Aluno b = new Aluno ();
		b.setMatricula("123");
		
		al.add(a);
		Professor p = new Professor();
		//p.setAdmissao(admissao);
		p.setDepartamento("Computação");
		p.setInstituicao("UPE");
		p.setProf("Paulo");
		p.setTitulo(Titulo.DOUTORADO);
		p.setMatricula("1");
		
		Curso c = new Curso();
		
		c.setNome("Engenharia");
		c.setId(1);
		Turma t = new Turma() ;
		Turma alterar = new Turma();
		t.setNomeDaTurma("Teste123");
		t.setPeriodoAtual("Quarto");
		t.setTurnoDaTurma(Turno.NOITE);
		t.setProfessorDaTurma(p);
		//t.setDisciplina(d);
		t.setAlunosDaTurma(al);
		t.setCurso(c);
		
		
		alterar.setNomeDaTurma("ADS");
		alterar.setPeriodoAtual("Oitavo");
		alterar.setTurnoDaTurma(Turno.NOITE);
		alterar.setProfessorDaTurma(p);
		alterar.setAlunosDaTurma(al);
		alterar.setCurso(c);
				
		
		try {
			mapa = rn.listarAlunosDaTurma(t);
		} catch (SQLException | ConexaoException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		System.out.println(mapa.size());
			
		int j = 0;
		
		while (j < mapa.size()) {
		
			
		
			for (int i = 1; i < 5; i++) {
			
				System.out.println(mapa.get(j).get(i));
			
			}
			j++;
			
		}
		
		
		/*
		try {
			dt.InserirDisciplinaEmTurma(d, t);
		} catch (TurmaCheiaException | AlunoMatriculadoTurmaException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		*/
		
		// dt.removerDisciplinaDeTurma(t);
		
		/*
		try {
			dt.cadastrarDisciplina(d);
		} catch (TurmaExistenteException | CursoCheioException
				| ConexaoException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
				try {
						try {
							rn.cadastrar(t);
						} catch (TurmaExistenteException | CursoCheioException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
						}
					} catch (ConexaoException | SQLException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
*/
					//Turma teste = rn.pesquisarTurma("Teste");
					//teste.setNomeDaTurma("NovoNome");	
					//teste.setPeriodoAtual("Nono");
					
					//System.out.println(teste.getNomeDaTurma());
					/*
					try {
								rn.alterar(teste);
							} catch (TurmaInexistenteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						*/
					
						//rn.removerAluno(a, alterar);

				/*
						try {
							rn.matricularAluno(b, t);
						} catch (TurmaCheiaException
								| AlunoMatriculadoTurmaException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						*/
				/*
				try {
					rn.remover(t);
				} catch (TurmaInexistenteException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}	
				*/
	}	
}
	