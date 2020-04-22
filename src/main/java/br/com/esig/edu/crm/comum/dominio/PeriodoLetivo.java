package br.com.esig.edu.crm.comum.dominio;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.esig.edu.crm.dominio.Contato;
import br.com.esig.edu.crm.dominio.EnderecoEmail;
import br.com.esig.edu.crm.dominio.Telefone;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "ensino", name = "periodo_letivo")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class PeriodoLetivo {

	@Id
	@Column(name = "id_periodo_letivo", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;

	@Column(name = "denominacao", unique = false, nullable = false, insertable = true, updatable = true, length = 80)
	private String denominacao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_instituicao", nullable = true)
	private Instituicao instituicao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inicio", nullable = false)
	private Date dataInicio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_fim", nullable = false)
	private Date dataFim;

}