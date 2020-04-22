package br.com.esig.edu.crm.comum.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "servico", schema = "financeiro")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Servico {

	@Id
	@Column(name = "id_servico", unique = true, nullable = false)
	private int id;

	@Column(name = "descricao", nullable = false)
	@Expose
	private String descricao;

	@Column(name = "ativo")
	private Boolean ativo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_instituicao", nullable = true)
	private Instituicao instituicao;

	public Servico(String descricao) {
		this.descricao = descricao;
	}
}