/* Direitos de Propriedade da SIG Software e Consultoria em TI, CNPJ 13.406.686/0001-67,
 * NÃO ALTERE OU REMOVA O COPYRIGHT DO CABEÇALHO DESTE ARQUIVO.
 *
 * Este código é um código proprietário e não pode ser redistribuído, copiado ou
 * modificado sem expressa autorização de seu proprietário. Violações do direito autoral
 * podem ser noticadas através de contato@esig.com.br.
 *
 */
package br.com.esig.edu.crm.comum.dto;

import java.util.Date;

import br.com.esig.edu.crm.comum.dominio.SexoEnum;

public class PessoaDTO {

	private int id;
	private String nome;
	private String nomeAscii;
	private Date dataNascimento;
	private SexoEnum sexo;
	private Long cpfCnpj;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeAscii() {
		return nomeAscii;
	}

	public void setNomeAscii(String nomeAscii) {
		this.nomeAscii = nomeAscii;
	}

	public Date getDataNascimento() {
		return dataNascimento;
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

}
