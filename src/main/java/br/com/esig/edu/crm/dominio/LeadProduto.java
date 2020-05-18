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
 * Produtos que são do interesse de um lead_produto
 * @author Dinarte
 */

@Entity
@Table(schema = "crm", name = "lead_produto")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class LeadProduto {

	@Id
	@GeneratedValue(generator = "lead_produto_generator")
	@GenericGenerator(name = "lead_produto_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "crm.lead_produto_seq") })
	@Column(name = "id_lead_produto", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_lead", unique = false, nullable = false)
	private Lead lead;

	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_produto", unique = false, nullable = false)
	private Produto produto;
	
	private Integer quantidade = 1;
	
	@CriadoPor
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario_cadastro", unique = false, nullable = true)
	private Usuario usuarioCadastro;
	
	@CriadoEm
	@Column(name="data_cadastro")
	private Date dataCadastro;
	
	public String toString() {
		return super.toString();
	}

}
