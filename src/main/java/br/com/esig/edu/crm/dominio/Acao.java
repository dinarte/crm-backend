package br.com.esig.edu.crm.dominio;

import java.util.Date;
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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
@Table(schema = "crm", name = "acao")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Acao {

	public static String ATIVO = "Interação Ativa";
	public static String PASSIVO = "Interação Passiva";
	public static String INTRENO = "Procedimento Interno";
	
	@Id
	@GeneratedValue(generator = "acaoGenerator")
	@GenericGenerator(name = "acaoGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "crm.acao_seq") })
	@Column(name = "id_acao", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;
	
	private String tipo;

	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_lead", unique = false, nullable = false)
	private Lead lead;
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_acao_canal", unique = false, nullable = false)
	private AcaoCanal canal;
	
	private String resumo;
	
	private String descricao;
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario_operacao", unique = false, nullable = false)
	private Usuario usuarioOperacao;
	
	private Date dataOperacao;
	
	@OneToMany(mappedBy = "acao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Agendamento> agendamentos;
	
	/**
	 * Se a ação está sendo cadastrada como resultado de um agendamento realizado, 
	 * será criado a relação desta ação na entidade Agendamento.
	 */
	@Transient
	private Agendamento resultadoDoAgendamento;
	
	@Transient
	private boolean marcarLeadComoConvertido = false;
	
	@Transient
	private boolean marcarLeadComoDesinteressado = false;
	
	@Transient
	private MotivoDesinteresse motivoDesinteresse;
		
	
	public Acao(Integer id) {
		this.id = id;
	}
}
