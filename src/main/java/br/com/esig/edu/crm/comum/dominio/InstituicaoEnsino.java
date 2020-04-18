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
import br.com.esig.utils.StringUtils;
import br.com.esig.utils.ValidatorUtil;

/**
 * Instituição acadêmica é uma generalização para mapear Universidades,
 * Institutos ou Escolas. Uma Instituicao (cliente) poderá ter várias
 * instituições acadêmicas vinculadas, que por sua vez poderá ter várias
 * unidades acadêmicas (InstituicaoAcademicaUnidade).
 *
 *
 * @author gleydson
 *
 */
@Entity
@Table(name = "instituicao_ensino", schema = "geral")
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

	public InstituicaoEnsino(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public InstituicaoEnsino() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public String getNomeMaiusculo() {

		return StringUtils.upperCase(nome);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Boolean getComplexoIntegrado() {
		return complexoIntegrado;
	}

	public void setComplexoIntegrado(Boolean complexoIntegrado) {
		this.complexoIntegrado = complexoIntegrado;
	}

	public InstituicaoEnsino getInstituicaoMae() {
		return instituicaoMae;
	}

	public void setInstituicaoMae(InstituicaoEnsino instituicaoMae) {
		this.instituicaoMae = instituicaoMae;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public QualificacaoInstituicaoEnsino getQualificacaoInstituicaoEnsino() {
		return qualificacaoInstituicaoEnsino;
	}

	public void setQualificacaoInstituicaoEnsino(QualificacaoInstituicaoEnsino qualificacaoInstituicaoEnsino) {
		this.qualificacaoInstituicaoEnsino = qualificacaoInstituicaoEnsino;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getMantenedora() {
		return mantenedora;
	}

	public void setMantenedora(String mantenedora) {
		this.mantenedora = mantenedora;
	}

	public Long getCnpj() {
		return cnpj;
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

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getId();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof InstituicaoEnsino)) {
			return false;
		}
		InstituicaoEnsino other = (InstituicaoEnsino) obj;
		if (getId() != other.getId()) {
			return false;
		}
		return true;
	}

}
