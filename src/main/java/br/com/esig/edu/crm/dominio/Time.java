package br.com.esig.edu.crm.dominio;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.esig.edu.crm.comum.dominio.Instituicao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 *Representa os times de vendas existentes na organização.
 *
 * @author Dinarte
 */

@Entity
@Table(schema = "crm", name = "time")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Time {


	@Id
	@GeneratedValue(generator = "time_generator")
	@GenericGenerator(name = "time_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "crm.time_seq") })
	@Column(name = "id_time", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;
	
	private String nome;
	
	private String descricao;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "time", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TimeMembro> membros;
	
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_instituicao", unique = false, nullable = true)
	@br.com.esig.audit.annotations.Instituicao
	private Instituicao instituicao;
	
	public Time(int id) {
		this.id = id;
	}
	
	public String toString(){
		return super.toString();
	}

}
