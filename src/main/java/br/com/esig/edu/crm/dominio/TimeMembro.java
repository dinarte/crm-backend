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

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.esig.edu.crm.comum.dominio.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 *Representa os membros de um dado time de vendas.
 *
 * @author Dinarte
 */

@Entity
@Table(schema = "crm", name = "time_membro")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class TimeMembro {

	@Id
	@GeneratedValue(generator = "time_generator")
	@GenericGenerator(name = "time_membro_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "crm.time_membro_seq") })
	@Column(name = "id_time_membro", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;	
	
	@JsonBackReference
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_time", unique = false, nullable = false)
	private Time time;
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario", unique = false, nullable = false)
	private Usuario usuario;

	public TimeMembro(int id) {
		this.id = id;
	}
	
	public String toString(){
		return super.toString();
	}

}
