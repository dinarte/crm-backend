package br.com.esig.edu.crm.comum.dominio;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "responsavel", schema = "ensino", uniqueConstraints = {})
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Responsavel {

	@Id
	@Column(name = "id_responsavel")
	private int id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "responsavel")
	private Set<ResponsavelDiscente> discenteResponsaveis = new HashSet<>(0);

	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pessoa", nullable = true)
	private Pessoa pessoa = new Pessoa();

	@Column(name = "id_arquivo", nullable = true)
	private String idArquivo;

}
