package br.com.targettrust.persistencia.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@NamedQueries(value={
		@NamedQuery(name = "TurmaPorDescricao",
				 query = "select object(t) from Turma t where UPPER(t.descricao) LIKE UPPER(:descricao)"),
		@NamedQuery(name = "UsuarioPorCpf",
			     query = "select object(u) from Usuario u where u.cpf = :cpf")

}

)


@MappedSuperclass
// comparilha anota��es para quem quiser extender
public abstract class BasicEntity implements Serializable {

	private Long id;

	public BasicEntity() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gerador")
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "gerador", sequenceName = "targettrust_seq")
	// N�O UTILIZADO COM O MODO TABLE
	// gera o valor automaticamente do BD
	// UTILIZADO PARA BD QUE N�O CONTEM AUTO-INCREMENT
	// @GeneratedValue(strategy = GenerationType.TABLE, generator = "gerador")
	// @TableGenerator(name="gerador",
	// table = "SEQUENCIADORA",
	// initialValue = 1,
	// pkColumnName = "PRIMARY_KEY_COLUMN",
	// valueColumnName = "VALUE_COLUMN",
	// pkColumnValue = "ID",
	// allocationSize = 1
	// )
	// FIM
	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

}
