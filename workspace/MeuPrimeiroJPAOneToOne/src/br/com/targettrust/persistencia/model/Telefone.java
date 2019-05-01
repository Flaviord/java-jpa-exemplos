package br.com.targettrust.persistencia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TELEFONES")
public class Telefone extends BasicEntity {

	private Integer ddd;
	private String numeral;
	private String ramal;
	private Usuario usuario;
	
	public Telefone() {
		super();
	}
	
	@Column(name="DDD",length=3,nullable=false)
	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}
	
	@Column(name="NUMERO",length=9,nullable=false)
	public String getNumeral() {
		return numeral;
	}

	public void setNumeral(String numeral) {
		this.numeral = numeral;
	}

	@Column(name="RAMAL",length=4,nullable=true)
	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	@OneToOne(targetEntity=Usuario.class,mappedBy="telefone")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String toString(){
		return 
				getDdd() + " // " +
				getNumeral() + " // " +
				getRamal();
	}

}
