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
import br.com.esig.edu.crm.comum.dominio.Municipio;
import br.com.esig.edu.crm.comum.dominio.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Dinarte
 */

@Entity
@Table(name = "crm", schema = "endereco")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Endereco {

	public static final String RESIDENCIAL = "Residencial";
	public static final String COMERCIAL = "Comercial";
	public static final String TERCEIRO = "Terceiro";
	public static final String OUTRO = "Outro";
	
	@Id
	@GeneratedValue(generator = "endereco_generator")
	@GenericGenerator(name = "endereco_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "crm.endereco_seq") })
	@Column(name = "id_endereco", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;
	
	private String logradouro;
	
	private String numero;
	
	private String bairro;
	
	private String tipo;
	
	private String observacao;
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_municipio", unique = false, nullable = false)
	private Municipio municipio;
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_contato", unique = false, nullable = true)
	private Contato contato;
	
	@CriadoPor
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario_cadastro", unique = false, nullable = true)
	private Usuario usuarioCadastro;
	
	@CriadoEm
	@Column(name="data_cadastro")
	private Date dataCadastro;

}
