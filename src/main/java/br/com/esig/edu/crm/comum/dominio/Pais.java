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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import br.com.esig.utils.EqualsUtil;
import br.com.esig.utils.HashCodeUtil;
import br.com.esig.validacao.PersistDB;

/**
 * Classe comum referente ao país.
 *
 * @author Gleydson
 */
@Entity
@Table(name = "pais", schema = "comum")
@Cacheable
public class Pais implements PersistDB {

	private static final long serialVersionUID = -3433675926686464612L;

	public static final int BRASIL = 1;

	@Id
	@GeneratedValue(generator = "paisGenerator")
	@GenericGenerator(name = "paisGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "comum.pais_seq") })
	@Column(name = "id_pais")
	private int id;

	@Column(name = "nome_pais")
	private String nome;

	@Column(name = "ativo")
	private boolean ativo;

	public Pais() {
	}

	public Pais(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return HashCodeUtil.hashAll(id);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsUtil.testEquals(this, obj, "id");
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
