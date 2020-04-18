package br.com.esig.edu.crm.comum.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "responsavel_discente", schema = "ensino")
public class ResponsavelDiscente {

	@Id
	private int id;

	@ManyToOne
	@JoinColumn(name = "id_discente")
	private Discente discente;

	@ManyToOne
	@JoinColumn(name = "id_responsavel")
	private Responsavel responsavel;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipo_filiacao", nullable = false)
	private TipoFiliacao tipoFiliacao;

	@Column(name = "financeiro")
	private Boolean financeiro = false;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Discente getDiscente() {
		return discente;
	}

	public void setDiscente(Discente discente) {
		this.discente = discente;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public TipoFiliacao getTipoFiliacao() {
		return tipoFiliacao;
	}

	public void setTipoFiliacao(TipoFiliacao tipoFiliacao) {
		this.tipoFiliacao = tipoFiliacao;
	}

	public Boolean getFinanceiro() {
		return financeiro;
	}

	public void setFinanceiro(Boolean financeiro) {
		this.financeiro = financeiro;
	}

}