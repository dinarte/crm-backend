package br.com.esig.edu.crm.comum.dto;

public class DiscenteDTO {

	private int id;
	private String matricula;
	private StatusDiscenteDTO statusDiscente;
	private PessoaDTO pessoa;
	private InstituicaoEnsinoDTO instituicaoEnsino;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public StatusDiscenteDTO getStatusDiscente() {
		return statusDiscente;
	}

	public void setStatusDiscente(StatusDiscenteDTO statusDiscente) {
		this.statusDiscente = statusDiscente;
	}

	public PessoaDTO getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaDTO pessoa) {
		this.pessoa = pessoa;
	}

	public InstituicaoEnsinoDTO getInstituicaoEnsino() {
		return instituicaoEnsino;
	}

	public void setInstituicaoEnsino(InstituicaoEnsinoDTO instituicaoEnsino) {
		this.instituicaoEnsino = instituicaoEnsino;
	}

}