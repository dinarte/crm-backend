package br.com.esig.edu.crm.comum.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.esig.utils.EqualsUtil;
import br.com.esig.utils.HashCodeUtil;

@Entity
@Table(name = "tipo_filiacao", schema = "comum")
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

	public TipoFiliacao() {}
	
	public TipoFiliacao(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public boolean isMae() {
		return this.equals(MAE);
	}
	
	public boolean isPai() {
		return this.equals(PAI);
	}
	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}


	@Override
	public int hashCode() {
		return HashCodeUtil.hashAll(id);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsUtil.testEquals(this, obj, "id");
	}

}
