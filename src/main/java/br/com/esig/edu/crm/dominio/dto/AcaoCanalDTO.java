package br.com.esig.edu.crm.dominio.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "crm", name = "acao_canal")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class AcaoCanalDTO {
	
	@Id
	@GeneratedValue(generator = "acaoCnalGenerator")
	@GenericGenerator(name = "acaoCnalGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "crm.acao_canal_seq") })
	@Column(name = "id_acao", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;
	
	private String descricao;

}
