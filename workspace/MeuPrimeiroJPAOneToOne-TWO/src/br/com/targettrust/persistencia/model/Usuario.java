package br.com.targettrust.persistencia.model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
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
public class Usuario extends BasicEntity {

	// private Long id;// foi para classe pai
	private String nome;
	private String rg;
	private String cpf;
	private Date dataNasc;
	private transient BigDecimal salario;
	private Sexo sexo;
	private Perfil perfil;
	private Endereco endereco;
	private Telefone telefone;

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

	// @Transient//não persiste no banco
	@Column(name = "EMOLUMENTOS", precision = 10, scale = 2, nullable = true, insertable = true, updatable = true)
	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	@Enumerated(EnumType.STRING)
	// STRING=GRAVA OS NOME DO ENUM ORDINAL= GRAVA [0-1]
	@Column(name = "GENERO", nullable = false)
	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "PERFIL", nullable = false)
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	@OneToOne(targetEntity = Endereco.class, cascade = { CascadeType.ALL })
	// permite fazer [INSERT,UPDATE,DELETE] cascateado.
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
		endereco.setUsuario(this);// isto garante a BIDIRECIONALIDADE ENTRE
									// USUARIO E ENDERECO
	}
	
	@OneToOne(targetEntity = Telefone.class, cascade = {CascadeType.ALL})
	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
		telefone.setUsuario(this);
	}

	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		NumberFormat nf = new DecimalFormat("R$ #,##0.00");

		return getNome() + " // " + getRg() + " // " + getCpf() + " // "
				+ df.format(getDataNasc()) + " // " + nf.format(getSalario())
				+ " // " + getSexo() + " // " + getPerfil();
	}
}
