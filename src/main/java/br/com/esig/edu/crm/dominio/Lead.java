package br.com.esig.edu.crm.dominio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
 * Representa um determinado cliente em potencial interessado em um ou mais  produto ou serviço, 
 * ao cadastrar um novo negócio o usuário está dando início ao processo de vendas que será acompanhado pelo Quark CRM. 
 * Aqui ele informará os dados do cliente, bem como seus contatos (telefone, email, apps de msg) e o valor que o dado negócio poderá gerar caso convertido e a 
 * partir do qual será dado início às ações de conversão que são ações que visão mover o 
 * negócio pelas etapas do funil de vendas com o objetivo de atingir a conversão
 * @author Dinarte
 */

@Entity
@Table(name = "crm", schema = "lead")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Lead {

	@Id
	@GeneratedValue(generator = "lead_generator")
	@GenericGenerator(name = "lead_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "crm.lead_seq") })
	@Column(name = "id_lead", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_contato", unique = false, nullable = false)
	private Contato contato;
	
	private BigDecimal valor; 
	
	private boolean desinteressado;
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_etapa", unique = false, nullable = true)
	private FunilVendaEtapa etapa;
	
	@OneToMany(mappedBy = "lead", fetch = FetchType.LAZY)
	private List<LeadProduto> produtos;

	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_campanha", unique = false, nullable = true)
	private Campanha campanha;
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_motivo_desinteresse", unique = false, nullable = true)
	private MotivoDesinteresse motivoDesinteresse;
		
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
