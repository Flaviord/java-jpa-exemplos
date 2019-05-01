package br.com.targettrust.persistencia.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "PROFESSORES")
@AttributeOverrides(value=
{@AttributeOverride(name="id",column=@Column(name="PROFPK"))
})
@PrimaryKeyJoinColumn(name="PROFPK",referencedColumnName="USRPK")//FAZ A LIGAÇÃO ENTRE AS PK REESCRITAS
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
