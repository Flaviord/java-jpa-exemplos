package br.com.targettrust.persistencia.model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// Model - para persistencia
/**
 * CLasse com implemento da interface Serializable atributos privados metodos
 * publicos anotations - jpa
 * 
 */

/**
 * @author sala01
 *
 */
@Entity
@Table(name = "USUARIOS")
@Inheritance(strategy=InheritanceType.JOINED)
@AttributeOverrides(value=
					{@AttributeOverride(name="id",column=@Column(name="USRPK")) //MODIFICA O NOME DO ID PARA USERPK
				})
public class Usuario extends BasicEntity {

	// private Long id;// foi para classe pai
	private String nome;
	private String rg;
	private String cpf;
	private Date dataNasc;
	private transient BigDecimal salario;
	private Sexo sexo;
	private Perfil perfil;
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	private List<Telefone> telefones = new ArrayList<Telefone>();
	private List<Turma> turmas = new ArrayList<Turma>(); 

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

	@OneToMany(targetEntity=Endereco.class,cascade={CascadeType.ALL},
			   orphanRemoval = true,
			   fetch = FetchType.LAZY,
			   mappedBy = "usuario")//diz para JPA não criar tabela associativa	
//	@JoinTable(name="USUARIOS_HAS_ENDERENCOS",
//				joinColumns={@JoinColumn(name="USUARIO_CHAVE")},
//				inverseJoinColumns={@JoinColumn(name="ENDERECO_CHAVE")}
//				)
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	
	public void addEndereco(Endereco aux){
		this.enderecos.add(aux);
		aux.setUsuario(this);
	}
	
	public void removeEndereco(Endereco aux){
		aux.setUsuario(null);
	}
	
	@OneToMany(targetEntity=Telefone.class,cascade={CascadeType.ALL},
			   orphanRemoval = true,
			   fetch = FetchType.LAZY,
			   mappedBy = "usuario")		//  mappedBy = "usuario" é o mapeameno do getUsuario	
	@OrderBy("ddd ASC numeral Desc")	
	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public void addTelefone(Telefone aux){
		this.telefones.add(aux);
		aux.setUsuario(this);
	}
	
	public void removeTelefone(Telefone aux){
		aux.setUsuario(null);
	}

	@ManyToMany(targetEntity=Turma.class,cascade={CascadeType.MERGE,CascadeType.PERSIST},
			   fetch = FetchType.LAZY,
			   mappedBy = "usuarios")//  mappedBy = "usuarios" é o mapeamento getUsuarios
	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
	
//	public void addTurma(Turma aux){
//		this.turmas.add(aux);
//		aux.setUsuario(this);
//	}
//	
//	public void removeTurma(Turma aux){
//		aux.setUsuario(null);
//	}

	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		NumberFormat nf = new DecimalFormat("R$ #,##0.00");

		return getNome() + " // " + getRg() + " // " + getCpf() + " // "
				+ df.format(getDataNasc()) + " // " + nf.format(getSalario())
				+ " // " + getSexo() + " // " + getPerfil();
	}
}
