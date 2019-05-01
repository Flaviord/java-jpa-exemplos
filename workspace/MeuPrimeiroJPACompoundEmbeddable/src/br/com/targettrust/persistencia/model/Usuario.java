package br.com.targettrust.persistencia.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// Model - para persistencia
/**
 * CLasse com implemento da interface Serializable atributos privados metodos
 * publicos anotations - jpa
 * 
 */

@Entity
@Table(name = "USUARIOS")
public class Usuario implements Serializable {

	private UsuarioPK usuarioPK = new UsuarioPK();
	private String cpf;
	private Date dataNasc;
	private transient BigDecimal salario;

	// transient deixa o atributo somente na memoria
	// utilizar as anotations nos metodos gets

	public Usuario() {// construtor no-args
		super();// super na Serializable
	}

	// utilizar as anotations nos metodos gets
	@EmbeddedId
	// Os dois tribrutos da Classe usuarioPK são tornam-se chaves compostas 
	public UsuarioPK getUsuarioPK() {
		return usuarioPK;
	}

	public void setUsuarioPK(UsuarioPK usuarioPK) {
		this.usuarioPK = usuarioPK;
	}

	@Column(name = "CIC", length = 11)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "aniversario", nullable = false)
	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	@Column(name = "EMOLUMENTOS", precision = 10, scale = 2, nullable = true)
	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		NumberFormat nf = new DecimalFormat("R$ #,##0.00");

		return getUsuarioPK().getNome() + " // " + getUsuarioPK().getRg()
				+ " // " + getCpf() + " // " + df.format(getDataNasc())
				+ " // " + nf.format(getSalario());
	}
}
