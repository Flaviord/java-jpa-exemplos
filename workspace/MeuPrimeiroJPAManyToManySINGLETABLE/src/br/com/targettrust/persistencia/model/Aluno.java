package br.com.targettrust.persistencia.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="A")
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
