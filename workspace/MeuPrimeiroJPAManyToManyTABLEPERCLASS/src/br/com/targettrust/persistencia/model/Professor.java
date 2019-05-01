package br.com.targettrust.persistencia.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PROFESSORES")
public class Professor extends Usuario {

	private String especializacao;

	public Professor() {
		super();
	}

	public String getEspecializacao() {
		return especializacao;
	}

	public void setEspecializacao(String especializacao) {
		this.especializacao = especializacao;
	}

	@Override
	public String toString() {
		return super.toString() + " // " + getEspecializacao();
	}

}
