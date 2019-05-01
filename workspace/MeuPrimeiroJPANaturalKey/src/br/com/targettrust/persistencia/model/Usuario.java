package br.com.targettrust.persistencia.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

// Model - para persistencia
/**
 * CLasse com implemento da interface Serializable atributos privados metodos
 * publicos anotations - jpa
 * 
 */

@Entity
@Table(name = "USUARIOS", 
		uniqueConstraints = { 
		@UniqueConstraint(columnNames = {"ALCUNHA", "IDENTIDADE" }) 
		}
)

public class Usuario implements Serializable {

	private String nome;
	private String rg;
	private String cpf;
	private Date dataNasc;
	private transient BigDecimal salario;

	// transient deixa o atributo somente na memoria

	public Usuario() {// construtor no-args
		super();// super na Serializable
	}

	@Column(name = "ALCUNHA", length = 80, nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "IDENTIDADE", length = 10, nullable = false)
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	// utilizar as anotations nos metodos gets
	@Id
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
	
	//@Transient//não persiste no banco
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

		return getNome() + " // " + getRg() + " // " + getCpf() + " // "
				+ df.format(getDataNasc()) + " // " + nf.format(getSalario());
	}
}
