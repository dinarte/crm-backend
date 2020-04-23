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
import br.com.esig.edu.crm.comum.dominio.Instituicao;
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
@Table(schema = "crm", name = "motivo_desinteresse")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class MotivoDesinteresse {

	@Id
	@GeneratedValue(generator = "motivoDesinteresseGenerator")
	@GenericGenerator(name = "motivoDesinteresseGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "crm.motivo_desinteresse_seq") })
	@Column(name = "id_motivo_desinteresse", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;
	
	private String nome;
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_instituicao", unique = false, nullable = true)
	@br.com.esig.audit.annotations.Instituicao
	private Instituicao instituicao;
	
	@CriadoPor
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario_cadastro", unique = false, nullable = true)
	private Usuario usuarioCadastro;
	
	@CriadoEm
	@Column(name="data_cadastro")
	private Date dataCadastro;

}
