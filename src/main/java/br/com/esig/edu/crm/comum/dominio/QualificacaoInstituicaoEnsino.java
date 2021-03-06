package br.com.esig.edu.crm.comum.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import br.com.esig.audit.annotations.CriadoEm;
import br.com.esig.utils.EqualsUtil;
import br.com.esig.utils.HashCodeUtil;
import br.com.esig.utils.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author geyson.soares
 *
 */
@Entity
@Table(name = "qualificacao_instituicao_ensino", schema = "geral")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class QualificacaoInstituicaoEnsino {

	@Id
	@GeneratedValue(generator = "qualificacaoInstituicaoEnsinoGenerator")
	@GenericGenerator(name = "qualificacaoInstituicaoEnsinoGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "geral.qualificacao_instituicao_ensino_seq") })
	@Column(name = "id_qualificacao_instituicao_ensino", unique = true, nullable = false)
	private int id;

	@Column(name = "nome", unique = false, nullable = false, insertable = true, updatable = true, length = 200)
	private String nome;

	@Column(name = "sigla", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	private String sigla;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro", updatable = false)
	@CriadoEm
	private Date dataCadastro;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "instituicao_id", nullable = true)
	@br.com.esig.audit.annotations.Instituicao
	private Instituicao instituicao;

}
