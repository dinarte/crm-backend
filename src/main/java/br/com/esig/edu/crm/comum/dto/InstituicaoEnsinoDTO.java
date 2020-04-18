package br.com.esig.edu.crm.comum.dto;

public class InstituicaoEnsinoDTO {

	private int id;

	private String nome;

	private InstituicaoDTO instituicao;

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

	public InstituicaoDTO getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoDTO instituicao) {
		this.instituicao = instituicao;
	}

}
