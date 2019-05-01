package br.com.targettrust.persistencia.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "ALUNOS")
@AttributeOverrides(value=
{@AttributeOverride(name="id",column=@Column(name="ALUPK"))
})
@PrimaryKeyJoinColumn(name="ALUPK",referencedColumnName="USRPK")//FAZ A LIGAÇÃO ENTRE AS PK REESCRITAS
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
