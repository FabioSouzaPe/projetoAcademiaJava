package sistemaAcademico.classesBasicas;

import java.util.List;

import sistemaAcademico.enuns.Turno;

public class Turma {

private int idTurma;	
private String nomeDaTurma;
private Professor professorDaTurma;
private int idProfessor;
private Turno turnoDaTurma;
private int idDisciplina;
private Curso curso;
private int idCurso;


public int getIdCurso() {
	return idCurso;
}
public void setIdCurso(int idCurso) {
	this.idCurso = idCurso;
}
public int getIdDisciplina() {
	return idDisciplina;
}
public void setDisciplina(int disciplina) {
	this.idDisciplina = disciplina;
}
public Curso getCurso() {
	return curso;
}
public void setCurso(Curso curso) {
	this.curso = curso;
}
public int getIdTurma() {
	return idTurma;
}
public void setIdTurma(int idTurma) {
	this.idTurma = idTurma;
}
public int getIdProfessor() {
	return idProfessor;
}
public void setIdProfessor(int idProfessor) {
	this.idProfessor = idProfessor;
}


private String periodoAtual;
private List <Aluno> alunosDaTurma;


public int getId() {
	return idTurma;
}
public void setId(int id) {
	this.idTurma = id;
}
public String getNomeDaTurma() {
	return nomeDaTurma;
}
public void setNomeDaTurma(String nomeDaTurma) {
	this.nomeDaTurma = nomeDaTurma;
}
public Professor getProfessorDaTurma() {
	return professorDaTurma;
}
public void setProfessorDaTurma(Professor professorDaTurma) {
	
	this.professorDaTurma = professorDaTurma;
	
}

public Turno getTurnoDaTurma() {
	return turnoDaTurma;
}
public void setTurnoDaTurma(Turno turnoDaTurma) {
	this.turnoDaTurma = turnoDaTurma;
}
public String getPeriodoAtual() {
	return periodoAtual;
}
public void setPeriodoAtual(String periodoAtual) {
	this.periodoAtual = periodoAtual;
}
public List<Aluno> getAlunosDaTurma() {
	return alunosDaTurma;
}
public void setAlunosDaTurma(List<Aluno> alunosDaTurma) {
	this.alunosDaTurma = alunosDaTurma;
}

public int getIdProfessor (Professor professorTurma) {
	
	return professorTurma.getId();
}


	
}
