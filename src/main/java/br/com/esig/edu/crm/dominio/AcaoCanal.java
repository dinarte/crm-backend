package br.com.esig.edu.crm.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import br.com.esig.edu.crm.comum.dominio.Instituicao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "crm", name = "acao_canal")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class AcaoCanal {
	
	
	public static AcaoCanal QUARK_CRM = new AcaoCanal(9999);
	
	@Id
	@GeneratedValue(generator = "acaoCanalGenerator")
	@GenericGenerator(name = "acaoCanalGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "crm.acao_canal_seq") })
	@Column(name = "id_acao_canal", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;
	
	private String descricao;
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_instituicao", unique = false, nullable = true)
	@br.com.esig.audit.annotations.Instituicao
	private Instituicao instituicao;
	
	public AcaoCanal(int id) {
		this.id = id;
	}

}
