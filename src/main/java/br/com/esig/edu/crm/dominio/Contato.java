package br.com.esig.edu.crm.dominio;

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
 *Mapeia as etapas por onde o cliente em potencial passa até se tornar um cliente. Aqui o usuário poderá customizar seu próprio funil informando quais etapas 
 *farão parte do seu funil e sua sequência. 
 *O sistema deverá trazer um funil padrão com as seguintes etapas: Cliente Em Potencial, Contato Efetuado, Visita Efetuada,  Proposta enviada, Convertido.
 *A página principal do sistema deverá mostrar de forma visualmente clara o funil de vendas de forma que o usuário possa identificar de maneira simples os negócios 
 *que estão em cada etapa.
 *
 * @author Dinarte
 */

@Entity
@Table(name = "crm", schema = "contato")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Contato {

	@Id
	@GeneratedValue(generator = "contato_generator")
	@GenericGenerator(name = "contato_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "crm.contato_seq") })
	@Column(name = "id_contato", unique = true, nullable = false, insertable = true, updatable = true)
	private int id;
	
	private String nomeContato;
	
	private String nomeOrganizacao;
	
	private String url;
	
	@OneToMany(mappedBy = "contato", fetch = FetchType.LAZY)
	private List<EnderecoEmail> emails;

	@OneToMany(mappedBy = "contato", fetch = FetchType.LAZY)
	private List<Telefone> telefones;

	@OneToMany(mappedBy = "endereco", fetch = FetchType.LAZY)
	private List<Telefone> enderecos;
	
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
