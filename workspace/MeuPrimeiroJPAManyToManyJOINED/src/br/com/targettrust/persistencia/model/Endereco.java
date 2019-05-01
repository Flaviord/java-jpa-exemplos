package br.com.targettrust.persistencia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ENDERECOS")
public class Endereco extends BasicEntity {

	// private Long id;

	private String logradouro;
	private Integer numeral;
	private String bairro;
	private String cep;
	private String cidade;
	private String uf;
	private Usuario usuario;

	public Endereco() {
		super();
	}

	@Column(name = "RUA", length = 100, nullable = false)
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@Column(name = "NUMERO", nullable = false)
	public Integer getNumeral() {
		return numeral;
	}

	public void setNumeral(Integer numeral) {
		this.numeral = numeral;
	}

	@Column(length = 30, nullable = true)
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Column(length = 8, nullable = false)
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Column(name = "LOCALIDADE", length = 40, nullable = false)
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Column(name = "ESTADO", length = 2, nullable = false)
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@ManyToOne(targetEntity = Usuario.class)
	@JoinColumn(name="USUARIO_CHAVE")
	// mapped="endereco" diz para o JPA que a chave do da tabela (PK) USUARIO
	// não vira para tabela ENDERECOS
	// todo o controle ficara em usuarios.
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return getLogradouro() + " // " + getNumeral() + " // " + getBairro()
				+ " // " + getCep() + " // " + getCidade() + " // " + getUf();
	}
}
