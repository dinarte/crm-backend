/* Direitos de Propriedade da SIG Software e Consultoria em TI, CNPJ 13.406.686/0001-67,
 * NÃO ALTERE OU REMOVA O COPYRIGHT DO CABEÇALHO DESTE ARQUIVO.
 *
 * Este código é um código proprietário e não pode ser redistribuído, copiado ou
 * modificado sem expressa autorização de seu proprietário. Violações do direito autoral
 * podem ser noticadas através de contato@esig.com.br.
 *
 */
package br.com.esig.edu.crm.comum.dominio;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa os usuários do sistema.
 *
 * @author Gleydson
 */
@Entity
@Table(name = "usuario", schema = "comum")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Usuario {

	@Id
	private int id;

	private String nome;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

	private String login;

	private String password;

	private Date lastLogin;

	boolean ativo = Boolean.TRUE;

	boolean habilitado = Boolean.TRUE;

	@ManyToOne
	@JoinColumn(name = "instituicao_grupo_id")
	private InstituicaoGrupo instituicaoGrupo;

	
	public Usuario(int id) {
		this.id = id;
	}

}