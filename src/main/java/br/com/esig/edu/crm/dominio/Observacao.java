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

import br.com.esig.audit.annotations.CriadoEm;
import br.com.esig.audit.annotations.CriadoPor;
import br.com.esig.edu.crm.comum.dominio.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * Os motivos de desinteresse são importantes para que a empresa melhore seu atendimento, captação e até mesmo seu produto, este motivo é 
 * colhido quando o cliente informa que não tem interesse no produto ou serviço.
 * @author Dinarte
 */

@Entity
@Table(name = "crm", schema = "observacao")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Observacao {

	/**
	 * Constante utilizada para indicar que uma observação foi inserida manualmente por um usuário
	 */
	public static final String USUARIO = "Usuário";
	
	/**
	 * Constante utilizada para indicar que uma observação foi inserida automáticamente pelo sistema
	 */
	public static final String SISTEMA = "Sistema";
	
	@Id
	@GeneratedValue(generator = "motivoDesinteresseGenerator")
	@GenericGenerator(name = "motivoDesinteresseGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "crm.observacao_seq") })
	@Column(name = "id_observacao", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;
	
	private String observacao;
	
	private String tipoObservacao;
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_lead", unique = false, nullable = true)
	private Lead lead;
	
	@CriadoPor
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario_cadastro", unique = false, nullable = true)
	private Usuario usuarioCadastro;
	
	@CriadoEm
	@Column(name="data_cadastro")
	private Date dataCadastro;
	
	public boolean isInformadoPeloUsuario() {
		return tipoObservacao.equals(USUARIO);
	}
	
	public boolean isInformadoPeloSistema() {
		return tipoObservacao.equals(SISTEMA);
	}

}
