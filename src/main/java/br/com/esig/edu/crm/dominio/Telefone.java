package br.com.esig.edu.crm.dominio;

import java.util.Date;
import java.util.Set;

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
import br.com.esig.edu.crm.comum.dominio.Discente;
import br.com.esig.edu.crm.comum.dominio.InstituicaoEnsino;
import br.com.esig.edu.crm.comum.dominio.Pessoa;
import br.com.esig.edu.crm.comum.dominio.ResponsavelDiscente;
import br.com.esig.edu.crm.comum.dominio.StatusDiscente;
import br.com.esig.edu.crm.comum.dominio.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Dinarte
 */

@Entity
@Table(name = "crm", schema = "telefone")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Telefone {

	public static final String PESSOAL = "Pessoal";
	public static final String COMERCIAL = "Comercial";
	public static final String TERCEIRO = "Terceiro";
	public static final String OUTRO = "Outro";
	
	@Id
	@GeneratedValue(generator = "telefone_generator")
	@GenericGenerator(name = "telefone_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "crm.telefone_seq") })
	@Column(name = "id_telefone", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;
	
	private String codigoArea;
	
	private String numero;
	
	private String observacao;
	
	private String tipo;
	
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
