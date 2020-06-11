package br.com.esig.edu.crm.dominio;

import java.util.Date;

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
 * 
 * @author Dinarte
 *
 */
@Entity
@Table(schema = "crm", name = "agendamento")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Agendamento {
		
	public static String AGENDADO = "Agendado";

	public static String CAMCELADO = "Cancelado";

	public static String REALIZADO = "Realizado";
	
	@Id
	@GeneratedValue(generator = "acaoAgendamento")
	@GenericGenerator(name = "acaoAgendamento", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "crm.agendamento_seq") })
	@Column(name = "id_agendamento", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;
	
	@JsonBackReference
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_acao", unique = false, nullable = false)
	private Acao acao;
	
	private String titulo;
	
	private String descricao;
	
	
	private Date inicio;
	
	
	private Date fim;
	
	private String situacao = AGENDADO;
	
	/**
	 * Log de alterações sofridas 
	 */
	private String resultado;
	
	@JsonBackReference(value = "realizacaoAgendamentoReference")
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_acao_realizacao_agendamento", unique = false, nullable = false)
	private Acao realizacaoAgendamento;
	
	private Date dataResultado;
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario_realizacao", unique = false, nullable = true)
	private Usuario usuarioRealizacao;
	
	@Override
	public String toString() {
		return super.toString();
	}
}
