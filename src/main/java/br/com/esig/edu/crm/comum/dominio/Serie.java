package br.com.esig.edu.crm.comum.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa uma determinada série/ano no ensino básico.
 * Representa as várias séries
 *
 * @author Gleydson Lima
 * @author Dinarte
 */
@Entity
@Table(name = "serie", schema = "basico", uniqueConstraints = {})
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Serie {

	@Id
	@GeneratedValue(generator = "serieGenerator")
	@GenericGenerator(name = "serieGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "basico.serie_seq") })
	@Column(name = "id_serie", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;

	@Column(name = "descricao", nullable = false)
	private String descricao;
}
