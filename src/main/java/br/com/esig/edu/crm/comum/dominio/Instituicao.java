/*
 * Direitos de Propriedade da SIG Software e Consultoria em TI, CNPJ 13.406.686/0001-67,
 * NÃO ALTERE OU REMOVA O COPYRIGHT DO CABEÇALHO DESTE ARQUIVO.
 *
 * Este código é um código proprietário e não pode ser redistribuído, copiado ou
 * modificado sem expressa autorização de seu proprietário. Violações do direito autoral
 * podem ser noticadas através de contato@esig.com.br.
 *
 */
package br.com.esig.edu.crm.comum.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "instituicao", schema = "comum")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Instituicao {


	@Id
	@Column(name = "id")
	private int id;

	private String nome;

	private String nomeExibicao;

	private String url;

	private String email;

	private String emailFinanceiro;

	@Column(name = "cpf_cnpj")
	private Long cpfCnpj;

	@ManyToOne
	@JoinColumn(name = "instituicao_grupo_id")
	private InstituicaoGrupo instituicaoGrupo;
	
	public Instituicao(int id) {
		this.id = id;
	}


}