package br.com.esig.edu.crm.comum.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Entidade que representa uma determinada série/ano no ensino básico.
 * Representa as várias séries
 *
 * @author Gleydson Lima
 * @author Dinarte
 */
@Entity
@Table(name = "serie", schema = "basico", uniqueConstraints = {})
public class Serie {

	@Id
	@GeneratedValue(generator = "serieGenerator")
	@GenericGenerator(name = "serieGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "basico.serie_seq") })
	@Column(name = "id_serie", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
