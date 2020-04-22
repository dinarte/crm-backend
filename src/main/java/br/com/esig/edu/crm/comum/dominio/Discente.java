package br.com.esig.edu.crm.comum.dominio;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classe mÃ£e de todos os tipos de alunos. Usada como base para DiscenteBasico,
 * DiscenteGraduacao, DiscenteLato.....
 *
 * @author Gleydson Lima
 *
 */
@Entity
@Table(name = "discente", schema = "ensino")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Discente {

	@Id
	@Column(name = "id_discente", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "discente")
	private Set<ResponsavelDiscente> discenteResponsaveis;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_ingresso")
	private Date dataIngresso;

	@Column(name = "matricula", unique = true, nullable = true, insertable = true, updatable = true)
	private String matricula;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_status_discente")
	private StatusDiscente statusDiscente;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa = new Pessoa();

	/** ReferÃªncia a unidade gestora do discente */
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_instituicao_ensino", unique = false, nullable = true)
	private InstituicaoEnsino instituicaoEnsino;

}