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
 * Etapas de um funil de vendas que determinam o processo pr onde uma oportunidade passa.
 * @author Dinarte
 */

@Entity
@Table(name = "crm", schema = "funil_venda_etapa")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class FunilVendaEtapa {
	
	@Id
	@GeneratedValue(generator = "funil_venda_etapa_generator")
	@GenericGenerator(name = "funil_venda_etapa_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "crm.funil_venda_etapa_seq") })
	@Column(name = "id_funil_venda_etapa", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;
	
	private String nome;
	
	private Integer ordem;
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_funil_venda_etapa_tipo", unique = false, nullable = false)
	private FunilVendaEtapaTipo tipo;

	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_funil_venda", unique = false, nullable = false)
	private FunilVenda funilVenda;
	
	@CriadoPor
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario_cadastro", unique = false, nullable = true)
	private Usuario usuarioCadastro;
	
	@CriadoEm
	@Column(name="data_cadastro")
	private Date dataCadastro;
	
}
