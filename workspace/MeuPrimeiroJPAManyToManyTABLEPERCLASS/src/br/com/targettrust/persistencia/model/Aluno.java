package br.com.targettrust.persistencia.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ALUNOS")
public class Aluno extends Usuario {

	private String matricula;

	public Aluno() {
		super();
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Override
	public String toString() {
		return super.toString() + " // " + getMatricula();
	}

}
