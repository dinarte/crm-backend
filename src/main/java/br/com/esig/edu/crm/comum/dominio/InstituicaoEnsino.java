package br.com.esig.edu.crm.comum.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.esig.audit.annotations.AtualizadoEm;
import br.com.esig.audit.annotations.CriadoEm;
import br.com.esig.utils.Formatador;
import br.com.esig.utils.ValidatorUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Instituição de ensino é uma generalização para mapear Universidades,
 * Institutos ou Escolas. Uma Instituicao (cliente) poderá ter várias
 * instituições acadêmicas vinculadas, que por sua vez poderá ter várias
 * unidades acadêmicas (InstituicaoEnsinoUnidade).
 *
 *
 * @author gleydson
 *
 */
@Entity
@Table(name = "instituicao_ensino", schema = "geral")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class InstituicaoEnsino {

	@Id
	@Column(name = "id_instituicao_ensino", unique = true, nullable = false)
	private int id;

	@Column(name = "nome", unique = false, nullable = false, insertable = true, updatable = true, length = 120)
	private String nome;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_instituicao", nullable = false)
	private Instituicao instituicao;

	@Column(name = "complexo_integrado")
	private Boolean complexoIntegrado;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_instituicao_mae", nullable = true)
	private InstituicaoEnsino instituicaoMae;

	@Column(name = "sigla", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	private String sigla;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_qualificacao_instituicao_ensino", nullable = false)
	private QualificacaoInstituicaoEnsino qualificacaoInstituicaoEnsino;

	/* Dados Mantenedora */
	@Column(name = "mantenedora")
	private String mantenedora;

	@Column(name = "cnpj")
	private Long cnpj;

	@Column(name = "inscricao_municipal")
	private String inscricaoMunicipal;

	/* Dados adicionais */
	@Column(name = "email")
	private String email;

	@Column(name = "homepage")
	private String homepage;

	/* Dados do Endereço */
	@ManyToOne
	@JoinColumn(name = "municipio_id", nullable = false)
	private Municipio municipio;

	@Column(name = "logradouro")
	private String logradouro;

	@Column(name = "numero")
	private String numero;

	@Column(name = "complemento")
	private String complemento;

	@Column(name = "bairro")
	private String bairro;

	@Column(name = "cep")
	private String cep;

	@Column(name = "latitude")
	private String latitude;

	@Column(name = "longitude")
	private String longitude;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro", updatable = false)
	@CriadoEm
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_atualizacao")
	@AtualizadoEm
	private Date dataAtualizacao;

	public InstituicaoEnsino(int id) {
		this.id = id;
	}

	
	public String getCnpjFormatado() {
		if (ValidatorUtil.isEmpty(cnpj)) {
			return "";
		}
		return Formatador.getInstance().formatarCPF_CNPJ(cnpj);
	}

	public String getCnpjNumeros() {
		if (ValidatorUtil.isEmpty(cnpj)) {
			return "";
		}
		String cnpjTratado = Formatador.getInstance().formatarCPF_CNPJ(cnpj);
		cnpjTratado = cnpjTratado.replace(".", "");
		cnpjTratado = cnpjTratado.replace("/", "");
		cnpjTratado = cnpjTratado.replace("-", "");
		return cnpjTratado;
	}

}
