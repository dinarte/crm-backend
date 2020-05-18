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
import lombok.NoArgsConstructor;

@Entity
@Table(name = "servico_serie", schema = "financeiro")
@Data
@NoArgsConstructor
public class ServicoSerie {


	@Id
	@GeneratedValue(generator = "servicoSerieGenerator")
	@GenericGenerator(name = "servicoSerieGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "financeiro.servico_serie_seq") })
	@Column(name = "id_servico_serie", unique = true, nullable = false)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_servico", nullable = false)
	private Servico servico;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_serie", nullable = false)
	private Serie serie;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_instituicao", nullable = true)
	@br.com.esig.audit.annotations.Instituicao
	private Instituicao instituicao;

	@Column(name="id_plano_contas_erp")
	private Integer planoContasErp;

	public ServicoSerie(Integer idServicoSerie) {
		this.id = idServicoSerie;
	}
	
}
