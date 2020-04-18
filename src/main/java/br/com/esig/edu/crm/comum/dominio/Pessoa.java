/* Direitos de Propriedade da SIG Software e Consultoria em TI, CNPJ 13.406.686/0001-67,
 * NÃO ALTERE OU REMOVA O COPYRIGHT DO CABEÇALHO DESTE ARQUIVO.
 *
 * Este código é um código proprietário e não pode ser redistribuído, copiado ou
 * modificado sem expressa autorização de seu proprietário. Violações do direito autoral
 * podem ser noticadas através de contato@esig.com.br.
 *
 */
package br.com.esig.edu.crm.comum.dominio;

import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.gson.annotations.Expose;

import br.com.esig.utils.Formatador;
import br.com.esig.utils.ValidatorUtil;

/**
 * @author gleydson
 *
 */
@Entity
@Table(name = "pessoa", schema = "comum")
public class Pessoa {

	@Id
	@GeneratedValue(generator = "pessoaGenerator")
	@GenericGenerator(name = "pessoaGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "comum.pessoa_seq") })
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true)
	@Expose
	private int id;

	@Version
	private int version;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_instituicao", nullable = true)
	@br.com.esig.audit.annotations.Instituicao
	private Instituicao instituicao;

	@Column(name = "nome", nullable = false)
	@Expose
	private String nome;

	@Column(name = "nome_ascii")
	private String nomeAscii;

	@Column(name = "abreviacao")
	private String abreviacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_nascimento")
	private Date dataNascimento;

	@Enumerated(EnumType.STRING)
	@Column(name = "sexo")
	private SexoEnum sexo;

	@Column(name = "cpf_cnpj", nullable = true, unique = true)
	private Long cpfCnpj;

	@Column(name = "rg")
	private String identidade;

	@Column(name = "orgao_emissor_rg")
	private String orgaoEmissorRg;

	@Column(name = "email")
	private String email;

	/* Dados do Endereço */

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

	@ManyToOne
	@JoinColumn(name = "municipio_id")
	private Municipio municipio;

	@Column(name = "telefone_fixo")
	private String telefoneFixo;

	@Column(name = "telefone_celular")
	private String telefoneCelular;

	@Column(name = "telefone_corporativo")
	private String telefoneCorporativo;

	public Pessoa() {
		municipio = new Municipio();
	}

	public Pessoa(int id, String nome) {
		this();
		setId(id);
		setNome(nome);
	}

	public Pessoa(int id, String nome, Long cpfCnpj) {
		this.id = id;
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public String getNome() {
		return nome;
	}

	public String getNomeComCPF() {
		return nome.concat(ValidatorUtil.isEmpty(cpfCnpj) ? "" : " (".concat(getCpfCnpjFormatado().concat(")")));
	}

	public String getNomeSobrenome() {
		String nomeSobrenome = "";
		if (ValidatorUtil.isNotEmpty(nome)) {
			String[] split = nome.split(" ");
			nomeSobrenome = split[0] + " " + split[split.length - 1];
		}
		return nomeSobrenome;
	}

	public String getPrimeiroNome() {
		String nome = "";
		if (ValidatorUtil.isNotEmpty(this.nome)) {
			String[] split = this.nome.split(" ");
			nome = split[0];
		}
		return nome;
	}

	public String getSobrenome() {
		String sobrenome = "";
		if (ValidatorUtil.isNotEmpty(nome)) {
			String[] split = nome.split(" ");
			if (split.length > 1)
				sobrenome = split[split.length - 1];
		}
		return sobrenome;
	}

	public void setNome(String nome) {
		this.nome = nome;
		this.nomeAscii = Normalizer.normalize(nome, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	public String getNomeAscii() {
		return nomeAscii;
	}

	public void setNomeAscii(String nomeAscii) {
		this.nomeAscii = nomeAscii;
	}

	public String getAbreviacao() {
		return abreviacao;
	}

	public void setAbreviacao(String abreviacao) {
		this.abreviacao = abreviacao;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public String getDataNascimentoFormatada() {
		if (dataNascimento != null) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			return format.format(dataNascimento);
		} else {
			return "NI";
		}
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public Long getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(Long cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

	public String getOrgaoEmissorRg() {
		return orgaoEmissorRg;
	}

	public void setOrgaoEmissorRg(String orgaoEmissorRg) {
		this.orgaoEmissorRg = orgaoEmissorRg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		if (ValidatorUtil.isEmpty(cep)) {
			return cep;
		}
		String cepTratado = cep.trim();
		cepTratado = cepTratado.replace(".", "");
		cepTratado = cepTratado.replace("-", "");
		return cepTratado;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCepFormatado() {
		if (ValidatorUtil.isEmpty(cep)) {
			return cep;
		}
		return Formatador.getInstance().formatarCep(Integer.parseInt(getCep()));
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Object getEnderecoCompleto() {
		if (logradouro != null) {
			return logradouro.concat(ValidatorUtil.isNotEmpty(numero) ? ", número " + numero : "")
					.concat(ValidatorUtil.isNotEmpty(complemento) ? ", " + complemento : "")
					.concat(ValidatorUtil.isNotEmpty(bairro) ? ", " + bairro : "")
					.concat(ValidatorUtil.isNotEmpty(municipio) ? ", " + municipio.getDescricaoCompleta() : "")
					.toString();
		} else {
			return "";
		}
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getTelefoneCorporativo() {
		return telefoneCorporativo;
	}

	public void setTelefoneCorporativo(String telefoneCorporativo) {
		this.telefoneCorporativo = telefoneCorporativo;
	}

	public String getCpfCnpjNome() {
		if (ValidatorUtil.isNotEmpty(nome)) {
			return getCpfCnpjFormatado() + " - " + nome;
		}
		return null;
	}

	public String getCpfCnpjFormatado() {
		if (ValidatorUtil.isEmpty(cpfCnpj)) {
			return "";
		}
		return Formatador.getInstance().formatarCPF_CNPJ(cpfCnpj);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpfCnpj == null) ? 0 : cpfCnpj.hashCode());
		result = prime * result + id;
		result = prime * result + ((nomeAscii == null) ? 0 : nomeAscii.hashCode());
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		Pessoa other = (Pessoa) obj;
		if (cpfCnpj == null) {
			if (other.cpfCnpj != null) {
				return false;
			}
		} else if (!cpfCnpj.equals(other.cpfCnpj)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (nomeAscii == null) {
			if (other.nomeAscii != null) {
				return false;
			}
		} else if (!nomeAscii.equalsIgnoreCase(other.nomeAscii)) {
			return false;
		}
		return true;
	}
}
