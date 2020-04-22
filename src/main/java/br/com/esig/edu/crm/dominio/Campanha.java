package br.com.esig.edu.crm.dominio;

import java.math.BigDecimal;
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
 * Usualmente os processos de vendas são disparados através de campanhas de marketing, as campanhas de marketing são ações estruturadas e 
 * planejadas utilizando-se de um ou muitos canais de mídia que visam atrair uma grande quantidade de Leads, para que a partir de então, 
 * eles possam entrar no funil de vendas. Tais campanhas poderão ser informadas ao sistema com título, descrição, custo e período de duração, para que ao ser 
 * iniciado um novo negócio ele seja 
 * relacionado a uma campanha, possibilitando assim que seja aferido através de relatório a efetividade das mesmas
 * 
 * @author Dinarte
 */

@Entity
@Table(name = "crm", schema = "campanha")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Campanha {

	@Id
	@GeneratedValue(generator = "formaCaptacaoGenerator")
	@GenericGenerator(name = "formaCaptacaoGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "crm.campanha_seq") })
	@Column(name = "id_campanha", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;
	
	private String nome;
	
	private String Descricao;
	
	private BigDecimal custo;
	
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
