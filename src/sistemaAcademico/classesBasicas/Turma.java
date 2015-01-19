package sistemaAcademico.classesBasicas;

import java.util.List;

import sistemaAcademico.enuns.Turno;

public class Turma {

private int id;	
private String nomeDaTurma;
private Professor professorDaTurma;
private Turno turnoDaTurma;
private List <Disciplina> disciplinasDaTurma;
private String periodoAtual;
private List <Aluno> alunosDaTurma;


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
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
public List<Disciplina> getDisciplinasDaTurma() {
	return disciplinasDaTurma;
}
public void setDisciplinasDaTurma(List<Disciplina> disciplinasDaTurma) {
	this.disciplinasDaTurma = disciplinasDaTurma;
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


	
}
