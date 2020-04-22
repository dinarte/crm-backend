package br.com.esig.edu.crm.comum.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "responsavel_discente", schema = "ensino")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class ResponsavelDiscente {

	@Id
	private int id;

	@ManyToOne
	@JoinColumn(name = "id_discente")
	private Discente discente;

	@ManyToOne
	@JoinColumn(name = "id_responsavel")
	private Responsavel responsavel;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipo_filiacao", nullable = false)
	private TipoFiliacao tipoFiliacao;

	@Column(name = "financeiro")
	private Boolean financeiro = false;
	
}