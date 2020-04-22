/* Direitos de Propriedade da SIG Software e Consultoria em TI, CNPJ 13.406.686/0001-67,
 * NÃO ALTERE OU REMOVA O COPYRIGHT DO CABEÇALHO DESTE ARQUIVO.
 *
 * Este código é um código proprietário e não pode ser redistribuído, copiado ou
 * modificado sem expressa autorização de seu proprietário. Violações do direito autoral
 * podem ser noticadas através de contato@esig.com.br.
 *
 */
package br.com.esig.edu.crm.comum.dominio;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import br.com.esig.utils.ValidatorUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa um município brasileiro.
 *
 * @author Gleydson
 */
@Entity
@Table(name = "municipio", schema = "comum")
@Cacheable
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Municipio{

	@Id
	@GeneratedValue(generator = "municipioGenerator")
	@GenericGenerator(name = "municipioGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "comum.municipio_seq") })
	@Column(name = "id")
	private int id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cep")
	private String cep;

	@Transient
	private boolean capital;

	@JoinColumn(name = "uf_id")
	@ManyToOne
	private UnidadeFederativa uf;

	@Column(name = "cod_ibge")
	private String codIBGE;

	@Column(name = "ativo")
	private boolean ativo;

	

	public String getDescricaoCompleta() {

		if (ValidatorUtil.isEmpty(uf) && ValidatorUtil.isEmpty(nome)) {
			return null;
		}

		if (ValidatorUtil.isNotEmpty(uf)) {
			return nome + "/" + uf.getSigla() + " - " + uf.getPais().getNome();
		}

		return nome;
	}

	public String getNomeEstado() {

		if (ValidatorUtil.isEmpty(uf) && ValidatorUtil.isEmpty(nome)) {
			return null;
		}

		if (ValidatorUtil.isNotEmpty(uf)) {
			return nome + "/" + uf.getSigla();
		}

		return nome;
	}
}
