package br.com.targettrust.persistencia.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TURMAS")
public class Turma extends BasicEntity {

	private Character sala;
	private String descricao;
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	public Turma() {
		super();// no-args
	}

	public Turma(String desc, Character sala) {
		this.descricao = desc;
		this.sala = sala;
	}
	

	@Column(nullable = false)
	public Character getSala() {
		return sala;
	}

	public void setSala(Character sala) {
		this.sala = sala;
	}

	@Column(length = 80, nullable = false)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@ManyToMany(targetEntity = Usuario.class,
				cascade = { CascadeType.PERSIST,
				CascadeType.MERGE },
				fetch = FetchType.LAZY
			)
	@JoinTable(name = "TURMAS_HAS_USUARIOS",
				joinColumns = { @JoinColumn(name = "TURMA_CHAVE") },
				inverseJoinColumns = { @JoinColumn(name = "USUARIO_CHAVE") })
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void addUsuario(Usuario aux) {
		this.usuarios.add(aux);
		aux.getTurmas().add(this);
	}

	public void removeUsuario(Usuario aux) {
		this.usuarios.remove(aux);
		aux.getTurmas().remove(this);
	}

	@Override
	public String toString() {
		return getSala() + " // " + getDescricao();
	}
}
