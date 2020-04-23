package br.com.esig.edu.crm.auth;

import br.com.esig.edu.crm.comum.dominio.Usuario;
import br.com.esig.edu.crm.comum.dto.InstituicaoEnsinoDTO;

/**
 * Retorno da autenticação do responsável. Adicione aqui todos os atributos que
 * desejar retornar na autenticação para o Front.
 * 
 * @author gleydson
 *
 */
public class AutenticacaoResponse {

	private int userId;

	private String login;
	private String nome;

	private InstituicaoEnsinoDTO instituicaoEnsino;

	private int pessoaId;

	private String jwt;

	public AutenticacaoResponse(Usuario usuario) {
		this.nome = usuario.getNome();
		this.userId = usuario.getId();
		this.login = usuario.getLogin();
		this.pessoaId = usuario.getPessoa() != null ? usuario.getPessoa().getId() : 0;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public InstituicaoEnsinoDTO getInstituicaoEnsino() {
		return instituicaoEnsino;
	}

	public void setInstituicaoEnsino(InstituicaoEnsinoDTO instituicaoEnsino) {
		this.instituicaoEnsino = instituicaoEnsino;
	}

	public int getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(int pessoaId) {
		this.pessoaId = pessoaId;
	}

}