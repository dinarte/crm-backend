package br.com.esig.edu.crm.comum.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_filiacao", schema = "comum")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class TipoFiliacao  {

	public static final TipoFiliacao PAI = new TipoFiliacao(1,"PAI");
	public static final TipoFiliacao MAE = new TipoFiliacao(2,"MÃE");
	
	@Id
	@Column(name = "id_tipo_filiacao", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;

	@Column(name = "descricao", unique = false, nullable = false, insertable = true, updatable = true, length = 80)
	private String descricao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_instituicao", nullable = true)
	@br.com.esig.audit.annotations.Instituicao
	private Instituicao instituicao;

	
	public TipoFiliacao(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
}
