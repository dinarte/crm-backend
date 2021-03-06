package br.com.esig.edu.crm.comum.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classe que define os possíveis status de um discente.
 *
 * @author Gleydson Lima
 */
@Entity
@Table(name = "status_discente", schema = "ensino")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class StatusDiscente {

	@Id
	@GeneratedValue(generator = "statusDiscenteGenerator")
	@GenericGenerator(name = "statusDiscenteGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "ensino.status_discente_seq") })
	@Column(name = "id_status_discente", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;

	/** Descrição do status. */
	@Column(name = "descricao", unique = false, nullable = false, insertable = true, updatable = true, length = 80)
	private String descricao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_instituicao", nullable = true)
	private Instituicao instituicao;

}