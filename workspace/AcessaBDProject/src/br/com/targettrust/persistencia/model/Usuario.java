package br.com.targettrust.persistencia.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// Model - tipo JavaBean

public class Usuario implements Serializable {

	private String nome;
	private String rg;
	private String cpf;
	private Date dataNasc;
	private transient BigDecimal salario;
	// transient deixa o atributo somente na memoria

	public Usuario() {// construtor no-args
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public String toString(){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		NumberFormat nf = new DecimalFormat("R$ #,##0.00");
				
		return getNome() + " // " +
				getRg()	+ " // " +
				getCpf() + " // " +
				df.format(getDataNasc()) + " // " +
				nf.format(getSalario())  ;
	}
}
