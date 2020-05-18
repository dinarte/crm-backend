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
 * Um tipo de etapa do funil de vendas é uma abstração da etapa do funil unificando o conseito da etapa para todos os funis de venda, permitindo ao 
 * usuário uam visão macro da situação de todos os funis de venda de sua instituição.
 *
 * @author Dinarte
 */

@Entity
@Table(schema = "crm", name = "funil_venda_etapa_tipo")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class FunilVendaEtapaTipo {

	public FunilVendaEtapaTipo(Integer tipoEtapaId) {
		this.id = tipoEtapaId;
	}

	@Id
	@GeneratedValue(generator = "funil_venda_etapa_tipo_generator")
	@GenericGenerator(name = "funil_venda_etapa_tipo_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "crm.funil_venda_etapa_tipo_seq") })
	@Column(name = "id_funil_venda_etapa_tipo", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;
	
	private String nome;
	
	private Integer ordem;
	
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
